package pl.edu.agh.student.mprezes.lvoteandroid.activities.listview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.WaitingVote;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class WaitingListAdapter extends ArrayAdapter<WaitingVote> {

    private final Context context;
    private final List<WaitingVote> waitingVotes;
    private final int layoutRes;
    private final Map<CheckBox, WaitingVote> checkBoxMap = new HashMap<>();


    public WaitingListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<WaitingVote> objects) {
        super(context, resource, objects);

        this.context = context;
        this.waitingVotes = objects;
        this.layoutRes = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        WaitingVote waitingVote = getItem(position);

        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layoutRes, parent, false);

            final CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.check_vote);
            final TextView votingName = (TextView) convertView.findViewById(R.id.voting_name);
            final TextView voteStatus = (TextView) convertView.findViewById(R.id.vote_status);

            votingName.setText(waitingVote != null ? waitingVote.getVotingName() : null);
            voteStatus.setText(waitingVote != null ? waitingVote.getVoteStatues().toString() : null);

            checkBoxMap.put(checkBox, waitingVote);
            result = convertView;
        } else {
            result = convertView;
        }

        return result;
    }

    public boolean isAnySelected() {
        for (Map.Entry<CheckBox, WaitingVote> entry : checkBoxMap.entrySet()) {
            if (entry.getKey().isChecked()) {
                return true;
            }
        }
        return false;
    }

    public Set<WaitingVote> getSelected() {
        Set<WaitingVote> result = new HashSet<>();
        for (Map.Entry<CheckBox, WaitingVote> entry : checkBoxMap.entrySet()) {
            if (entry.getKey().isChecked()) {
                result.add(entry.getValue());
            }
        }
        return result;
    }
}
