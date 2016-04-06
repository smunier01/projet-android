package com.example.excilys.projetmessages.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.excilys.projetmessages.R;
import com.example.excilys.projetmessages.tasks.LogTask;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.concurrent.ExecutionException;

/**
 * Activité permettant de s'identifier
 */
public class MainActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;

    public EditText getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(EditText passwordField) {
        this.passwordField = passwordField;
    }

    public EditText getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(EditText usernameField) {
        this.usernameField = usernameField;
    }

    /**
     * Quand on click sur le bouton "clear"
     *
     * @param v
     */
    public void clearOnClick(View v) {
        usernameField.setText("");
        passwordField.setText("");
    }

    /**
     * Quand on click sur le bouton "envoyer" pour s'identifier
     *
     * @param v
     */
    public void sendOnClick(View v) {
        Toast.makeText(getApplicationContext(), "Toast !", Toast.LENGTH_SHORT).show();

        LogTask p = new LogTask(this);
        p.execute();
        boolean result = false;

        try {

            result = p.get();

        } catch (ExecutionException | InterruptedException e) {

            e.printStackTrace();

        }

        // Si les informations sont correctes, ont enregistre username/password dans les SharedPreferences
        // et on envoit vers le menu
        if (result) {

            SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.sharedPrefFile), Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = settings.edit();
            editor.putString("username", usernameField.getText().toString());
            editor.putString("password", passwordField.getText().toString());

            editor.commit();

            Intent intent = new Intent(this, MenuActivity.class);

            startActivity(intent);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = (EditText) findViewById(R.id.usernameEditText);
        passwordField = (EditText) findViewById(R.id.passwordEditText);

        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.sharedPrefFile), Context.MODE_PRIVATE);
        String username = settings.getString("username", "");
        String password = settings.getString("password", "");

        usernameField.setText(username);
        passwordField.setText(password);

    }
}
