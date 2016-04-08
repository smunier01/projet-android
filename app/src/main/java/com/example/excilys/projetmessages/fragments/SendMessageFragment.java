package com.example.excilys.projetmessages.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.excilys.projetmessages.R;
import com.example.excilys.projetmessages.activities.ListMessagesActivity;
import com.example.excilys.projetmessages.tasks.SendMessageTask;


public class SendMessageFragment extends Fragment {

    private View view;

    private String username;
    private String password;

    private EditText messageEditText;
    private FloatingActionButton sendButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_send_message, container, false);

        SharedPreferences settings = this.getActivity().getSharedPreferences(getResources().getString(R.string.sharedPrefFile), Context.MODE_PRIVATE);

        username = settings.getString("username", "");
        password = settings.getString("password", "");
        messageEditText = (EditText) view.findViewById(R.id.editTextMessage);
        sendButton = (FloatingActionButton) view.findViewById(R.id.sendMessageButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessageTask p = new SendMessageTask(null, username, password, messageEditText.getText().toString());
                p.execute();
                ((ListMessagesActivity)getActivity()).refreshOnClick(v);
            }
        });

        return view;
    }

}
