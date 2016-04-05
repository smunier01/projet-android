package com.example.excilys.projetmessages;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class SendMessageTask extends AsyncTask<String, Void, Void> {

    private SendMessageActivity act;

    private String username;
    private String password;
    private String message;

    public SendMessageTask(SendMessageActivity act, String username, String password, String message) {
        this.username = username;
        this.password = password;
        this.message = message;
        this.act = act;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Void doInBackground(String... strings) {


        URL url = null;
        HttpURLConnection urlConnection = null;

        try {
            String msgEncoded = URLEncoder.encode(message, "UTF-8").replace("+", "%20");

            url = new URL("http://formation-android-esaip.herokuapp.com/message/" + username + "/" + password + "/" + msgEncoded);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.getInputStream();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            if (urlConnection != null) {
                urlConnection.disconnect();
            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);
        act.taskOK();
    }
}