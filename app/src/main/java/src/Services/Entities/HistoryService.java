package src.Services.Entities;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;

import src.Activities.ui.manage_history.ManageHistoryViewModel;
import src.Models.History;

public abstract class HistoryService {
    public static void fetchHistoryList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("historical_alerts")
                .whereEqualTo("user_id", UserSessionService.getUser().getId())
                .get()
                .addOnCompleteListener(task -> {
                    try {
                        if (!task.isSuccessful()) throw new Exception();

                        List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();

                        if (documentSnapshots.isEmpty()) throw new Exception();

                        documentSnapshots.forEach(documentSnapshot -> {
                            Map<String, Object> map = documentSnapshot.getData();
                            map.put("id", documentSnapshot.getId());

                            ManageHistoryViewModel.addHistoryReport(new History().unwrap(map));
                        });
                    } catch (Exception e) {
                        ManageHistoryViewModel.noFetchedReports();
                    }
                });
    }

    public static void save(History history) {
        FirebaseFirestore.getInstance()
                .collection("historical_alerts")
                .add(history.wrap())
                .addOnFailureListener(Throwable::printStackTrace);

        ManageHistoryViewModel.addHistoryReport(history);
    }

    public static void update(History history) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("historical_alerts")
                .document(history.getId())
                .update(history.wrap());

        ManageHistoryViewModel.updateProduct(history);
    }

    public static void remove(String historicalId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("historical_alerts").document(historicalId).delete();
    }
}
