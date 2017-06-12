package pl.edu.agh.student.mprezes.lvoteandroid.activities.listview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.Voting;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class AvailableVotingsListAdapter extends ArrayAdapter<Voting> {

    private final Context context;
    private final List<Voting> votings;
    private final int layoutRes;

    public AvailableVotingsListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Voting> objects) {
        super(context, resource, objects);

        this.context = context;
        this.votings = objects;
        this.layoutRes = resource;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        Voting voting = getItem(position);

        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layoutRes, parent, false);
            result = convertView;
        } else {
            result = convertView;
        }

        TextView groupName = (TextView) convertView.findViewById(R.id.voting_name);
        groupName.setText(voting != null ? voting.getName() : null);

        return result;
    }
}
