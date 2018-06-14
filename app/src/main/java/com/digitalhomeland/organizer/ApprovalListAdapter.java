package com.digitalhomeland.organizer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.digitalhomeland.organizer.models.Employee;
import com.digitalhomeland.organizer.models.Team;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Asus on 5/2/2018.
 */

public class ApprovalListAdapter  extends BaseAdapter implements ListAdapter {
    private List<Employee> empList  = new ArrayList<>();
    private Context mContext;
    private Context context;
    static DatabaseHandler db = null;
    static Activity mActivity;

    public ApprovalListAdapter(Context mContext , List<Employee> unappList, Activity activity) {
        this.mContext = mContext;
        this.empList = unappList;
        db = new DatabaseHandler(mContext);
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return empList.size();
    }

    @Override
    public Object getItem(int position) {
        return empList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return empList.get(position).hashCode(); //fix it later
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
        nameItemText.setText(empList.get(position).getFirstName() + " " + empList.get(position).getLastName() + " | ");
        TextView idItemText = (TextView) view.findViewById(R.id.student_rollno_string);
        idItemText.setText(empList.get(position).getEmployeeId());
        TextView typeItemText = (TextView) view.findViewById(R.id.employee_type);
        typeItemText.setText(empList.get(position).getIsManager());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Log.d("myTag", "notiflistadapter : " +empList.get(position).getEmployeeId() );
                Integer count = 0;
                ArrayList<Team> teams = db.getAllTeams();
                for (Team tl:
                     teams) {
                    if(!tl.getManagerId().equals("")){
                        count++;
                    }
                }
                if(empList.get(position).getIsManager().equals("true")) {
                    Intent i = new Intent(context, EmployeeApproval.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("employeeId", empList.get(position).getEmployeeId());
                    mContext.startActivity(i);
                } else if( empList.get(position).getIsManager().equals("false") && count == teams.size() ){
                    Intent i = new Intent(context, EmployeeApproval.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("employeeId", empList.get(position).getEmployeeId());
                    mContext.startActivity(i);
                } else if(empList.get(position).getIsManager().equals("false") && count != teams.size()){
                    new SweetAlertDialog(mActivity, SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Teams Unassigned Error")
                            .setContentText("Please add all team managers first")
                            .setConfirmText("Ok!")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
            }
        });
        return view;
    }
}
