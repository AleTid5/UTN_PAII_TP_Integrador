package src.Services.Entities;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import src.Activities.ui.history_alerts.HistoryAlertsViewModel;
import src.Models.Alert;
import src.Services.Notifications.AlertNotificationService;

public abstract class AlertService {
    public static void fetchAlertList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("alerts").get().addOnCompleteListener(alertTask -> {
            if (alertTask.isSuccessful()) {
                List<String> blockedUsers = UserSessionService.getUser().getBlockedUsers();

                for (QueryDocumentSnapshot userTask : Objects.requireNonNull(alertTask.getResult())) {
                    String userId = (String) userTask.getData().get("user_id");

                    if (!blockedUsers.contains(userId)) {
                        Map<String, Object> map = userTask.getData();
                        map.put("id", userId);

                        HistoryAlertsViewModel.addAlert(new Alert().unwrap(map));
                    }
                }
            } else {
                System.out.println("ERROR!");
            }
        });
    }

    public static void save(Alert alert) {
        FirebaseFirestore.getInstance()
                .collection("alerts")
                .add(alert.wrap())
                .addOnFailureListener(Throwable::printStackTrace);

        AlertNotificationService.sendNotification("Nueva alerta", "¡Una nueva alerta cerca de tu zona se ha producido!");
    }

    public static void blockUser(String userId) {
        Map<String, Object> map = new HashMap<>();

        map.put("user_from", UserSessionService.getUser().getId());
        map.put("user_to", userId);
        map.put("block_date", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("es", "ES")).format(new Date()));

        FirebaseFirestore.getInstance()
                .collection("user_blocked_alerts")
                .add(map)
                .addOnFailureListener(Throwable::printStackTrace);

        UserSessionService.getUser().getBlockedUsers().add(userId);
        UserSessionService.setUser(UserSessionService.getUser());
    }

    public static void unblockUser(List<String> users) {
        if (users.isEmpty()) return;

        FirebaseFirestore.getInstance()
                .collection("user_blocked_alerts")
                .whereEqualTo("user_from", UserSessionService.getUser().getId())
                .whereIn("user_to", users)
                .get()
                .addOnCompleteListener(t1 -> Objects.requireNonNull(t1.getResult()).forEach(t2 -> t2.getReference().delete()));

        users.forEach(userId -> UserSessionService.getUser().getBlockedUsers().remove(userId));
        UserSessionService.setUser(UserSessionService.getUser());
    }
}
