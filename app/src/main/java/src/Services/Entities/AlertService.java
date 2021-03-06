package src.Services.Entities;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;

import src.Activities.ui.history_alerts.HistoryAlertsViewModel;
import src.Models.Alert;
import src.Services.Notifications.AlertNotificationService;
import src.Validators.DateValidator;

public abstract class AlertService {
    public static void fetchAlertList() {
        Executors.newFixedThreadPool(1).submit(() -> FirebaseFirestore
                .getInstance()
                .collection("alerts")
                .orderBy("created_date", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(alertTask -> {
                    try {
                        if (!alertTask.isSuccessful()) throw new Exception();

                        List<DocumentSnapshot> documentSnapshots = alertTask.getResult().getDocuments();

                        if (documentSnapshots.isEmpty()) throw new Exception();

                        final List<String> blockedUsers = UserSessionService.getUser().getBlockedUsers();

                        documentSnapshots.forEach(documentSnapshot -> {
                            String userId = (String) documentSnapshot.getData().get("user_id");

                            if (!blockedUsers.contains(userId)) {
                                Map<String, Object> map = documentSnapshot.getData();
                                map.put("id", userId);

                                HistoryAlertsViewModel.addAlert(new Alert().unwrap(map));
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
    }

    public static void save(Alert alert) {
        Executors.newFixedThreadPool(1).submit(() -> FirebaseFirestore
                .getInstance()
                .collection("alerts")
                .add(alert.wrap())
                .addOnFailureListener(Throwable::printStackTrace));

        AlertNotificationService.sendNotification("Nueva alerta", "¡Una nueva alerta cerca de tu zona se ha producido!");
    }

    public static void blockUser(String userId) {
        Map<String, Object> map = new HashMap<>();

        map.put("user_from", UserSessionService.getUser().getId());
        map.put("user_to", userId);
        map.put("block_date", DateValidator.getThisMomentAsDate());

        Executors.newFixedThreadPool(1).submit(() -> FirebaseFirestore
                .getInstance()
                .collection("user_blocked_alerts")
                .add(map)
                .addOnFailureListener(Throwable::printStackTrace));

        UserSessionService.getUser().getBlockedUsers().add(userId);
        UserSessionService.setUser(UserSessionService.getUser());
    }

    public static void unblockUser(List<String> users) {
        if (users.isEmpty()) return;

        Executors.newFixedThreadPool(1).submit(() -> FirebaseFirestore
                .getInstance()
                .collection("user_blocked_alerts")
                .whereEqualTo("user_from", UserSessionService.getUser().getId())
                .whereIn("user_to", users)
                .get()
                .addOnCompleteListener(t1 -> Objects.requireNonNull(t1.getResult()).forEach(t2 -> t2.getReference().delete())));

        users.forEach(userId -> UserSessionService.getUser().getBlockedUsers().remove(userId));
        UserSessionService.setUser(UserSessionService.getUser());
    }
}
