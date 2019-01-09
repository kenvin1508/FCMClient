package vn.edu.vtn.fcmclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import vn.edu.vtn.models.FireBaseIDTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().subscribeToTopic("testForm");
        String token = FirebaseInstanceId.getInstance().getToken();
        new FireBaseIDTask().execute(token);
    }
}
