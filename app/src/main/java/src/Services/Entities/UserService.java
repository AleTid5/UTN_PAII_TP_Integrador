package src.Services.Entities;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.internal.bind.ObjectTypeAdapter;

import java.util.Arrays;
import java.util.List;

import src.Models.User;

public abstract class UserService {
    private static List<User> userList = Arrays.asList(
            new User("1", "Alejandro Tidele", 39100507, "12/08/1995", "AleTid5", "ale123ale"),
            new User("1", "Alejandro Tidele", 39100507, "12/08/1995", "AleTid5", "ale123ale"),
            new User("1", "Alejandro Tidele", 39100507, "12/08/1995", "AleTid5", "ale123ale"),
            new User("1", "Alejandro Tidele", 39100507, "12/08/1995", "AleTid5", "ale123ale"),
            new User("1", "Alejandro Tidele", 39100507, "12/08/1995", "AleTid5", "ale123ale")
    );

    public static List<User> getBlockedUserList() {
        return userList;
    }

    public static User findUserById(Integer userId) {
        return userList.get(2);
    }

    public static void authenticateUser (String email, String password) throws Exception
    {
        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection(("users"))
                    .whereEqualTo("email",email)
                    .whereEqualTo("password",password)
                    .get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Map<String, Object>
                        }
                    });
        }
        catch (Exception ex){
            throw new Exception("Usuario y/o contraseña incorrectos");
        }
    }
}
