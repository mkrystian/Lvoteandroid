package pl.edu.agh.student.mprezes.lvoteandroid.activities;

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

public class VoteActivity extends AppCompatActivity {

    private Voting voting;
    private RadioButtonsListAdapter radioButtonsListAdapter;

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
        } else {
            errorText.setVisibility(View.GONE);
        }
    }

    private void createVotingAnswersList() {
        ListView answerList = (ListView) findViewById(R.id.voting_answers);
        radioButtonsListAdapter = new RadioButtonsListAdapter(this, R.layout.answers_list, new ArrayList<>(voting.getVotingContent().getAnswers()));
        answerList.setAdapter(radioButtonsListAdapter);
    }

}
