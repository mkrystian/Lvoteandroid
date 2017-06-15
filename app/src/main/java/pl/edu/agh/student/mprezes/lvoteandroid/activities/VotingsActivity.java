package pl.edu.agh.student.mprezes.lvoteandroid.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.listview.AvailableVotingsListAdapter;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.listview.OwnedVotingsListAdapter;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.service.voting.VotingService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.voting.VotingServiceImpl;

public class VotingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votings);
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

        loadData();
    }

    private void loadData() {
        new GetAvailableVotings().execute();
        new GetOwnedVotings().execute();
    }

    @Override
    public void onRestart() {
        super.onRestart();

        loadData();
    }

    private void createAvailableVotingsList(List<Voting> votings) {
        ListView listView = (ListView) findViewById(R.id.list_of_available_votings);
        listView.setAdapter(new AvailableVotingsListAdapter(this, R.layout.available_votings_list, votings));
    }

    private void createOwnedVotingsList(List<Voting> votings) {
        ListView listView = (ListView) findViewById(R.id.list_of_your_votings);
        listView.setAdapter(new OwnedVotingsListAdapter(this, R.layout.owned_votings_list, votings));
    }

    private class GetAvailableVotings extends AsyncTask<Void, Void, List<Voting>> {

        @Override
        protected List<Voting> doInBackground(Void... params) {
            VotingService votingService = new VotingServiceImpl();
            return votingService.getAllAvailableVotings();
        }

        @Override
        protected void onPostExecute(final List<Voting> votings) {
            createAvailableVotingsList(votings);
        }
    }

    private class GetOwnedVotings extends AsyncTask<Void, Void, List<Voting>> {

        @Override
        protected List<Voting> doInBackground(Void... params) {
            VotingService votingService = new VotingServiceImpl();
            return votingService.getAllOwnedVotings();
        }

        @Override
        protected void onPostExecute(final List<Voting> votings) {
            createOwnedVotingsList(votings);
        }
    }

}
