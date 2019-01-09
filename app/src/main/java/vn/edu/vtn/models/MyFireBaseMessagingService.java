package vn.edu.vtn.models;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import vn.edu.vtn.fcmclient.MainActivity;
import vn.edu.vtn.fcmclient.R;

public class MyFireBaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getNotification() != null) { // Có nghĩa là gửi từ Server của Google
            toProcessShowMess(remoteMessage.getNotification().getBody());
            return;
        }
        toProcessShowMess(remoteMessage.getData().get("body"), remoteMessage.getData().get("title"));
    }

    private void toProcessShowMess(String body, String title) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_bell)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.gg))
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        builder.setContentIntent(pendingIntent);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }
    private void toProcessShowMess(String body) {
        toProcessShowMess(body, "From Google !!!");
    }
}
