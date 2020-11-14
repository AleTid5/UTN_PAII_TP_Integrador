package src.Services.Entities;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.Executors;

import src.Activities.ui.dashboard.DashboardViewModel;

public abstract class DashboardService {
    public static Boolean firstTime = true;

    public static void initializeDashboard() {
        Executors.newFixedThreadPool(1).submit(() -> {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("historical_alerts")
                    .whereEqualTo("user_id", UserSessionService.getUser().getId())
                    .get()
                    .addOnSuccessListener(t -> {
                        if (firstTime) {
                            firstTime = false;
                            initializeDashboard();
                        }

                        final Integer historyAlertsCount = t.getDocuments().size();
                        db.collection("alerts")
                                .get()
                                .addOnSuccessListener(t2 -> {
                                    final Integer alertsCount = t2.getDocuments().size();
                                    db.collection("user_blocked_alerts")
                                            .whereEqualTo("user_from", UserSessionService.getUser().getId())
                                            .get()
                                            .addOnSuccessListener(t3 -> {
                                                final Integer blockedUsersCount = t3.getDocuments().size();
                                                DashboardViewModel.setLiveHistoryReportsTotal(historyAlertsCount);
                                                DashboardViewModel.setLiveAlertsTotal(alertsCount);
                                                DashboardViewModel.setLiveBlockedUsersTotal(blockedUsersCount);

                                                Executors.newFixedThreadPool(1).submit(() -> {
                                                    try {
                                                        Thread.sleep(1000);
                                                        DashboardViewModel.updateTotal(historyAlertsCount + alertsCount + blockedUsersCount);
                                                    } catch (InterruptedException ex) {
                                                        Thread.currentThread().interrupt();
                                                    }
                                                });
                                            });
                                });
                    });
        });
    }
}
