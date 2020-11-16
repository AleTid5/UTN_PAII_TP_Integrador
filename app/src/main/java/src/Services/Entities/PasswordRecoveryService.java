package src.Services.Entities;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;

import src.Activities.StatusViewModel;
import src.Enums.StatusEnum;
import src.Models.User;
import src.Services.Notifications.EmailSenderService;
import src.Validators.PasswordValidator;

public abstract class PasswordRecoveryService {
    public static void recoverPassword(Integer DNI) {
        Executors.newFixedThreadPool(1).submit(() -> FirebaseFirestore
                .getInstance()
                .collection("users")
                .limit(1)
                .whereEqualTo("dni", DNI)
                .get()
                .addOnCompleteListener(task -> {
                    try {
                        if (!task.isSuccessful()) throw task.getException();

                        List<DocumentSnapshot> documentSnapshots = Objects.requireNonNull(task.getResult()).getDocuments();

                        if (documentSnapshots.isEmpty()) throw new Exception();

                        Map<String,Object> map = documentSnapshots.get(0).getData();
                        assert map != null;

                        User user = new User();
                        user.setEmail((String) map.get("email"));

                        StatusViewModel.onStatusChange(StatusEnum.TRANSACTION_OK);

                        new EmailSenderService().sendMail(
                                String.format("La contaseña de su cuenta es: %s", PasswordValidator.decrypt((String) map.get("password"))),
                                user.getEmail()
                        );
                    } catch (Exception e) {
                        StatusViewModel.onStatusChange(StatusEnum.INVALID_DNI);
                    }
                }));
    }
}
