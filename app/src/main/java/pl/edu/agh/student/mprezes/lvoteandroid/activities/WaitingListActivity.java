package pl.edu.agh.student.mprezes.lvoteandroid.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.listview.WaitingListAdapter;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.WaitingVote;
import pl.edu.agh.student.mprezes.lvoteandroid.service.WaitingService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.WaitingServiceImpl;
import pl.edu.agh.student.mprezes.lvoteandroid.service.voting.VoteServiceImpl;

public class WaitingListActivity extends AppCompatActivity {

    private WaitingService service = new WaitingServiceImpl();
    private WaitingListAdapter waitingListAdapter;
    private CheckBox useProxyCheckbox;
    private SendVoteTask sendVoteTask;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        useProxyCheckbox = (CheckBox) findViewById(R.id.use_proxy_option);
        createWaitingList();
        createSendButton();
        createDeleteButton();

    }

    private void createDeleteButton() {
        Button deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!waitingListAdapter.isAnySelected()) {
                    Snackbar.make(view, R.string.no_votings_selected, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    service.removeVotes(waitingListAdapter.getSelected());
                    WaitingListActivity.this.recreate();
                }
            }
        });
    }

    private void createSendButton() {
        Button sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!waitingListAdapter.isAnySelected()) {
                    Snackbar.make(view, R.string.no_votings_selected, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    if (sendVoteTask == null) {
                        sendVoteTask = new SendVoteTask();
                        sendVoteTask.execute();
                    }
                }
            }
        });
    }


    private boolean useProxy() {
        return useProxyCheckbox.isChecked();
    }


    private void createWaitingList() {
        ListView waitingList = (ListView) findViewById(R.id.waiting_list);
        waitingListAdapter = new WaitingListAdapter(this, R.layout.waitings_list, new ArrayList<>(service.getVotes()));
        waitingList.setAdapter(waitingListAdapter);
    }

    private class SendVoteTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(WaitingListActivity.this, "Connecting to server",
                    "Please wait", true);
        }

        @Override
        protected Void doInBackground(Void... params) {
            VoteServiceImpl voteService = new VoteServiceImpl();
            for (WaitingVote waitingVote : waitingListAdapter.getSelected()) {
                voteService.sendWaitingVote(waitingVote, useProxy());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void voidObject) {
            service.updateVotes();
            WaitingListActivity.this.recreate();
            sendVoteTask = null;
            progressDialog.dismiss();
        }

    }

}
