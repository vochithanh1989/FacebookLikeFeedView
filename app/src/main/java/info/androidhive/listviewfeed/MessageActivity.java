package info.androidhive.listviewfeed;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import info.androidhive.listviewfeed.adapter.SendMessageAdapter;
import info.androidhive.listviewfeed.app.FormatDateTime;
import info.androidhive.listviewfeed.app.Utility;
import info.androidhive.listviewfeed.data.FeedItem;
import info.androidhive.listviewfeed.data.Message;

/**
 * Created by Jason on 7/6/2015.
 */
public class MessageActivity extends ListActivity {
    ArrayList<Message> messages;
    SendMessageAdapter adapter;
    ListView lv;
    EditText text;
    private TextView name;
    private RelativeLayout buttonSend;
    static Random rand = new Random();
    static String sender;
    private View mHeader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_messenger);

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
        //sender = Utility.sender[rand.nextInt( Utility.sender.length-1)];

        Intent intent = getIntent();
        FeedItem item = (FeedItem) intent.getSerializableExtra("Item");
        this.setTitle(R.string.facebook_message);

        buttonSend = (RelativeLayout) findViewById(R.id.realcmtn);
        text = (EditText) this.findViewById(R.id.edtcmtn);

        lv = (ListView) findViewById(android.R.id.list);
        mHeader = getLayoutInflater().inflate(R.layout.header_send_messenger_item, null);
        lv.addHeaderView(mHeader);

        name = (TextView) findViewById(R.id.txttennguoint);
        name.setText(item.getName());

        //Date time current
        final TextView txtDateTime = (TextView) findViewById(R.id.txtTimeUpdate);
        txtDateTime.setText(FormatDateTime.CurrentDateTime());

        //when change text Editex btnSend Visable
        final EditText edtMessenger = (EditText) findViewById(R.id.edtcmtn);
        edtMessenger.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buttonSend.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        messages = new ArrayList<Message>();
        messages.add(new Message("Hello", false));
        messages.add(new Message("Hi!", true));
        messages.add(new Message("Wassup??", false));
        messages.add(new Message("nothing much, working on speech bubbles.", true));
        messages.add(new Message("you say!", true));
        messages.add(new Message("oh thats great. how are you showing them", false));


        adapter = new SendMessageAdapter(this, messages);
        setListAdapter(adapter);
        addNewMessage(new Message("mmm, well, using 9 patches png to show them.", true));
    }

    public void sendMessage(View v) {
        String newMessage = text.getText().toString().trim();
        if (newMessage.length() > 0) {
            text.setText("");
            addNewMessage(new Message(newMessage, true));
            //buttonSend = (RelativeLayout) findViewById(R.id.realcmtn);
            buttonSend.setVisibility(View.GONE);
            new SendMessage().execute();
        }
    }

    private class SendMessage extends AsyncTask<Void, String, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(1000); //simulate a network call
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            this.publishProgress(String.format("%s started writing", sender));
//            try {
//                Thread.sleep(2000); //simulate a network call
//            }catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            this.publishProgress(String.format("", sender));
            try {
                Thread.sleep(1000);//simulate a network call
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Utility.messages[rand.nextInt(Utility.messages.length - 1)];
        }

        @Override
        public void onProgressUpdate(String... v) {

            if (messages.get(messages.size() - 1).isStatusMessage)//check wether we have already added a status message
            {
                messages.get(messages.size() - 1).setMessage(v[0]); //update the status for that
                adapter.notifyDataSetChanged();
                getListView().setSelection(messages.size() - 1);
            } else {
                addNewMessage(new Message(true, v[0])); //add new message, if there is no existing status message
            }
        }

        @Override
        protected void onPostExecute(String text) {
            if (messages.get(messages.size() - 1).isStatusMessage)//check if there is any status message, now remove it.
            {
                messages.remove(messages.size() - 1);
            }

            addNewMessage(new Message(text, false)); // add the orignal message from server.
        }
    }

    void addNewMessage(Message m) {
        messages.add(m);
        adapter.notifyDataSetChanged();
        getListView().setSelection(messages.size() - 1);
    }

}