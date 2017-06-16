package pl.edu.agh.student.mprezes.lvoteandroid.activities.listview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import pl.edu.agh.student.mprezes.lvoteandroid.activities.VoteResultsActivity;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class VotingResultListAdapter extends ArrayAdapter<Voting> {

    private final Context context;
    private final List<Voting> votings;
    private final int layoutRes;

    public VotingResultListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Voting> objects) {
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
            TextView groupName = (TextView) convertView.findViewById(R.id.voting_name);
            Button showResult = (Button) convertView.findViewById(R.id.voting_results_button);
            showResult.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startVoteResultsActivity(voting);
                }
            });
            groupName.setText(voting != null ? voting.getName() : null);
            result = convertView;
        } else {
            result = convertView;
        }

        return result;
    }

    private void startVoteResultsActivity(Voting voting) {
        Intent intent = new Intent(context, VoteResultsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("content", voting);
        intent.putExtras(bundle);

        context.startActivity(intent);
    }
}
