package vn.edu.vtn.models;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFireBaseIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        saveTokenIntoDatabase(token);
    }

    private void saveTokenIntoDatabase(String token) {
        new FireBaseIDTask().execute(token);
    }
}
