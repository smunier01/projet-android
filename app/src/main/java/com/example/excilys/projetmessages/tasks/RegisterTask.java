package com.example.excilys.projetmessages.tasks;

import android.os.AsyncTask;

import com.example.excilys.projetmessages.activities.MainActivity;

/**
 * AsyncTask to register user
 */
public class RegisterTask extends AsyncTask<String, Void, Boolean> {

    private MainActivity act;

    private String username;
    private String password;

    public RegisterTask(MainActivity act, String username, String password) {
        this.act = act;
        this.username = username;
        this.password = password;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Boolean doInBackground(String... strings) {

        RestService.register(username, password);

        return true;
    }

    @Override
    protected void onPostExecute(Boolean o) {
        super.onPostExecute(o);
    }
}
