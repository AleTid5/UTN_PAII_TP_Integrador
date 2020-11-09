package src.Services.Entities;

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
}
