package src.Models;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import src.Services.ContextManagerService;

public class NotificationSingleton {
    private static NotificationSingleton instance;
    private RequestQueue requestQueue;

    private NotificationSingleton() {
        requestQueue = getRequestQueue();
    }

    public static synchronized NotificationSingleton getInstance() {
        if (instance == null) {
            instance = new NotificationSingleton();
        }

        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ContextManagerService.getContext().getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
