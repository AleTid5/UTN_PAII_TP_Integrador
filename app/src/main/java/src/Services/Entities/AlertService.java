package src.Services.Entities;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import src.Models.Alert;

public abstract class AlertService {
    private static List<Alert> alertList;

    public static List<Alert> getAlertList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("alerts").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                alertList = new ArrayList<>();
                for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                    System.out.println(document.getData());
                    alertList.add(new Alert());
                }
            } else {
                System.out.println("ERROR!");
            }
        });
        return alertList;
    }

    public static void save() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> user = new HashMap<>();
        user.put("name", "Ale");
        user.put("contact", "something");
        user.put("observations", "Lorem ipsum");

        db.collection("alerts")
                .add(user)
                .addOnSuccessListener(documentReference -> System.out.println(documentReference.getId()))
                .addOnFailureListener(Throwable::printStackTrace);
    }
}
