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
import pl.edu.agh.student.mprezes.lvoteandroid.activities.listview.GroupsBelongsListAdapter;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.listview.YourGroupsListAdapter;
import pl.edu.agh.student.mprezes.lvoteandroid.model.UserGroup;
import pl.edu.agh.student.mprezes.lvoteandroid.service.usergroup.UserGroupService;
import pl.edu.agh.student.mprezes.lvoteandroid.service.usergroup.UserGroupServiceImpl;

public class ManageGroupsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_groups);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new FloatingActionButtonController((FloatingActionButton) findViewById(R.id.fab), this).create();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        new GetUserGroups().execute();
        new GetGroupsUserBelongs().execute();
    }

    private void createYourGroupsList(List<UserGroup> userGroups) {
        ListView yourGroups = (ListView) findViewById(R.id.your_groups);
        yourGroups.setAdapter(new YourGroupsListAdapter(this, R.layout.your_groups, userGroups));
    }

    private void createGroupsUserBelongs(List<UserGroup> groupsUserBelongs) {
        ListView listView = (ListView) findViewById(R.id.groups_you_belong);
        listView.setAdapter(new GroupsBelongsListAdapter(this, R.layout.your_groups, groupsUserBelongs));
    }


    private class GetUserGroups extends AsyncTask<Void, Void, List<UserGroup>> {

        @Override
        protected List<UserGroup> doInBackground(Void... params) {
            UserGroupService userGroupService = new UserGroupServiceImpl();
            return userGroupService.getUserGroups();
        }

        @Override
        protected void onPostExecute(final List<UserGroup> userGroups) {
            createYourGroupsList(userGroups);
        }
    }

    private class GetGroupsUserBelongs extends AsyncTask<Void, Void, List<UserGroup>> {

        @Override
        protected List<UserGroup> doInBackground(Void... params) {
            UserGroupService userGroupService = new UserGroupServiceImpl();
            return userGroupService.getGroupsUserBelong();
        }

        @Override
        protected void onPostExecute(final List<UserGroup> userGroups) {
            createGroupsUserBelongs(userGroups);
        }
    }

}
