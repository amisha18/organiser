package com.digitalhomeland.inventorymanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.digitalhomeland.inventorymanager.models.Employee;
import com.digitalhomeland.inventorymanager.models.Team;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class EditTeam extends AppCompatActivity {

    static EditText teamName, manager;
    static Spinner managerSpinner;
    static Button editName, editManger;
    static DatabaseHandler db;
    static Team tl = null;
    private static Volley_Request postRequest;
    Context mContext;
    List<Employee> manList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);
        mContext = EditTeam.this;
        db = new DatabaseHandler(this);
        teamName = (EditText) findViewById(R.id.su_team_name_text);
        manager = (EditText) findViewById(R.id.su_manager_text);
        managerSpinner = (Spinner) findViewById(R.id.su_manager_spinner);
        editName = (Button) findViewById(R.id.teamname_edit_button);
        editManger = (Button) findViewById(R.id.edit_man_button);

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        String team = intent.getStringExtra("teamName");
        if(team != null) {
            tl = db.getTeamByName(team);
        }
        manList = db.getAllManagers();
        if(manList != null) {
            Integer len = manList.size();
            String[] manArr = new String[len];
            for (int i = 0; i < len; i++) {
                manArr[i] = manList.get(i).getFirstName() + " " + manList.get(i).getLastName() + " - " + manList.get(i).getEmployeeId();
            }

            ArrayAdapter<String> manAdapter = new ArrayAdapter<String>
                    (this, R.layout.spinner_second_page,
                            manArr);
            manAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            managerSpinner.setAdapter(manAdapter);
        }
        teamName.setText(tl.getName());
        teamName.setEnabled(false);
        if(!tl.getManagerName().equals("")){
            manager.setText(tl.getManagerId() + " " + tl.getManagerName());
        } else {
            manager.setText("None");
        }

        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editName.getText().equals("Edit")){
                    teamName.setEnabled(true);
                    editName.setText("Save");
                 } else if (editName.getText().equals("Save")){
                    teamName.setEnabled(false);
                    editName.setText("Edit");
                    String response = "{\"reciever\":\"updateTeamName\", \"params\":{\"storeId\":\"" + db.getStore().getStoreId() + "\",\"" + "id\":\"" + tl.getId().toString() + "\",\"" + "oldName\":\"" + tl.getName() + "\",\"" + "newName\":\"" + teamName.getText().toString() + "\"}}";
                    postRequest = new Volley_Request();
                    postRequest.createRequest(mContext, getResources().getString(R.string.mJSONURL_teams), "POST", "updateName",response);
                }
            }
        });

        editManger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editManger.getText().equals("Edit")){
                    manager.setVisibility(View.GONE);
                    managerSpinner.setVisibility(View.VISIBLE);
                    editManger.setText("Save");
                } else if (editManger.getText().equals("Save")){
                    manager.setVisibility(View.VISIBLE);
                    managerSpinner.setVisibility(View.GONE);
                    editManger.setText("Edit");
                    Employee emp  = manList.get(managerSpinner.getSelectedItemPosition());
                    String response = "{\"reciever\":\"updateTeamManager\", \"params\":{\"storeId\":\"" + db.getStore().getStoreId() + "\",\"" + "id\":\"" + tl.getId().toString() + "\",\"" + "managerId\":\"" + emp.getEmployeeId() + "\",\"" + "managerName\":\"" + emp.getFirstName() + " " + emp.getLastName() + "\"}}";
                    postRequest = new Volley_Request();
                    postRequest.createRequest(mContext, getResources().getString(R.string.mJSONURL_teams), "POST", "updateManager",response);
                }
            }
        });
    }

    public static void updateNameResponse(String response){
        try {
            JSONObject responseObj = new JSONObject(response);

            //Store storeRes = new Store(responseObj.getString("_id"),responseObj.getString("storeId"),responseObj.getString("name"),responseObj.getString("city"),responseObj.getString("state"),responseObj.getString("lat"),responseObj.getString("longi"),responseObj.getString("address"),responseObj.getString("empCount"));
            //Team team = new Team(responseObj.getString("_id"), responseObj.getString("name"), responseObj.getString("storeId"), responseObj.getString("city"), responseObj.getString("state"), responseObj.getString("lat"), responseObj.getString("longi"), responseObj.getString("address"), responseObj.getString("empCount"), responseObj.getString("keyActive"), responseObj.getString("sellerId"), responseObj.getString("closingDay"), responseObj.getString("rosterGenDay"), responseObj.getString("adminId"));
            db.updateTeamName(responseObj.getString("_id"), responseObj.getString("name"));
        }catch(JSONException ex){
            Log.d("myTag", "error hua", ex );
        }
        }

    public static void updateManagerResponse(String response){
        try {
            JSONObject responseObj = new JSONObject(response);

            //Store storeRes = new Store(responseObj.getString("_id"),responseObj.getString("storeId"),responseObj.getString("name"),responseObj.getString("city"),responseObj.getString("state"),responseObj.getString("lat"),responseObj.getString("longi"),responseObj.getString("address"),responseObj.getString("empCount"));
            //Team team = new Team(responseObj.getString("_id"), responseObj.getString("name"), responseObj.getString("storeId"), responseObj.getString("city"), responseObj.getString("state"), responseObj.getString("lat"), responseObj.getString("longi"), responseObj.getString("address"), responseObj.getString("empCount"), responseObj.getString("keyActive"), responseObj.getString("sellerId"), responseObj.getString("closingDay"), responseObj.getString("rosterGenDay"), responseObj.getString("adminId"));
            db.addManagerTOTeam(responseObj.getString("_id"), responseObj.getString("managerId"), responseObj.getString("managerName"));
        }catch(JSONException ex){
            Log.d("myTag", "error hua", ex );
        }
    }

}
