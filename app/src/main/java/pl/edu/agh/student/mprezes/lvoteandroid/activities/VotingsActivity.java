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
import pl.edu.agh.student.mprezes.lvoteandroid.activities.listview.AvailableVotingsListAdapter;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.listview.OwnedVotingsListAdapter;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.service.voting.VotingService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.voting.VotingServiceImpl;

public class VotingsActivity extends AppCompatActivity {

    private FloatingActionButtonController floatingActionButtonController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floatingActionButtonController = new FloatingActionButtonController((FloatingActionButton) findViewById(R.id.fab), this);
        floatingActionButtonController.create();


        reloadData();
    }

    public void reloadData() {
        new GetAvailableVotings().execute();
        new GetOwnedVotings().execute();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        floatingActionButtonController.refresh();
        reloadData();
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
