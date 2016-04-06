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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_messages);

        // Task récupérant la liste des messages

        GetListMessagesTask p = new GetListMessagesTask(this, 10, 0);
        p.execute();

    }

    /**
     * Met à jour la liste des messages
     * @param messages ArrayList contenant les messages
     */
    public void updateMessages(ArrayList<HashMap<String, String> > messages) {

        ListAdapter adapter = new SimpleAdapter(ListMessagesActivity.this, messages, R.layout.list_item, new String[] {"login", "message"}, new int[] { R.id.name, R.id.message });

        setListAdapter(adapter);
    }



    /**
     * Event listener actualisant la liste des messages
     * @param view
     */
    public void refreshOnClick(View view) {

        GetListMessagesTask p = new GetListMessagesTask(this, 10, 0);
        p.execute();

    }

}
