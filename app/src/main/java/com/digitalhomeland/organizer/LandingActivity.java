package com.digitalhomeland.organizer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalhomeland.organizer.models.Store;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

public class LandingActivity extends AppCompatActivity implements LocationListener{

    LocationManager locationManager;
    static Location testLoc;
    static String testAddress;
    private static Volley_Request postRequest;
    static DatabaseHandler db;
    String actionButton;
    static Context mActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        mActivity = LandingActivity.this;
        db = new DatabaseHandler(this);
        //db.deleteStore();
        //db.deleteEmployees();
        //db.deleteTeams();
        //db.dropAllUserTables();

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        Button contiButton = (Button) findViewById(R.id.btn_conti);
        contiButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Launch Time Picker Dialog
                getLocation();
                actionButton = "2";
            }

        });

    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, (LocationListener) this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //  locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
        testLoc = location;
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            Log.d("myTag","Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude() + "\n" + addresses.get(0).getAddressLine(0)+" : "+
                    addresses.get(0).getAddressLine(1)+" : "+addresses.get(0).getAddressLine(2) );
            testAddress = addresses.get(0).getAddressLine(0);

            if(actionButton == "2"){
                //String req = "{\"reciever\":\"empGet\",\"params\":{\"address\":\"" + testAddress + "\"}}";
                //postRequest = new Volley_Request();
                //postRequest.createRequest(LandingActivity.this, getResources().getString(R.string.mJSONURL_store), "POST", "loadStore", req);
                getStoresResponse("{\"_id\":\"123\",\"name\":\"Shivedic\",\"storeId\":\"St001\",\"city\":\"Bareilly\",\"state\":\"Uttar Pradesh\",\"lat\":\"345\",\"longi\":\"678\",\"address\":\"bly\",\"keyActive\":\"true\",\"sellerId\":\"seller001\",\"closingDay\":\"Sunday\",\"rosterGenDay\":\"Sunday\",\"adminId\":\"admin007\"}");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            Log.d("myTag", "error : " , e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(locationManager != null) {
            locationManager.removeUpdates(this);
        }
        Log.d("my/tag", "onPause, done");
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        if(db.getStore() != null ){
            Intent i = new Intent(mActivity, DashboardActivity.class);
            mActivity.startActivity(i);
        }*/
    }


    public static void addStoreResponse(String responseString){
    }

    public static void getStoresResponse(String responseString){
        try{
            JSONObject responseObj = new JSONObject(responseString);

            //Store storeRes = new Store(responseObj.getString("_id"),responseObj.getString("storeId"),responseObj.getString("name"),responseObj.getString("city"),responseObj.getString("state"),responseObj.getString("lat"),responseObj.getString("longi"),responseObj.getString("address"),responseObj.getString("empCount"));
            final Store storeRes = new Store(responseObj.getString("_id"),responseObj.getString("name"),responseObj.getString("storeId"),responseObj.getString("city"),responseObj.getString("state"),responseObj.getString("lat"),responseObj.getString("longi"),responseObj.getString("address"),responseObj.getString("empCount"),responseObj.getString("keyActive"),responseObj.getString("sellerId"),responseObj.getString("closingDay"),responseObj.getString("rosterGenDay"),responseObj.getString("adminId"));
            final String adminId = responseObj.getString("adminId");
            //List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            // Log.d("myTag", "sync:addingApplication: " + testAddress.getAddressLine(0).equals(locRes.getAddress()) + " : " + locRes.getLat().equals(testLoc.getLatitude()) + " : " + locRes.getLong().equals(testLoc.getLongitude()));
            LayoutInflater layoutInflaterAndroid = LayoutInflater.from(mActivity);
            View mView = layoutInflaterAndroid.inflate(R.layout.teacher_input_dailog_box, null);

            AlertDialog.Builder alertDialogBuilderTeacherInput = new AlertDialog.Builder(mActivity, R.style.AlertDialogTheme);
            TextView removeStudentText = (TextView) mView.findViewById(R.id.dialogTitle);
            removeStudentText.setText("Enter StoreID :");
            final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);

            alertDialogBuilderTeacherInput.setView(mView);
            alertDialogBuilderTeacherInput
                    .setCancelable(false)
                    .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogBox, int id) {
                            // ToDo get user input here
                            Log.d("myTag", "dailog input : " + userInputDialogEditText.getText() + " : " +  adminId);
                            // if it is equal to schoolId recieved from page 1
                            if(userInputDialogEditText.getText().toString().equals(adminId)) {  //fix later
                                db.addStores(storeRes);
                                Intent i = new Intent(mActivity, DashboardActivity.class);
                                mActivity.startActivity(i);
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

        }catch(JSONException e){
            Log.d("myTag", "json error " ,e);
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(LandingActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
