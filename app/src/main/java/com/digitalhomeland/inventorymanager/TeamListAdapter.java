package com.digitalhomeland.inventorymanager;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.digitalhomeland.inventorymanager.models.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 5/3/2018.
 */

public class TeamListAdapter extends BaseAdapter implements ListAdapter {
    private List<Team> teamList  = new ArrayList<>();
    private Context mContext;
    private Context context;
    static DatabaseHandler db = null;

    public TeamListAdapter(Context mContext , List<Team> tlList) {
        this.mContext = mContext;
        this.teamList = tlList;
        db = new DatabaseHandler(mContext);
    }

    @Override
    public int getCount() {
        return teamList.size();
    }

    @Override
    public Object getItem(int position) {
        return teamList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return teamList.get(position).hashCode(); //fix it later
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater =  (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null) {
            view = inflater.inflate(R.layout.unapproved_emp_item, null);

        }
        //Handle TextView and display string from your list
        TextView nameItemText = (TextView) view.findViewById(R.id.student_name_string);
        nameItemText.setText(teamList.get(position).getName() + " | ");
        TextView idItemText = (TextView) view.findViewById(R.id.student_rollno_string);
        if(!teamList.get(position).getManagerName().equals(""))
        {
        idItemText.setText(teamList.get(position).getManagerName());
        } else {
            idItemText.setText("Manager not set");
        }
        TextView typeItemText = (TextView) view.findViewById(R.id.employee_type);
        typeItemText.setText("EmpCount : " + db.getEmployeesCountTeam(teamList.get(position).getName()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Log.d("myTag", "notiflistadapter : " +teamList.get(position).getName() );
                    Intent i = new Intent(context, EditTeam.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("teamName", teamList.get(position).getName());
                    mContext.startActivity(i);
            }
        });
        return view;
    }
}
