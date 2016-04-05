package com.example.excilys.projetmessages;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

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

    public void clearOnClick(View v) {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void sendOnClick(View v) throws ExecutionException {
        Toast.makeText(getApplicationContext(), "Toast !", Toast.LENGTH_SHORT).show();

        LogTask p = new LogTask(this);
        p.execute();
        boolean result = false;

        try {

            result = p.get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

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
