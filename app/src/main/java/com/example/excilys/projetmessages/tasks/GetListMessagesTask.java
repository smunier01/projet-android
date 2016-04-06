package com.example.excilys.projetmessages.tasks;

import android.os.AsyncTask;

import com.example.excilys.projetmessages.mappers.InputStreamToString;
import com.example.excilys.projetmessages.activities.ListMessagesActivity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * AsyncTask récupérant les messages
 */
public class GetListMessagesTask extends AsyncTask<String, Void, ArrayList<HashMap<String, String> >> {

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
    protected ArrayList<HashMap<String, String> > doInBackground(String... strings) {

        String result = null;

        return RestService.getMessages();

        /*
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

        */
    }

    @Override
    protected void onPostExecute(ArrayList<HashMap<String, String> > messages) {
        super.onPostExecute(messages);

        act.updateMessages(messages);

    }
}