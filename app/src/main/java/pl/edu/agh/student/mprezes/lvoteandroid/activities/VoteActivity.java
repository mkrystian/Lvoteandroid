package pl.edu.agh.student.mprezes.lvoteandroid.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    private void createVotingAnswersList() {
        ListView answerList = (ListView) findViewById(R.id.voting_answers);
        radioButtonsListAdapter = new RadioButtonsListAdapter(this, R.layout.answers_list, new ArrayList<>(voting.getVotingContent().getAnswers()));
        answerList.setAdapter(radioButtonsListAdapter);
    }

    private class VoteTask extends AsyncTask<Void, Void, Boolean> {

        private VotingAnswer answer;

        private VoteTask(VotingAnswer answer) {
            this.answer = answer;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            VoteService service = new VoteServiceImpl();
            return service.vote(voting, answer);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            voteTask = null;
        }

        @Override
        protected void onCancelled() {
            voteTask = null;
        }
    }

}
