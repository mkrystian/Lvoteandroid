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
import pl.edu.agh.student.mprezes.lvoteandroid.model.UserGroup;

/**
 * @author Krystian Majewski
 * @since 10.06.2017
 */

public class YourGroupsListAdapter extends ArrayAdapter<UserGroup> {

    private final Context context;
    private final List<UserGroup> userGroups;
    private final int layoutRes;

    public YourGroupsListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<UserGroup> objects) {
        super(context, resource, objects);

        this.context = context;
        this.userGroups = objects;
        this.layoutRes = resource;
    }

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        UserGroup userGroup = getItem(position);

        final View result;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(layoutRes, parent, false);
            result = convertView;
        } else {
            result = convertView;
        }

        TextView groupName = (TextView) convertView.findViewById(R.id.group_name);
        groupName.setText(userGroup.getName());

        return result;
    }
}
