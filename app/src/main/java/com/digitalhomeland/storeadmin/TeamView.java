package com.digitalhomeland.storeadmin;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.digitalhomeland.storeadmin.models.Employee;
import com.digitalhomeland.storeadmin.models.Team;

import java.util.List;

public class TeamView extends AppCompatActivity {

    static DatabaseHandler db;
    private static Volley_Request postRequest;
    static Context mContext;
    public static Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_view);
        getWindow().setBackgroundDrawableResource(R.drawable.btr);
        db = new DatabaseHandler(this);
        mContext = this;
        mActivity = TeamView.this;
        setListView(db);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_refresh:
                Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }
        return true;
    }

    public void setListView(DatabaseHandler db){
        ListView listView = (ListView)findViewById(R.id.taskd_list_view);
        List<Team> team = db.getAllTeams();
        if(team.size() != 0) {
            TeamListAdapter adapter = new TeamListAdapter(getApplicationContext(), team);
            listView.setAdapter(adapter);
        }
    }


}
