package src.Models;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import src.Services.ContextManagerService;

public class NotificationSingleton {
    private static NotificationSingleton instance;
    private RequestQueue requestQueue;
    private Context ctx;

    private NotificationSingleton() {
        ctx = ContextManagerService.getContext();
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
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
