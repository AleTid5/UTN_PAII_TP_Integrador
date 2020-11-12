package src.Services.Notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.tp_cuatrimestral.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

import src.Activities.LoginActivity;
import src.Services.Entities.UserSessionService;

public class FirebaseNotificationService extends FirebaseMessagingService {
    private final String ADMIN_CHANNEL_ID ="alerts";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (!showMessage(remoteMessage.getData())) {
            return;
        }

        final Intent intent = new Intent(this, LoginActivity.class);
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationID = new Random().nextInt(3000);

      /*
        Apps targeting SDK 26 or above (Android O) must implement notification channels and add its notifications
        to at least one of them. Therefore, confirm if version is Oreo or higher, then setup notification channel
      */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            setupChannels(notificationManager);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_chat);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, ADMIN_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_chat)
                .setLargeIcon(largeIcon)
                .setContentTitle(getTitle(remoteMessage))
                .setContentText(getBody(remoteMessage))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        //Set notification color to match your app color template
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            notificationBuilder.setColor(getResources().getColor(R.color.colorPrimary));
        }

        notificationManager.notify(notificationID, notificationBuilder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setupChannels(NotificationManager notificationManager){
        CharSequence adminChannelName = "New notification";
        String adminChannelDescription = "Device to devie notification";

        NotificationChannel adminChannel;
        adminChannel = new NotificationChannel(ADMIN_CHANNEL_ID, adminChannelName, NotificationManager.IMPORTANCE_HIGH);
        adminChannel.setDescription(adminChannelDescription);
        adminChannel.enableLights(true);
        adminChannel.setLightColor(Color.RED);
        adminChannel.enableVibration(true);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(adminChannel);
        }
    }

    private String getTitle(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() == null) {
            return remoteMessage.getData().get("title");
        }

        return remoteMessage.getNotification().getTitle();
    }

    private String getBody(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() == null) {
            return remoteMessage.getData().get("message");
        }

        return remoteMessage.getNotification().getBody();
    }

    private Boolean showMessage(Map<String, String> data) {
        if (data == null || UserSessionService.getUser() == null) {
            return true;
        }

        return !isFromBlockedUser(data.get("user_from")) && !isSameUser(data.get("user_from"));
    }

    private Boolean isSameUser(String userId) {
        return UserSessionService.getUser().getId() == userId;
    }

    private Boolean isFromBlockedUser(String userId) {
        return UserSessionService.getUser().getBlockedUsers().contains(userId);
    }
}
