package pl.edu.agh.student.mprezes.lvoteandroid.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.listview.RadioButtonsListAdapter;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingAnswer;
import pl.edu.agh.student.mprezes.lvoteandroid.service.voting.VoteService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.voting.VoteServiceImpl;

public class VoteActivity extends AppCompatActivity {

    private Voting voting;
    private RadioButtonsListAdapter radioButtonsListAdapter;
    private VoteTask voteTask;
    private RadioGroup votingType;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getPreferences(MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle bundle = getIntent().getExtras();
        voting = (Voting) bundle.getSerializable("content");

        TextView votingTitle = (TextView) findViewById(R.id.voting_title);
        votingTitle.setText(voting.getName());
        TextView votingQuestion = (TextView) findViewById(R.id.voting_question);
        votingQuestion.setText(voting.getVotingContent().getQuestion());

        votingType = (RadioGroup) findViewById(R.id.voting_type_radio_group);


        createVotingAnswersList();

        Button voteButton = (Button) findViewById(R.id.vote_button);
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote();
            }
        });
    }

    private void vote() {
        TextView errorText = (TextView) findViewById(R.id.vote_error_text);
        if (!radioButtonsListAdapter.isAnySelected()) {
            errorText.setVisibility(View.VISIBLE);
            return;
        }

        errorText.setVisibility(View.INVISIBLE);

        if (voteTask == null) {
            voteTask = new VoteTask(radioButtonsListAdapter.getSelected());
            voteTask.execute();
        }

    }

    private void showAnswerAndClose(Boolean result) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.result_label));
        if (result) {
            alertDialog.setMessage(getString(R.string.vote_submitted));

        } else {
            alertDialog.setMessage(getString(R.string.vote_rejected));

        }
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        setResult(0);
                        finish();
                    }
                });
        alertDialog.show();

    }

    private VotingMethod getVotingMethod() {
        return VotingMethod.getVotingMethodById(votingType.getCheckedRadioButtonId());
    }

    private void createVotingAnswersList() {
        ListView answerList = (ListView) findViewById(R.id.voting_answers_list);
        radioButtonsListAdapter = new RadioButtonsListAdapter(this, R.layout.answers_list, new ArrayList<>(voting.getVotingContent().getAnswers()));
        answerList.setAdapter(radioButtonsListAdapter);
    }

    private class VoteTask extends AsyncTask<Void, Void, Boolean> {

        private VotingAnswer answer;

        private VoteTask(VotingAnswer answer) {
            this.answer = answer;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(VoteActivity.this, "Connecting to server",
                    "Please wait", true);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            VoteService service = new VoteServiceImpl();

            switch (getVotingMethod()) {
                case VOTE:
                    return service.vote(voting, answer, false);
                case VOTE_PROXY:
                    return service.vote(voting, answer, true);
                case ADD_TO_WAITING_LIST:
                    return service.addToWaitingList(voting, answer);
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            voteTask = null;
            showAnswerAndClose(result);
            progressDialog.dismiss();
        }

    }

    private enum VotingMethod {
        VOTE(R.id.send_vote_option),
        VOTE_PROXY(R.id.use_proxy_option),
        ADD_TO_WAITING_LIST(R.id.add_to_waiting_list_option);

        private final static SparseArray<VotingMethod> votingTypeMap = new SparseArray<>();
        private final int id;

        static {
            for (VotingMethod value : values()) {
                votingTypeMap.put(value.id, value);
            }
        }

        VotingMethod(int radioButtonId) {
            this.id = radioButtonId;
        }

        public static VotingMethod getVotingMethodById(int id) {
            return votingTypeMap.get(id);
        }

        ;
    }

}
