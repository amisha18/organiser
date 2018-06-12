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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.digitalhomeland.inventorymanager.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;

public class SignupActivity extends AppCompatActivity {

    //String storeId = "";
    TextView firstName, middleName, lastName, phone, email, aadharId, employeeId;
    Context mContext;
    String response = "{\"reciever\":\"add\", \"params\": { \"employee\" :{";
    static DatabaseHandler db;
    private static Volley_Request postRequest;
    static User user;
    static Activity mActivity ;
    static String  storeKey,dailogInput = "";
    static String track = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setBackgroundDrawableResource(R.drawable.btr);
        mContext = getApplicationContext();
        mActivity = SignupActivity.this;
        db = new DatabaseHandler(this);
        firstName = (TextView) findViewById(R.id.su_first_name);
        middleName = (TextView) findViewById(R.id.su_middle_name);
        lastName = (TextView) findViewById(R.id.su_last_name);
        phone = (TextView) findViewById(R.id.su_phone);
        email = (TextView) findViewById(R.id.su_email);
        aadharId = (TextView) findViewById(R.id.su_aadhar_id);
        employeeId = (TextView) findViewById(R.id.su_employee_id);

        String req = "{\"reciever\":\"empKey\", \"params\" :{ \"storeId\":\"" + db.getStore().getStoreId() + "\"}}";
        postRequest = new Volley_Request();
        postRequest.createRequest(mContext, mContext.getResources().getString(R.string.mJSONURL_store), "POST", "loadKey", req);

        Button signUp =(Button) findViewById(R.id.sign_in_button);
        signUp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                validateInput(firstName, lastName, phone, email, aadharId, employeeId);
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(mContext);
                View mView = layoutInflaterAndroid.inflate(R.layout.teacher_input_dailog_box, null);
                AlertDialog.Builder alertDialogBuilderTeacherInput = new AlertDialog.Builder(SignupActivity.this, R.style.AlertDialogTheme);
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
                                if(userInputDialogEditText.getText().toString().equals(db.getStore().getStoreId())) {  //fix later
                                    response += "\"" + "firstName" + "\":\"" + firstName.getText().toString() + "\"," + "\"" + "middleName" + "\":\"" + middleName.getText().toString() + "\"," + "\"" + "lastName" + "\":\"" + lastName.getText().toString() + "\", \""  + "phone" + "\":\"" + phone.getText().toString() + "\",\""  + "email" + "\":\"" + email.getText().toString() + "\"," + "\"" + "aadharId" + "\":\"" + aadharId.getText().toString() + "\"," + "\"" + "employeeId" + "\":\"" + employeeId.getText().toString() + "\"," + "\"" + "storeId" + "\":\"" + userInputDialogEditText.getText() + "\",";
                                    response = response.substring(0, response.length()-1);
                                    response += "}}}";
                                    // setPreferences();
                                    Log.d("myTag", "adding response : " + response);
                                    postRequest = new Volley_Request();
                                    postRequest.createRequest(mContext, getResources().getString(R.string.mJSONURL_employee), "POST", "SignActivityEmployee",response);
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

    public static void getLoadKeyResponse(String response){
        try{
            JSONObject responseObj = new JSONObject(response);
            storeKey = responseObj.getString("joinKey");
            }catch(JSONException e){
            Log.d("myTag", "json error " ,e);
        }
    }

    public static void getSignUpResponse(String responseString){
        try{
            JSONObject responseObj = new JSONObject(responseString);
            if(!responseObj.has("success")) {
                user = new User(responseObj.getString("_id"), responseObj.getString("firstName"), responseObj.getString("middleName"), responseObj.getString("lastName"), responseObj.getString("phone"), responseObj.getString("email"), responseObj.getString("aadharId"), responseObj.getString("employeeId"), responseObj.getString("storeId"));
                db.addUsers(user);
            }
            //List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            // Log.d("myTag", "sync:addingApplication: " + testAddress.getAddressLine(0).equals(locRes.getAddress()) + " : " + locRes.getLat().equals(testLoc.getLatitude()) + " : " + locRes.getLong().equals(testLoc.getLongitude()));
            Intent i = new Intent(mActivity, DashboardActivity.class);
            mActivity.startActivity(i);
        }catch(JSONException e){
            Log.d("myTag", "json error " ,e);
        }
    }


    public void validateInput(TextView firstName, TextView lastName, TextView phone, TextView email, TextView aadharId, TextView employeeId){
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

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}

