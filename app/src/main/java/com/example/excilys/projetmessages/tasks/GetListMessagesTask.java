package com.example.excilys.projetmessages.tasks;

import android.os.AsyncTask;

import com.example.excilys.projetmessages.mappers.InputStreamToString;
import com.example.excilys.projetmessages.activities.ListMessagesActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * AsyncTask récupérant les messages
 */
public class GetListMessagesTask extends AsyncTask<String, Void, String> {

    private ListMessagesActivity act;

    private String username;
    private String password;

    public GetListMessagesTask(ListMessagesActivity act, String username, String password) {
        this.act = act;
        this.username = username;
        this.password = password;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... strings) {

        String result = null;

        URL url = null;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL("http://formation-android-esaip.herokuapp.com/messages/" + username + "/" + password);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            result = InputStreamToString.convert(in);

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
    protected void onPostExecute(String messages) {
        super.onPostExecute(messages);

        act.updateMessages(messages);

    }
}