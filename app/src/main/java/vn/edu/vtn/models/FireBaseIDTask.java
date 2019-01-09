package vn.edu.vtn.models;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FireBaseIDTask extends AsyncTask<String, Void, Boolean> {
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            URL url = new URL("http://192.168.1.13/hocfcm/api/fcm/?token=" + strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.addRequestProperty("Content-Type", "application/xml;charset=UTF-8");

            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
            boolean kt = bufferedReader.toString().contains("true");
            return kt;
        } catch (Exception e) {
            Log.e("LOI", e.toString());
        }
        return null;
    }
}
