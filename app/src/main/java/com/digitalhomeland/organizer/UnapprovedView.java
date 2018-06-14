package com.digitalhomeland.organizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.digitalhomeland.organizer.models.Employee;

import java.util.List;

/**
 * Created by Asus on 5/2/2018.
 */

public class UnapprovedView extends AppCompatActivity {

    static DatabaseHandler db;
    private static Volley_Request postRequest;
    static Context mContext;
    public static Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unapproved_view);
        getWindow().setBackgroundDrawableResource(R.drawable.btr);
        db = new DatabaseHandler(this);
        mContext = this;
        mActivity = UnapprovedView.this;
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
        List<Employee> unapp = db.getEmployeeUnapproved();
        if(unapp.size() != 0) {
            ApprovalListAdapter adapter = new ApprovalListAdapter(getApplicationContext(), unapp, mActivity);
            listView.setAdapter(adapter);
        } else {
            Intent i = new Intent(mActivity, DashboardActivity.class);
            mActivity.startActivity(i);
        }
    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        Intent i = new Intent(mActivity, DashboardActivity.class);
        mActivity.startActivity(i);
    }

}
