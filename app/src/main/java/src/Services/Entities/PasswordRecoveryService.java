package src.Services.Entities;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class PasswordRecoveryService {
    public static void recoverPassword(Integer DNI) throws Exception {
        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users")
                    .whereEqualTo("dni",DNI)
                    .get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            List<DocumentSnapshot> documentSnapshots = Objects.requireNonNull(task.getResult()).getDocuments();
                            if (documentSnapshots.size() > 0) {
                                Map<String,Object> map = documentSnapshots.get(0).getData();
                                String email=(String)map.get("email");
                                System.out.println(email);
                            }

                        }
                    });
        } catch(Exception ex) {
            throw new Exception("No hemos podido encontrar el dni ingresado");
        }
    }
}
