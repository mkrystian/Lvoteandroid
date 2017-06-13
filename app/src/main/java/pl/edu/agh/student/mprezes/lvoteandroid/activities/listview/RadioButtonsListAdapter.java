package pl.edu.agh.student.mprezes.lvoteandroid.activities.listview;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.model.voting.VotingAnswer;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class RadioButtonsListAdapter extends ArrayAdapter<VotingAnswer> {

    private final Context context;
    private final List<VotingAnswer> votingAnswers;
    private final int layoutRes;
    private final Map<RadioButton, VotingAnswer> buttonsMap = new HashMap<>();


    public RadioButtonsListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<VotingAnswer> objects) {
        super(context, resource, objects);

        this.context = context;
        this.votingAnswers = objects;
        this.layoutRes = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        VotingAnswer votingAnswer = getItem(position);

        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layoutRes, parent, false);

            final RadioButton radioButton = (RadioButton) convertView.findViewById(R.id.answer_button);
            radioButton.setText(votingAnswer != null ? votingAnswer.getAnswer() : null);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    unselectOther(radioButton);
                }
            });
            buttonsMap.put(radioButton, votingAnswer);
            convertView.setTag(radioButton);

            result = convertView;
        } else {
            result = convertView;
        }

        return result;
    }

    public boolean isAnySelected() {
        for (Map.Entry<RadioButton, VotingAnswer> entry : buttonsMap.entrySet()) {
            if (entry.getKey().isChecked()) {
                return true;
            }
        }
        return false;
    }

    public VotingAnswer getSelected() {
        for (Map.Entry<RadioButton, VotingAnswer> entry : buttonsMap.entrySet()) {
            if (!entry.getKey().isChecked()) {
                return entry.getValue();
            }
        }
        return null;
    }

    private void unselectOther(RadioButton radioButton) {
        for (Map.Entry<RadioButton, VotingAnswer> entry : buttonsMap.entrySet()) {
            if (!entry.getKey().equals(radioButton)) {
                entry.getKey().setChecked(false);
            }
        }
    }
}
