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
import pl.edu.agh.student.mprezes.lvoteandroid.activities.VoteResultsActivity;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class VoteResultsListAdapter extends ArrayAdapter<VoteResultsActivity.AnswersContainer> {

    private final Context context;

    private final int layoutRes;
    private final List<VoteResultsActivity.AnswersContainer> answerContainers;

    public VoteResultsListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<VoteResultsActivity.AnswersContainer> objects) {
        super(context, resource, objects);

        this.context = context;
        this.answerContainers = objects;
        this.layoutRes = resource;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        VoteResultsActivity.AnswersContainer answersContainer = getItem(position);

        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layoutRes, parent, false);
            result = convertView;
        } else {
            result = convertView;
        }

        TextView votePosition = (TextView) convertView.findViewById(R.id.vote_result_position);
        votePosition.setText(answersContainer != null ? answersContainer.answer + " : " + answersContainer.votesCount : null);

        return result;
    }
}
