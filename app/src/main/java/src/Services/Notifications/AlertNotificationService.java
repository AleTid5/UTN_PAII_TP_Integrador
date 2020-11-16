package src.Services.Notifications;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import src.Models.NotificationSingleton;
import src.Services.Entities.UserSessionService;

public abstract class AlertNotificationService {
    final static private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final static private String serverKey = "key=AAAA1uDe2r8:APA91bHTYaL9lFOAmJh3Yp8PAlmz-n4cFk97qyQsgzDcdePSUbStouEy4AjWcO00l_Q-8twnJRuuoggJbUGS5oTdCy0r9zC2medIWGLZolL_SikUyEt5qWGs_efil9Lv0C3PjV9gfxKx";
    final static private String contentType = "application/json";

    private static final JSONObject notification = new JSONObject();
    private static final JSONObject notificationBody = new JSONObject();

    public static void sendNotification(String title, String message) {
        Executors.newFixedThreadPool(1).submit(() -> {
            try {
                notificationBody.put("title", title);
                notificationBody.put("message", message);
                notificationBody.put("user_from", UserSessionService.getUser().getId());
                notification.put("to", "/topics/alerts");
                notification.put("data", notificationBody);

                pushNotification();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void pushNotification() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, AlertNotificationService.notification, null, null) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };

        NotificationSingleton.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
