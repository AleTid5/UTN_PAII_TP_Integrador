package src.Services.Entities;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import src.Activities.ui.setup_account.UserViewModel;
import src.Models.User;
import src.Services.Notifications.EmailSenderService;
import src.Validators.PasswordValidator;

public abstract class PasswordRecoveryService {
    public static void recoverPassword(Integer DNI) throws Exception {
        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("users")
                    .whereEqualTo("dni", DNI)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documentSnapshots = Objects.requireNonNull(task.getResult()).getDocuments();

                            if (! documentSnapshots.isEmpty()) {
                                Map<String,Object> map = documentSnapshots.get(0).getData();
                                assert map != null;

                                User user = new User();
                                user.setEmail((String) map.get("email"));

                                UserViewModel.onUserChange(user);
                                new EmailSenderService().sendMail(
                                        String.format("La contaseña de su cuenta es: %s", PasswordValidator.decrypt((String) map.get("password"))),
                                        user.getEmail()
                                );
                            } else {
                                UserViewModel.onUserChange(null);
                            }
                        }
                    });
        } catch(Exception ex) {
            throw new Exception("No hemos podido encontrar el dni ingresado");
        }
    }
}
