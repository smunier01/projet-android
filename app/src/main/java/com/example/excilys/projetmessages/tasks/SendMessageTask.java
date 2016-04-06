package com.example.excilys.projetmessages.tasks;

import android.os.AsyncTask;

import com.example.excilys.projetmessages.activities.SendMessageActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * AsyncTask permettant d'envoyer un message
 */
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

        RestService.sendMessage(username, message);

        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        super.onPostExecute(v);
        act.messageSent();
    }
}