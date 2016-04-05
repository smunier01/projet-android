package com.example.excilys.projetmessages;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class SendMessageActivity extends AppCompatActivity {

    private String username;
    private String password;
    private EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.sharedPrefFile), Context.MODE_PRIVATE);

        username = settings.getString("username", "");
        password = settings.getString("password", "");

        messageEditText = (EditText) findViewById(R.id.editTextMessage);

    }

    public void sendMessageOnClick(View view) {

        SendMessageTask p = new SendMessageTask(this, username, password, messageEditText.getText().toString());
        p.execute();

    }

    public void taskOK() {
        finish();
    }
}
