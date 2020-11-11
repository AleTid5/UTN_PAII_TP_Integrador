package src.Services.Entities;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import src.Activities.ui.history_alerts.HistoryAlertsViewModel;
import src.Models.Alert;
import src.Services.SessionService;

public abstract class AlertService {
    public static void getAlertList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("alerts").get().addOnCompleteListener(alertTask -> {
            if (alertTask.isSuccessful()) {
                db.collection("user_blocked_alerts")
                        .whereEqualTo("user_from", SessionService.getUser().getId())
                        .get().addOnCompleteListener(userBlockedTask -> {
                            /*for (QueryDocumentSnapshot userBlockedDocument : Objects.requireNonNull(alertTask.getResult())) {
                                for (QueryDocumentSnapshot userTask : Objects.requireNonNull(alertTask.getResult())) {
                                    Map<String, Object> map = userTask.getData();
                                    map.put("id", userTask.getId());

                                    HistoryAlertsViewModel.addAlert(new Alert().unwrap(map));
                                }
                            }*/

                            for (QueryDocumentSnapshot userTask : Objects.requireNonNull(alertTask.getResult())) {
                                Map<String, Object> map = userTask.getData();
                                map.put("id", userTask.getId());

                                HistoryAlertsViewModel.addAlert(new Alert().unwrap(map));
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

        map.put("user_from", SessionService.getUser().getId());
        map.put("user_to", userId);
        map.put("block_date", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", new Locale("es", "ES")).format(new Date()));

        FirebaseFirestore.getInstance()
                .collection("user_blocked_alerts")
                .add(map)
                .addOnFailureListener(Throwable::printStackTrace);
    }
}
