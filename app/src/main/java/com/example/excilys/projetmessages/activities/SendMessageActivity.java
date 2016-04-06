package com.example.excilys.projetmessages.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.excilys.projetmessages.R;
import com.example.excilys.projetmessages.tasks.SendMessageTask;

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

    /**
     * Envoi le message
     * @param view
     */
    public void sendMessageOnClick(View view) {

        SendMessageTask p = new SendMessageTask(this, username, password, messageEditText.getText().toString());
        p.execute();

    }


    public void messageSent() {
        finish();
    }
}
