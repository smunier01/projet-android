package com.example.excilys.projetmessages;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class LogTask extends AsyncTask<String, Void, Boolean> {

    private MainActivity act;

    private String username;
    private String password;

    public LogTask(MainActivity act) {
        this.act = act;
    }

    @Override
    protected void onPreExecute() {
        username = act.getUsernameField().getText().toString();
        password = act.getPasswordField().getText().toString();
    }

    @Override
    protected Boolean doInBackground(String... strings) {

        URL url = null;
        HttpURLConnection urlConnection = null;
        boolean result = false;
        try {
            url = new URL("http://formation-android-esaip.herokuapp.com/connect/" + username + "/" + password);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            String s = InputStreamToString.convert(in);

            if (s.equals("true")) {
                result = true;
            } else {
                result = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(Boolean o) {
        super.onPostExecute(o);
    }
}
