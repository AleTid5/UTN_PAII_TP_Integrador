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
                .edit().putInt("dni", user.getDNI()).apply();
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putString("born_date", user.getBornDate()).apply();
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putString("username", user.getUserName()).apply();
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putString("email", user.getEmail()).apply();
        PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                .edit().putString("password", user.getPassword()).apply();
    }

    public static User getUser() {
        if (user != null) {
            return user;
        }

        try {
            user = new User();
            user.setId(PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                    .getString("userId", null));
            user.setNameAndLastName(PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                    .getString("name", null));
            user.setDNI(PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                    .getInt("dni", 0));
            user.setBornDate(PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                    .getString("born_date", null));
            user.setUserName(PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                    .getString("username", null));
            user.setEmail(PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                    .getString("email", null));
            user.setPassword(PreferenceManager.getDefaultSharedPreferences(ContextManagerService.getContext())
                    .getString("password", null));
        } catch (Exception ignored) {}

        return user;
    }

    public static void cleanSession() {
        user = null;
    }
}
