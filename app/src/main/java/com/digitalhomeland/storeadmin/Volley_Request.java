package com.digitalhomeland.storeadmin;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Asus on 10/25/2017.
 */

public class Volley_Request {

    static String responseString = "";

    public static void createRequest(Context mContext, String requestURI, String type, String returnPath, final String requestBody) {

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        if (type.equals("GET")) {
            Log.d("myTag", "inside get req");
            // Initialize a new JsonObjectRequest instance
            final String returnPathCopy = returnPath;
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    requestURI,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Do something with response

                            for (int i = 0; i < 10; i++) {
                                //Log.d("myTag", "onResponse len : " + response.length());
                                if (response != null) {
                                    responseString = response.toString();
                                    Log.d("myTag", "responsestring != 1 : " + responseString);

                                } else {
                                    try {
                                        Thread.sleep(2000);
                                    } catch (Exception e) {
                                        Log.d("myTag", "error in sleep", e);
                                    }
                                }
                            }

                            //Log.d("myTag", "response : " + response.toString());
//responseString = response.toString();
                            // Log.d("myTag", "response assigned : " + responseString.toString());
                            //intent for Create Notification Page
                            // Intent i = new Intent(LandingActivity.this, SignupActivity.class);
                            // i.putExtra("input",response.toString());
                            //  i.putExtra("schoolId", "pihu007");
                            // startActivity(i);
                            // citySpinnerLoader(response.toString());

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Do something when error occurred
                            Log.d("myTag", "error aaya : " + error.toString());
                        }
                    }
            );
            jsonObjectRequest.setTag("GET");
            requestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    Log.d("DEBUG","request running: "+request.getTag().toString());
                    return true;
                }
            });
            requestQueue.add(jsonObjectRequest);
        } else if (type.equals("POST")) {
            Log.d("myTag", "requesturi : " + requestURI + " : " + requestBody);
            //Log.d("myTag", "requesturi : " + requestURI);
            final String returnPathCopy = returnPath;
            JSONObject requestObject = null;
            try {
                requestObject = new JSONObject(requestBody);
            } catch (Exception e) {
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    requestURI,
                    requestObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Do something with response
                                //Log.d("myTag", "onResponse len : " + response.length());
                                if (response != null) {
                                    responseString = response.toString();
                                    Log.d("myTag", "responsestring != 1 : " + responseString);
                                    if (returnPathCopy == "addStore")
                                        LandingActivity.addStoreResponse(responseString);
                                    else if (returnPathCopy == "loadStore")
                                        LandingActivity.getStoresResponse(responseString);
                                    else if (returnPathCopy == "SignActivityEmployee")
                                        SignupActivity.getSignUpResponse(responseString);
                                    else if (returnPathCopy == "getEmployees"){}
                                        //DashboardActivity.getEmpResponse(responseString);
                                    else if (returnPathCopy == "addTeam")
                                        DashboardActivity.addTeamResponse(responseString);
                                    else if (returnPathCopy == "getUnapprovedEmp")
                                        DashboardActivity.getUnapprovedResponse(responseString);
                                    else if (returnPathCopy == "ApproveEmployee")
                                        EmployeeApproval.getApprovalResponse(responseString);
                                    else if (returnPathCopy == "updateName")
                                        EditTeam.updateNameResponse(responseString);
                                    else if (returnPathCopy == "updateManager")
                                        EditTeam.updateManagerResponse(responseString);
                                } else {
                                    try {
                                        Thread.sleep(2000);
                                    } catch (Exception e) {
                                        Log.d("myTag", "error in sleep", e);
                                    }
                                }

                            //Log.d("myTag", "response : " + response.toString());
//responseString = response.toString();
                            // Log.d("myTag", "response assigned : " + responseString.toString());

                            //intent for Create Notification Page
                            // Intent i = new Intent(LandingActivity.this, SignupActivity.class);

                            // i.putExtra("input",response.toString());
                            //  i.putExtra("schoolId", "pihu007");
                            // startActivity(i);
                            // citySpinnerLoader(response.toString());
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Do something when error occurred
                            error.printStackTrace();
                            //String errorString = VolleyErrorHelper.getMessage(error, context);
                            Log.d("myTag", "error aaya : " + error.toString());
                        }
                    }

            );
            requestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    Log.d("DEBUG","request running: "+request.getTag().toString());
                    return true;
                }
            });
            requestQueue.add(jsonObjectRequest);
        }
    }


}