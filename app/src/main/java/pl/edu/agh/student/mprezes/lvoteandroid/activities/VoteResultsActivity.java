package pl.edu.agh.student.mprezes.lvoteandroid.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.floatingactionbutton.FloatingActionButtonController;
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

        new FloatingActionButtonController((FloatingActionButton) findViewById(R.id.fab), this).create();



        Bundle bundle = getIntent().getExtras();
        voting = (Voting) bundle.getSerializable("content");

        TextView votingQuestion = (TextView) findViewById(R.id.voting_question);
        votingQuestion.setText(voting.getVotingContent().getQuestion());

        createAnswersList();
    }

    private void createAnswersList() {
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
