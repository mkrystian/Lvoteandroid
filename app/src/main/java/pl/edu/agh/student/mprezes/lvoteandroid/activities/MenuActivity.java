package pl.edu.agh.student.mprezes.lvoteandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.edu.agh.student.mprezes.lvoteandroid.R;
import pl.edu.agh.student.mprezes.lvoteandroid.activities.floatingactionbutton.FloatingActionButtonController;
import pl.edu.agh.student.mprezes.lvoteandroid.model.Account;
import pl.edu.agh.student.mprezes.lvoteandroid.model.ActionCommand;
import pl.edu.agh.student.mprezes.lvoteandroid.service.ContextProvider;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SparseArray<ActionCommand> actionMap;
    private Button availableVotingsButton;
    private Button createVotingButton;
    private Button manageGroupsButton;
    private Button resultsVotingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new FloatingActionButtonController((FloatingActionButton) findViewById(R.id.fab), this).create();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        createActionMap();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        createNavigationHeader();
        createMenuButtons();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (actionMap.get(id) != null)
            actionMap.get(id).execute();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void createActionMap() {
        actionMap = new SparseArray<>();
        actionMap.put(R.id.nav_logout, new ActionCommand() {
            @Override
            public void execute() {
                logout();
            }
        });

    }


    private void createNavigationHeader() {
        Account userAccount = ContextProvider.getApplicationContext().getUserAccount();
        TextView usernameTextView = (TextView) findViewById(R.id.username);
        usernameTextView.setText(userAccount.getUsername());

        TextView nameTextView = (TextView) findViewById(R.id.name);
        nameTextView.setText(String.format("%s %s", userAccount.getFirstName(), userAccount.getLastName()));
    }

    private void createMenuButtons() {
        availableVotingsButton = (Button) findViewById(R.id.list_of_available_votings);
        createVotingButton = (Button) findViewById(R.id.create_new_votings);
        manageGroupsButton = (Button) findViewById(R.id.manage_groups);
        resultsVotingsButton = (Button) findViewById(R.id.results_of_finished_votings);

        availableVotingsButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, VotingsActivity.class));
            }
        });
        createVotingButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        manageGroupsButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ManageGroupsActivity.class));

            }
        });
        resultsVotingsButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, ResultsActivity.class));
            }
        });
    }

    private void logout() {
        ContextProvider.setApplicationContext(null);
        ContextProvider.setConnectionContext(null);
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }


}
