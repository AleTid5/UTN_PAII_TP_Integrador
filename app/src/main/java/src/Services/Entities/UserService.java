package src.Services.Entities;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import src.Activities.ui.manage_history.ManageHistoryViewModel;
import src.Activities.ui.setup_account.UserViewModel;
import src.Models.History;
import src.Models.User;
import src.Validators.PasswordValidator;

public abstract class UserService {
    public static List<User> getBlockedUserList() {
        return new ArrayList<>();
    }

    public static User findUserById(Integer userId) {
        return new User();
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

                            if (documentSnapshots.size() == 1) {
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

        db.collection("users")
                .whereEqualTo("email", user.getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<DocumentSnapshot> documentSnapshots = Objects.requireNonNull(task.getResult()).getDocuments();

                        if (documentSnapshots.size() != 0) {
                            UserViewModel.onUserChange(null);
                        } else {
                            db.collection("users")
                                    .add(user.wrap())
                                    .addOnFailureListener(Throwable::printStackTrace);

                            UserViewModel.onUserChange(user);
                        }
                    } else {
                        UserViewModel.onUserChange(null);
                    }
                });
    }

    public static void updateUser(User user) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (UserSessionService.getUser().getEmail().equals(user.getEmail())) {
            onUpdate(db, user);
        } else {
            checkUserEmailAndUpdate(db, user);
        }
    }

    /**
     * Valida que el email ingresado no pertenezca a otro usuario
     * @param db
     * @param user
     */
    private static void checkUserEmailAndUpdate(FirebaseFirestore db, User user) {
        db.collection("users")
                .whereEqualTo("email", user.getEmail())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<DocumentSnapshot> documentSnapshots = Objects.requireNonNull(task.getResult()).getDocuments();

                        if (documentSnapshots.size() != 0) {
                            UserViewModel.onUserChange(null);
                        } else {
                            onUpdate(db, user);
                        }
                    } else {
                        UserViewModel.onUserChange(null);
                    }
                });
    }

    private static void onUpdate(FirebaseFirestore db, User user) {
        db.collection("users")
                .document(user.getId())
                .update(user.wrap());

        UserSessionService.setUser(user);
        UserViewModel.onUserChange(user);
    }

    private static void fillUserData(User user) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(("user_blocked_alerts"))
                .whereEqualTo("user_from", user.getId())
                .get()
                .addOnCompleteListener(task -> {
                    try {
                        if (!task.isSuccessful()) throw new Exception();

                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            Map<String, Object> map = document.getData();
                            user.addBlockedUser((String) map.get("user_to"));
                        }
                    } catch (Exception ignored) {
                    } finally {
                        UserSessionService.setUser(user);
                    }
                });
    }
}
