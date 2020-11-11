package src.Services.Entities;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import src.Activities.UnauthorizedViewModel;
import src.Models.User;
import src.Services.SessionService;
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

                                SessionService.setUser(user);
                                UnauthorizedViewModel.onUserChange(user);
                            } else {
                                UnauthorizedViewModel.onUserChange(null);
                            }
                        } else {
                            UnauthorizedViewModel.onUserChange(null);
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
                            UnauthorizedViewModel.onUserChange(null);
                        } else {
                            db.collection("users")
                                    .add(user.wrap())
                                    .addOnFailureListener(Throwable::printStackTrace);

                            UnauthorizedViewModel.onUserChange(user);
                        }
                    } else {
                        UnauthorizedViewModel.onUserChange(null);
                    }
                });
    }
}
