package src.Services.Notifications;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import src.Models.NotificationSingleton;
import src.Services.Entities.UserSessionService;

public abstract class AlertNotificationService {
    final static private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final static private String serverKey = "key=AAAA1uDe2r8:APA91bHTYaL9lFOAmJh3Yp8PAlmz-n4cFk97qyQsgzDcdePSUbStouEy4AjWcO00l_Q-8twnJRuuoggJbUGS5oTdCy0r9zC2medIWGLZolL_SikUyEt5qWGs_efil9Lv0C3PjV9gfxKx";
    final static private String contentType = "application/json";

    private static JSONObject notification = new JSONObject();
    private static JSONObject notificationBody = new JSONObject();

    public static void sendNotification(String title, String message) {
        try {
            notificationBody.put("title", title);
            notificationBody.put("message", message);
            notificationBody.put("user_from", UserSessionService.getUser().getId());
            notification.put("to", "/topics/alerts");
            notification.put("data", notificationBody);

            pushNotification(notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void pushNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                response -> {},
                error -> {}
        ){
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
