package com.example.excilys.projetmessages.tasks;

import android.os.AsyncTask;

import com.example.excilys.projetmessages.activities.MainActivity;

/**
 * AsyncTask permettant de s'identifier
 */
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

        RestService.auth(username, password);

        return RestService.connect();

    }

    @Override
    protected void onPostExecute(Boolean o) {
        super.onPostExecute(o);
    }
}
