package com.digitalhomeland.organizer;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalhomeland.organizer.models.Employee;
import com.digitalhomeland.organizer.models.Team;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DashboardActivity extends AppCompatActivity{

    private static Volley_Request getRequest, postRequest;
    LocationManager locationManager;
    static Location testLoc;
    static Address testAddress;
    static String buttonAction = "";
    static Activity mActivity;
    static Context mContext;
    static DatabaseHandler db = null;
    static String[] dates = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mActivity = DashboardActivity.this;
        mContext=this;
        db = new DatabaseHandler(this);
        //db.deleteAppli();
        //db.deleteTasks();
        //db.deleteEmployees();
        for (Team tl:
                db.getAllTeams()    ) {
            Team td = db.getTeamByName(tl.getName());
            Log.d("myTag","team name  : " + td.getName());
        }
        getWindow().setBackgroundDrawableResource(R.drawable.btr);
        Calendar currCal = Calendar.getInstance();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        Button addTeam = (Button) findViewById(R.id.add_team);
        addTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(mContext);
                View mView = layoutInflaterAndroid.inflate(R.layout.teacher_input_dailog_box, null);
                AlertDialog.Builder alertDialogBuilderTeacherInput = new AlertDialog.Builder(DashboardActivity.this, R.style.AlertDialogTheme);
                alertDialogBuilderTeacherInput.setView(mView);
                TextView removeStudentText = (TextView) mView.findViewById(R.id.dialogTitle);
                removeStudentText.setText("Enter StoreID :");
                final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
                alertDialogBuilderTeacherInput
                        .setCancelable(false)
                        .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                // ToDo get user input here
                                //Log.d("myTag", "dailog input : " + userInputDialogEditText.getText() + " : " +  db.getStore().getStoreId());
                                // if it is equal to schoolId recieved from page 1
                                    String response = "{\"reciever\":\"add\", \"params\":{\"team\":{\"teamName\":\"" + userInputDialogEditText.getText().toString() + "\",\"storeId\":\"" + db.getStore().getStoreId() + "\"}}}";
                                    postRequest = new Volley_Request();
                                    postRequest.createRequest(mContext, getResources().getString(R.string.mJSONURL_teams), "POST", "addTeam",response);
                            }
                        })

                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid = alertDialogBuilderTeacherInput.create();
                alertDialogAndroid.show();

            }
        });



        Button approveEmp = (Button) findViewById(R.id.approve_emp);
        approveEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requestNotif = "{\"reciever\":\"getUnapproved\" ,\"params\": {\"storeId\":\"" + db.getStore().getStoreId() + "\" } }";
                Log.d("myTag", "requestNotif : " + requestNotif);
                postRequest = new Volley_Request();
                postRequest.createRequest(mActivity, mActivity.getResources().getString(R.string.mJSONURL_employee), "POST", "getUnapprovedEmp",requestNotif);
            }
        });

        Button editTeam = (Button) findViewById(R.id.edit_teams);
        editTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mActivity, TeamView.class);
                mActivity.startActivity(i);

                            }
        });

    }

    public static void addTeamResponse(String responseString){
        try {
            JSONObject responseObj = new JSONObject(responseString);

            //Store storeRes = new Store(responseObj.getString("_id"),responseObj.getString("storeId"),responseObj.getString("name"),responseObj.getString("city"),responseObj.getString("state"),responseObj.getString("lat"),responseObj.getString("longi"),responseObj.getString("address"),responseObj.getString("empCount"));
            Team team = new Team(responseObj.getString("_id"), responseObj.getString("teamName"), "","");
            db.addTeam(team);
            new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Great Job!")
                    .setContentText("Team added successfully")
                    .setConfirmText("Congratulations!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();

        }catch(JSONException e){
            Log.d("myTag", "Json exception : ", e);
        }
    }

    public static void getUnapprovedResponse(String response){
        try {
            JSONObject responeObj = new JSONObject(response);
            JSONArray unappEmpArr = responeObj.getJSONArray("employees");
            for (int i = 0; i < unappEmpArr.length(); i++) {
                JSONObject empEle = (JSONObject) unappEmpArr.get(i);
                    Employee notifObj = new Employee(empEle.getString("_id"), empEle.getString("firstName"), empEle.getString("middleName"), empEle.getString("lastName"), empEle.getString("phone"), empEle.getString("email"), empEle.getString("aadharId"), empEle.getString("employeeId"), empEle.getString("isManager"));
                if(!db.checkEmpExists(empEle.getString("employeeId"))) {
                    db.addEmployees(notifObj);
                }
                //showNotificationNotif(notifObj, i * i);
            }
            Intent i = new Intent(mActivity, UnapprovedView.class);
            mActivity.startActivity(i);
        }catch(JSONException ex){
            Log.d("myTag", "Json exception : ", ex);
        }
    }

    public static void requestNotifs(String empid){
        String requestNotif = "{\"reciever\":\"EmpRead\", \"params\":{\"storeId\":\"" + db.getStore().getStoreId() + "\",\"seqId\":\"" + db.getNotifSeq("B") + "\"}}";
        Log.d("myTag", "requestNotif : " + requestNotif);
        postRequest = new Volley_Request();
        postRequest.createRequest(mActivity, mActivity.getResources().getString(R.string.mJSONURL_notifb), "POST", "loadNotifbSync",requestNotif);
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
/*
    public static void requestNotifs(String studentid){
        String requestNotif = "{\"reciever\":\"load\", \"params\":{\"studentid\":\"" + studentid + "\"}}";
        Log.d("myTag", "requestNotif : " + requestNotif);
        postRequest = new Volley_Request();
        postRequest.createRequest(mActivity, mActivity.getResources().getString(R.string.mJSONURL_notif), "POST", "loadNotifsSync",requestNotif);
    }

    public static void getloadNotifResponse(String responseString){
        try {
            Log.d("myTag", "got load notif resp : " + responseString);
            JSONObject responeObj = new JSONObject(responseString);
            JSONArray notifArr = responeObj.getJSONArray("notifs");
            for(int i=0; i< notifArr.length(); i++){
                JSONObject notifEle = (JSONObject) notifArr.get(i);
                Notif notifObj = new Notif(notifEle.getString("_id"),"5916b119aec2b708a0b960e1",notifEle.getString("title"),notifEle.getString("subject"),notifEle.getString("time"), studentId, notifEle.getInt("seqId"));
                db.addNotif(notifObj);
                showNotificationNotif(notifObj ,i*i);
                String applicationRequest = "{\"reciever\":\"studentsync\",\"params\":{\"studentid\":\""+ studentId +"\"}}";
                postRequest = new Volley_Request();
                postRequest.createRequest(mActivity, mActivity.getResources().getString(R.string.mJSONURL_appli), "POST", "SyncActivityStudent",applicationRequest);
            }}
        catch(Exception e){Log.d("myTag", "getloadNotifResponse : " , e);}
    }

*/

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
