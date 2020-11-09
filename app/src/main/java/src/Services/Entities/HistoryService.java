package src.Services.Entities;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import src.Models.Alert;
import src.Models.History;

public abstract class HistoryService {
    private static List<History> historyList;

    public static List<History> getHistoryList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("historical_alerts").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                historyList = new ArrayList<>();
                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                    Map<String, Object> map = document.getData();
                    map.put("id", document.getId());

                    historyList.add(new History().unwrap(map));
                }
            } else {
                System.out.println("ERROR!");
            }
        });

        return historyList;
    }

    public static void save(History history) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("historical_alerts")
                .add(history.wrap())
                .addOnSuccessListener(documentReference -> System.out.println(documentReference.getId()))
                .addOnFailureListener(Throwable::printStackTrace);
    }

    public static void remove(String historicalId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("historical_alerts").document(historicalId).delete();
    }
}
