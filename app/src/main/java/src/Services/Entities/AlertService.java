package src.Services.Entities;

import com.google.common.collect.Lists;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import src.Activities.ui.history_alerts.HistoryAlertsViewModel;
import src.Models.Alert;

public abstract class AlertService {
    public static void getAlertList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("alerts").get().addOnCompleteListener(alertTask -> {
            if (alertTask.isSuccessful()) {
                db.collection("user_blocked_alerts")
                        .whereEqualTo("user_from", UserSessionService.getUser().getId())
                        .get().addOnCompleteListener(userBlockedTask -> {
                            List<String> blockedUsers = new ArrayList<>();

                            for (QueryDocumentSnapshot userBlockedDocument : Objects.requireNonNull(userBlockedTask.getResult())) {
                                blockedUsers.add((String) userBlockedDocument.getData().get("user_to"));
                            }

                            for (QueryDocumentSnapshot userTask : Objects.requireNonNull(alertTask.getResult())) {
                                String userId = (String) userTask.getData().get("user_id");

                                if (!blockedUsers.contains(userId)) {
                                    Map<String, Object> map = userTask.getData();
                                    map.put("id", userId);

                                    HistoryAlertsViewModel.addAlert(new Alert().unwrap(map));
                                }
                            }
                        }
                );
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
    }
}
