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
public class GetListMessagesTask extends AsyncTask<Void, Void, ArrayList<HashMap<String, String> >> {

    private ListMessagesActivity act;

    private int limit;
    private int offset;

    public GetListMessagesTask(ListMessagesActivity act, int limit, int offset) {
        this.act = act;
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    protected void onPreExecute() {

    }


    @Override
    protected ArrayList<HashMap<String, String> > doInBackground(Void... params) {

        return RestService.getMessages(limit, offset);

    }

    @Override
    protected void onPostExecute(ArrayList<HashMap<String, String> > messages) {
        super.onPostExecute(messages);

        act.updateMessages(messages);

    }
}