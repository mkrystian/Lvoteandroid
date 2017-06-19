package pl.edu.agh.student.mprezes.lvoteandroid.activities.listview;

import android.os.AsyncTask;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.VotingsActivity;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;
import pl.edu.agh.student.mprezes.lvoteandroid.service.voting.VotingServiceImpl;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class OwnedVotingsListAdapter extends ArrayAdapter<Voting> {

    private final VotingsActivity context;
    private final List<Voting> votings;
    private final int layoutRes;

    public OwnedVotingsListAdapter(@NonNull VotingsActivity context, @LayoutRes int resource, @NonNull List<Voting> objects) {
        super(context, resource, objects);

        this.context = context;
        this.votings = objects;
        this.layoutRes = resource;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        final Voting voting = getItem(position);

        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layoutRes, parent, false);
            result = convertView;
            TextView groupName = (TextView) convertView.findViewById(R.id.voting_name);
            Button removeButton = (Button) convertView.findViewById(R.id.voting_remove_button);
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new RemoveVotingTask(voting.getId()).execute();
                }
            });
            groupName.setText(voting != null ? voting.getName() : null);
        } else {
            result = convertView;
        }



        return result;
    }

    private class RemoveVotingTask extends AsyncTask<Void, Void, Void> {

        private Long votingId;

        public RemoveVotingTask(Long id) {
            votingId = id;
        }

        @Override
        protected Void doInBackground(Void... params) {
            new VotingServiceImpl().delete(votingId);
            return null;
        }

        @Override
        protected void onPostExecute(Void voidObject) {
            context.reloadData();

        }
    }
}
