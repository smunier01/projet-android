package com.example.excilys.projetmessages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    public void listMessagesOnClick(View v) {
        Intent intent = new Intent(this, ListMessagesActivity.class);

        startActivity(intent);
    }

    public void sendMessageOnClick(View v) {
        Intent intent = new Intent(this, SendMessageActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
