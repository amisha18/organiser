package com.digitalhomeland.inventorymanager;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.digitalhomeland.inventorymanager.models.Employee;
import com.digitalhomeland.inventorymanager.models.Team;
import com.digitalhomeland.inventorymanager.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class EmployeeApproval extends AppCompatActivity {

    //String storeId = "";
    static TextView firstName, middleName, lastName, phone, email, aadharId, employeeId, teamName, designation;
    Spinner teamSpinner;
    Context mContext;
    String response = "{\"reciever\":\"add\", \"params\": { \"employee\" :{";
    static DatabaseHandler db;
    private static Volley_Request postRequest;
    static User user;
    static Activity mActivity;
    static String  storeKey,employeeIdStr= "";
    static Employee empr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_approval);
        getWindow().setBackgroundDrawableResource(R.drawable.btr);
        mContext = getApplicationContext();
        mActivity = EmployeeApproval.this;
        db = new DatabaseHandler(this);

        firstName = (TextView) findViewById(R.id.su_first_name);
        middleName = (TextView) findViewById(R.id.su_middle_name);
        lastName = (TextView) findViewById(R.id.su_last_name);
        phone = (TextView) findViewById(R.id.su_phone);
        email = (TextView) findViewById(R.id.su_email);
        aadharId = (TextView) findViewById(R.id.su_aadhar_id);
        employeeId = (TextView) findViewById(R.id.su_employee_id);
        teamName = (TextView) findViewById(R.id.su_team);
        designation = (TextView) findViewById(R.id.su_designation);

        Bundle bundle = getIntent().getExtras();
        employeeIdStr = bundle.getString("employeeId");
        empr = db.getEmployeeById(employeeIdStr);
        setViews(empr);

        ArrayList<Team> tmList = db.getAllTeams();
        String [] teamNames = new String[tmList.size()];
        for(int i =0; i<tmList.size() ; i++){
            teamNames[i] = tmList.get(i).getName();
        }
        teamSpinner = (Spinner) findViewById(R.id.su_team_spinner);

        //ArrayAdapter<CharSequence> closingDayAdapter = ArrayAdapter.(this, daysOfW, R.layout.spinner_second_page);
        ArrayAdapter<String> teamAdapter = new ArrayAdapter<String>
                (this, R.layout.spinner_second_page,
                        teamNames);
        teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamSpinner.setAdapter(teamAdapter);

        Button approvalBtn =(Button) findViewById(R.id.approve_button);
        approvalBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                validateInput(firstName, lastName, phone, email, aadharId, employeeId, designation);
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(mContext);
                View mView = layoutInflaterAndroid.inflate(R.layout.teacher_input_dailog_box, null);
                AlertDialog.Builder alertDialogBuilderTeacherInput = new AlertDialog.Builder(EmployeeApproval.this, R.style.AlertDialogTheme);
                alertDialogBuilderTeacherInput.setView(mView);
                TextView removeStudentText = (TextView) mView.findViewById(R.id.dialogTitle);
                removeStudentText.setText("Enter StoreID :");
                final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
                alertDialogBuilderTeacherInput
                        .setCancelable(false)
                        .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                // ToDo get user input here
                                Log.d("myTag", "dailog input : " + userInputDialogEditText.getText() + " : " +  db.getStore().getStoreId());
                                // if it is equal to schoolId recieved from page 1
                                if(userInputDialogEditText.getText().toString().equals(db.getStore().getAdminId())) {  //fix later
                                    // setPreferences();
                                    String request;
                                    if(empr.getIsManager().equals("true")) {
                                        request = "{\"reciever\":\"empApprove\", \"params\":{\"employeeId\":\"" + employeeIdStr + "\",\"storeId\":\"" + db.getStore().getStoreId() + "\",\"designation\":\"" + designation.getText().toString() + " \",\"teamname\":\"" + teamSpinner.getSelectedItem().toString() + " \",\"managerId\":\"" + "SELF" + "\"}}";
                                    }else {
                                        request = "{\"reciever\":\"empApprove\", \"params\":{\"employeeId\":\"" + employeeIdStr + "\",\"storeId\":\"" + db.getStore().getStoreId() + "\",\"designation\":\"" + designation.getText().toString() + " \",\"teamname\":\"" + teamSpinner.getSelectedItem().toString() + " \",\"managerId\":\"" + db.getTeamByName(teamSpinner.getSelectedItem().toString()).getManagerId() + "\"}}";
                                          }
                                    Log.d("myTag", "adding response : " + request);
                                    postRequest = new Volley_Request();
                                    postRequest.createRequest(mContext, getResources().getString(R.string.mJSONURL_employee), "POST", "ApproveEmployee", request);

                                }
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
    }

    public static void setViews(Employee emp){
        firstName.setText(emp.getFirstName());
        //set them un editable
        middleName.setText(emp.getMiddleName());
        lastName.setText(emp.getLastName());
        phone.setText(emp.getPhone());
        email.setText(emp.getEmail());
        aadharId.setText(emp.getAadharId());
        employeeId.setText(emp.getEmployeeId());
    }

    public static void getApprovalResponse(String responseString){
        try{
            JSONObject responseObj = new JSONObject(responseString);
            String team =  responseObj.getString("teamName");
            team = team.substring(0, team.length() - 1);
            Log.d("myTag", "searching team : " + team);
            if(responseObj.getString("isManager").equals("true") ){
                int resp = db.addManagerTOTeam(responseObj.getString("employeeId"), responseObj.getString("firstName") + " " + responseObj.getString("middleName") + " " + responseObj.getString("lastName"), team);
                Log.d("myTag", "updating team : " + resp );
            }
            if(db.getTeamByName(team) != null) {
                int resp = db.updateEmp(responseObj.getString("employeeId"), team, db.getTeamByName(team).getManagerId(), responseObj.getString("designation"));
                Log.d("myTag", "updating emp : " + resp);
            }
                Intent  i = new Intent(mActivity, UnapprovedView.class);
            mActivity.startActivity(i);
        }catch(JSONException e){
            Log.d("myTag", "json error " ,e);
        }
    }


    public void validateInput(TextView firstName, TextView lastName, TextView phone, TextView email, TextView aadharId, TextView employeeId, TextView designation){
        if(firstName.getText().length() == 0){
            firstName.requestFocus();
            firstName.setError("Field cannot be left empty");
        } else if (!validateFirstName(firstName.getText().toString())){
            firstName.requestFocus();
            firstName.setError("Please input valid name");
        }
        if(email.getText().length() == 0) {
            email.requestFocus();
            email.setError("Field cannot be left empty");
        } else if (!validateEmail(email.getText().toString())){
            Log.d("myTag", "wrong email");
            email.requestFocus();
            email.setError("Please input valid email");
        }
        if(lastName.getText().length() == 0){
            lastName.requestFocus();
            lastName.setError("Field cannot be left empty");
        } else if (!validateLastName(lastName.getText().toString())){
            lastName.requestFocus();
            lastName.setError("Please input valid name");
        }
        if(phone.getText().length() == 0){
            phone.requestFocus();
            phone.setError("Field cannot be left empty");
        } else if (!validatePhone(phone.getText().toString())){
            phone.requestFocus();
            phone.setError("Please input valid number");
        }
        if(aadharId.getText().length() == 0){
            aadharId.requestFocus();
            aadharId.setError("Field cannot be left empty");
        }
        if(employeeId.getText().length() == 0){
            employeeId.requestFocus();
            employeeId.setError("Field cannot be left empty");
        }
        if(designation.getText().length() == 0){
            employeeId.requestFocus();
            employeeId.setError("Field cannot be left empty");
        }
    }

    // validate first name
    public static boolean validateFirstName( String firstName )
    {
        return firstName.matches( "[A-Z][a-zA-Z]*" );
    } // end method validateFirstName

    // validate last name
    public static boolean validateLastName( String lastName )
    {
        return lastName.matches( "[A-Z][a-zA-Z]*" );
    } // end method validateLastName

    public static boolean validatePhone(String phone_text) {
        boolean correct;

        if ((phone_text.length() <= 12) && (phone_text.matches("^[0-9-]+$")))
            correct = true;
        else
            correct = false;

        System.out.println("correct =" + correct);
        return correct;

        // InputFilter lengthFilter = new InputFilter.LengthFilter(12);
    }

    public static boolean validateEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

// onClick of button perform this simplest code.
        Matcher matcher = Patterns.EMAIL_ADDRESS.matcher(email);
        return matcher.matches();
    }
}

