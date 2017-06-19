package pl.edu.agh.student.mprezes.lvoteandroid.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.List;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.floatingactionbutton.FloatingActionButtonController;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.listview.VotingResultListAdapter;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.service.voting.VotingService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.voting.VotingServiceImpl;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new FloatingActionButtonController((FloatingActionButton) findViewById(R.id.fab), this).create();


        new GetResults().execute();
    }

    private void createVotingResultsList(List<Voting> votingsResults) {
        ListView resultsListView = (ListView) findViewById(R.id.voting_results_list);
        resultsListView.setAdapter(new VotingResultListAdapter(this, R.layout.voting_results_list, votingsResults));
    }


    private class GetResults extends AsyncTask<Void, Void, List<Voting>> {

        @Override
        protected List<Voting> doInBackground(Void... params) {
            VotingService votingService = new VotingServiceImpl();
            return votingService.getResults();
        }

        @Override
        protected void onPostExecute(final List<Voting> votings) {
            createVotingResultsList(votings);
        }
    }

}
