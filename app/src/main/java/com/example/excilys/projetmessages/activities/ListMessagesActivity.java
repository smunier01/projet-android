package com.example.excilys.projetmessages.activities;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.excilys.projetmessages.R;
import com.example.excilys.projetmessages.tasks.GetListMessagesTask;
import com.example.excilys.projetmessages.utils.EndlessScrollListener;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Activity displaying the list of messages
 */
public class ListMessagesActivity extends ListActivity {

    private String username;
    private MyAdapter adapter;
    private ArrayList<HashMap<String, String> > messages;
    private Socket mSocket;
    private Activity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_messages);

        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.sharedPrefFile), Context.MODE_PRIVATE);

        username = settings.getString("username", "");

        ListView lvItems = getListView();

        final ListMessagesActivity context = this;

        lvItems.setOnScrollListener(new EndlessScrollListener(1) {

            @Override
            public void loadMore(int page, int totalItemsCount) {
                GetListMessagesTask p = new GetListMessagesTask(context, 10, 10 * (page-1));
                p.execute();
            }

        });

        // Task to get the list of messages

        GetListMessagesTask p = new GetListMessagesTask(this, 10, 0);
        p.execute();

        //

        this.act = this;

        //

        /*
        try {
            mSocket = IO.socket("https://training.loicortola.com/chat-rest/2.0/ws");
        } catch (URISyntaxException e) {
            System.out.println(e);
        }

        mSocket.on("auth_success", onNewMessage);
        mSocket.on("auth_failed", onAuthFailed);
        mSocket.on("auth_required", onAuthRequired);
        mSocket.connect();
        */
    }

    /*
    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];

                    System.out.println(data.toString());

                }
            });
        }
    };

    private Emitter.Listener onAuthRequired = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];

                    System.out.println("auth required");

                }
            });
        }
    };

    private Emitter.Listener onAuthFailed = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            act.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];

                    System.out.println(data.toString());

                }
            });
        }
    };
    */
    /**
     * Update list of messages
     * @param messages ArrayList containing the messages
     */
    public void updateMessages(ArrayList<HashMap<String, String> > messages) {

        if (adapter == null) {

            this.messages = messages;

            adapter = new MyAdapter(messages);

            setListAdapter(adapter);

        } else {

            this.messages.addAll(messages);

            adapter.notifyDataSetChanged();

        }

    }


    /**
     * Event listener updating the list of messages
     * @param view
     */
    public void refreshOnClick(View view) {

        this.messages.clear();
        GetListMessagesTask p = new GetListMessagesTask(this, 10, 0);
        p.execute();

    }

    class MyAdapter extends BaseAdapter {

        private ArrayList<HashMap<String, String> > messages;

        public MyAdapter(ArrayList<HashMap<String, String> > messages) {

            this.messages = messages;
        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            int ressource;

            String loginStr = messages.get(position).get("login");
            String messageStr = messages.get(position).get("message");

            if (username.equals(loginStr)) {
                ressource = R.layout.list_item;
            } else {
                ressource = R.layout.list_item2;
            }

            LayoutInflater inflater = getLayoutInflater();

            View row = inflater.inflate(ressource, parent, false);
            TextView login, message;

            login = (TextView) row.findViewById(R.id.name);
            message = (TextView) row.findViewById(R.id.message);

            login.setText(loginStr);
            message.setText(messageStr);

            return (row);
        }

    }

}
