package com.example.excilys.projetmessages.activities;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.example.excilys.projetmessages.R;
import com.example.excilys.projetmessages.tasks.GetListMessagesTask;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Activité affichant la liste des messages
 */
public class ListMessagesActivity extends ListActivity {

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

        messagesList = new ArrayList<>();

        // Task récupérant la liste des messages

        GetListMessagesTask p = new GetListMessagesTask(this, username, password);
        p.execute();

    }

    /**
     * Met à jour la liste des messages
     * @param s String contenant les messages
     */
    public void updateMessages(String s) {

        String[] messages = s.split(";");

        for (String message : messages) {

            String[] tmp = message.split(":");

            if (tmp.length < 2) {
                continue;
            }

            HashMap<String, String> msg = new HashMap<>();

            msg.put("name", tmp[0]);
            msg.put("message", tmp[1]);

            messagesList.add(0, msg);
        }


        ListAdapter adapter = new SimpleAdapter(ListMessagesActivity.this, messagesList, R.layout.list_item, new String[] {"name", "message"}, new int[] { R.id.name, R.id.message });

        setListAdapter(adapter);
    }



    /**
     * Event listener actualisant la liste des messages
     * @param view
     */
    public void refreshOnClick(View view) {

        GetListMessagesTask p = new GetListMessagesTask(this, username, password);
        p.execute();

    }

}
