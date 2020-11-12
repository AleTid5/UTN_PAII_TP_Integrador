package src.Services.Entities;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Map;
import java.util.Objects;

import src.Activities.ui.manage_history.ManageHistoryViewModel;
import src.Models.History;

public abstract class HistoryService {
    public static void fetchHistoryList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("historical_alerts")
                .whereEqualTo("user_id", UserSessionService.getUser().getId())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            Map<String, Object> map = document.getData();
                            map.put("id", document.getId());

                            ManageHistoryViewModel.addProduct(new History().unwrap(map));
                        }
                    } else {
                        System.out.println("ERROR!");
                    }
                });
    }

    public static void save(History history) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("historical_alerts")
                .add(history.wrap())
                .addOnFailureListener(Throwable::printStackTrace);

        ManageHistoryViewModel.addProduct(history);
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
