package com.example.excilys.projetmessages;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListMessagesActivity extends ListActivity {

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> messagesList;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_messages);

        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.sharedPrefFile), Context.MODE_PRIVATE);

        username = settings.getString("username", "");
        password = settings.getString("password", "");

        messagesList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        GetListMessagesTask p = new GetListMessagesTask(this, username, password);
        p.execute();

    }

    public void updateMessages(String s) {

        String[] messages = s.split(";");

        for (String message : messages) {

            String[] tmp = message.split(":");

            if (tmp.length < 2) {
                continue;
            }

            HashMap<String, String> msg = new HashMap<String, String>();

            msg.put("name", tmp[0]);
            msg.put("message", tmp[1]);

            messagesList.add(0, msg);
        }


        ListAdapter adapter = new SimpleAdapter(ListMessagesActivity.this, messagesList, R.layout.list_item, new String[] {"name", "message"}, new int[] { R.id.name, R.id.message });

        setListAdapter(adapter);
    }

    public void refreshOnClick(View view) {

        GetListMessagesTask p = new GetListMessagesTask(this, username, password);
        p.execute();

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
