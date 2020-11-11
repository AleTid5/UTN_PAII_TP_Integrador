package src.Services;

import android.preference.PreferenceManager;

import src.Models.User;

public abstract class SessionService {
    private static User user = null;

    public static void setUser(User user) {
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putString("userId", user.getId()).apply();
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putString("name", user.getNameAndLastName()).apply();
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putString("email", user.getUserName()).apply();
    }

    public static User getUser() {
        if (user != null) {
            return user;
        }

        try {
            user = new User(null, null, null, null, null, null);
            user.setId(PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                    .getString("userId", null));
        } catch (Exception ignored) {}

        return user;
    }

    public static void cleanSession() {
        user = null;
    }
}
