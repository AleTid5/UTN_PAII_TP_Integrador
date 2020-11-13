package src.Services.Entities;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import src.Activities.StatusViewModel;
import src.Activities.ui.blocked_users.BlockedUsersViewModel;
import src.Activities.ui.setup_account.UserViewModel;
import src.Enums.StatusEnum;
import src.Models.User;
import src.Services.Notifications.EmailSenderService;
import src.Validators.PasswordValidator;

public abstract class UserService {
    public static void fetchBlockedUserList() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        List<String> blockedUsers = UserSessionService.getUser().getBlockedUsers();

        if (blockedUsers.isEmpty()) {
            BlockedUsersViewModel.onAddBlockedUser(null);
            return;
        }

        db.collection("users")
                .whereIn(FieldPath.documentId(), blockedUsers)
                .get()
                .addOnCompleteListener(task -> {
                    try {
                        if (!task.isSuccessful()) throw new Exception();

                        List<DocumentSnapshot> documentSnapshots = Objects.requireNonNull(task.getResult()).getDocuments();

                        if (documentSnapshots.isEmpty()) throw new Exception();

                        documentSnapshots.forEach(documentSnapshot -> {
                            Map<String, Object> map = documentSnapshot.getData();
                            map.put("id", documentSnapshot.getId());

                            BlockedUsersViewModel.onAddBlockedUser(new User().unwrap(map));
                        });

                    } catch (Exception e) {}
                });
    }

    public static void authenticateUser (String email, String password) throws Exception
    {
        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection(("users"))
                    .whereEqualTo("email", email)
                    .whereEqualTo("password", PasswordValidator.encrypt(password))
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documentSnapshots = Objects.requireNonNull(task.getResult()).getDocuments();

                            if (!documentSnapshots.isEmpty()) {
                                Map<String,Object> map = documentSnapshots.get(0).getData();
                                assert map != null;
                                map.put("id", documentSnapshots.get(0).getId());

                                User user = new User().unwrap(map);

                                fillUserData(user);
                                UserViewModel.onUserChange(user);
                            } else {
                                UserViewModel.onUserChange(null);
                            }
                        } else {
                            UserViewModel.onUserChange(null);
                        }
                    });
        } catch (Exception ex){
            throw new Exception("Usuario y/o contraseña incorrectos");
        }
    }

    public static void registerUser(User user) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        checkUserEmailAndDNIThenExecute(db, user, () -> db.collection("users")
                .add(user.wrap())
                .addOnFailureListener(t -> StatusViewModel.onStatusChange(StatusEnum.TRANSACTION_FAILED))
                .addOnSuccessListener(t -> {
                    StatusViewModel.onStatusChange(StatusEnum.TRANSACTION_OK);

                    /*new EmailSenderService().sendMail(
                            "Bienvenido a la plataforma de Obras en la calle",
                            String.format("Hola %s, muchas gracias por suscribirse a \"Obras en la calle\".", user.getNameAndLastName()),
                            user.getEmail()
                    );*/
                }));
    }

    public static void updateUser(User user) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (UserSessionService.getUser().getEmail().equals(user.getEmail()) && UserSessionService.getUser().getDNI().equals(user.getDNI())) {
            onUpdate(db, user);
        } else if (!UserSessionService.getUser().getEmail().equals(user.getEmail()) && !UserSessionService.getUser().getDNI().equals(user.getDNI())) {
            checkUserEmailAndDNIThenExecute(db, user, () -> onUpdate(db, user));
        } else if (!UserSessionService.getUser().getEmail().equals(user.getEmail())) {
            checkUserEmailThenUpdate(db, user);
        } else {
            checkUserDNIThenUpdate(db, user);
        }
    }

    /**
     * Valida que el email ingresado no pertenezca a otro usuario
     * @param db
     * @param user
     */
    private static void checkUserEmailAndDNIThenExecute(FirebaseFirestore db, final User user, Runnable runnable) {
        db.collection("users")
                .whereEqualTo("email", user.getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    try {
                        if (!task.isSuccessful()) throw new Exception();

                        List<DocumentSnapshot> documentSnapshots = Objects.requireNonNull(task.getResult()).getDocuments();

                        if (!documentSnapshots.isEmpty()) throw new Exception();

                        db.collection("users")
                                .whereEqualTo("dni", user.getDNI())
                                .get()
                                .addOnCompleteListener(task2 -> {
                                    try {
                                        if (!task2.isSuccessful()) throw new Exception();

                                        List<DocumentSnapshot> documentSnapshots2 = Objects.requireNonNull(task2.getResult()).getDocuments();

                                        if (!documentSnapshots2.isEmpty()) throw new Exception();

                                        runnable.run();
                                    } catch (Exception e) {
                                        StatusViewModel.onStatusChange(StatusEnum.DUPLICATED_DNI);
                                    }
                                });
                    } catch (Exception e) {
                        StatusViewModel.onStatusChange(StatusEnum.DUPLICATED_EMAIL);
                    }
                });
    }

    /**
     * Valida que el email ingresado no pertenezca a otro usuario
     * @param db
     * @param user
     */
    private static void checkUserEmailThenUpdate(FirebaseFirestore db, User user) {
        db.collection("users")
                .whereEqualTo("email", user.getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    try {
                        if (!task.isSuccessful()) throw new Exception();

                        List<DocumentSnapshot> documentSnapshots = Objects.requireNonNull(task.getResult()).getDocuments();

                        if (!documentSnapshots.isEmpty()) throw new Exception();

                        onUpdate(db, user);
                    } catch (Exception e) {
                        StatusViewModel.onStatusChange(StatusEnum.DUPLICATED_EMAIL);
                    }
                });
    }

    /**
     * Valida que el DNI ingresado no pertenezca a otro usuario
     * @param db
     * @param user
     */
    private static void checkUserDNIThenUpdate(FirebaseFirestore db, User user) {
        db.collection("users")
                .whereEqualTo("dni", user.getDNI())
                .get()
                .addOnCompleteListener(task -> {
                    try {
                        if (!task.isSuccessful()) throw new Exception();

                        List<DocumentSnapshot> documentSnapshots = Objects.requireNonNull(task.getResult()).getDocuments();

                        if (!documentSnapshots.isEmpty()) throw new Exception();

                        onUpdate(db, user);
                    } catch (Exception e) {
                        StatusViewModel.onStatusChange(StatusEnum.DUPLICATED_DNI);
                    }
                });
    }

    private static void onUpdate(FirebaseFirestore db, User user) {
        db.collection("users")
                .document(user.getId())
                .update(user.wrap())
                .addOnFailureListener(t -> StatusViewModel.onStatusChange(StatusEnum.TRANSACTION_FAILED))
                .addOnSuccessListener(t -> {
                    UserSessionService.setUser(user);
                    StatusViewModel.onStatusChange(StatusEnum.TRANSACTION_OK);
                });
    }

    private static void fillUserData(User user) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(("user_blocked_alerts"))
                .whereEqualTo("user_from", user.getId())
                .get()
                .addOnCompleteListener(task -> {
                    try {
                        if (!task.isSuccessful()) throw new Exception();

                        task.getResult().getDocuments().forEach(documentSnapshot ->
                                user.addBlockedUser((String) documentSnapshot.getData().get("user_to")));
                    } catch (Exception ignored) {
                    } finally {
                        UserSessionService.setUser(user);
                    }
                });
    }
}
