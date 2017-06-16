package pl.edu.agh.student.mprezes.lvoteandroid.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.listview.VoteResultsListAdapter;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Vote;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingAnswer;

public class VoteResultsActivity extends AppCompatActivity {

    private Voting voting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_results);
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

        List<AnswersContainer> answersContainers = new ArrayList<>();
        for (VotingAnswer answer : voting.getVotingContent().getAnswers()) {
            AnswersContainer answerContainer = new AnswersContainer();
            answerContainer.answer = answer.getAnswer();
            answersContainers.add(answerContainer);

            for (Vote vote : voting.getVotes()) {
                if (Objects.equals(vote.getAnswerId(), answer.getId())) {
                    answerContainer.votesCount++;
                }
            }
        }


        ListView voteResultsList = (ListView) findViewById(R.id.vote_result_list);
        voteResultsList.setAdapter(new VoteResultsListAdapter(this, R.layout.vote_results_list, answersContainers));
    }

    public static class AnswersContainer {
        public String answer;
        public int votesCount;

    }

}
