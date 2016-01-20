package com.sid.androidstudioprojects.option.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.sid.androidstudioprojects.R;
import com.sid.androidstudioprojects.option.login.library.DatabaseHandler;
import com.sid.androidstudioprojects.option.login.library.UserFunctions;

/**
 * Created by siddhartha on 01-Jan-16.
 */


public class ChangePassword extends Activity {

    private static String KEY_SUCCESS = "success";
    private static String KEY_ERROR = "error";

    EditText newpass;
    TextView alert;
    Button changepass;
    Button cancel;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lin_changepassword);

        cancel = (Button) findViewById(R.id.btcancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent login = new Intent(getApplicationContext(), Main.class);

                startActivity(login);
                finish();
            }

        });

        newpass = (EditText) findViewById(R.id.newpass);
        alert = (TextView) findViewById(R.id.alertpass);
        changepass = (Button) findViewById(R.id.btchangepass);

        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetAsync(view);
            }
        });
    }

    private class NetCheck extends AsyncTask {
        private ProgressDialog nDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            nDialog = new ProgressDialog(ChangePassword.this);
            nDialog.setMessage("Loading..");
            nDialog.setTitle("Checking Network");
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(true);
            nDialog.show();
        }

        @Override
        protected Boolean doInBackground(Object[] params) {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                try {
                    URL url = new URL("http://www.google.com");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(3000);
                    urlc.connect();
                    if (urlc.getResponseCode() == 200) {
                        return true;
                    }
                } catch (MalformedURLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return false;
        }

        @Override
        protected void onPostExecute(Object o) {
            Boolean th=(Boolean)o;
            if (th == true) {
                nDialog.dismiss();
                new ProcessRegister().execute();
            } else {
                nDialog.dismiss();
                alert.setText("Error in Network Connection");
            }
        }
    }

    private class ProcessRegister extends AsyncTask {

        private ProgressDialog pDialog;

        String newpas, email;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            DatabaseHandler db = new DatabaseHandler(getApplicationContext());

            HashMap user = new HashMap();
            user = db.getUserDetails();

            newpas = newpass.getText().toString();
            email = user.get("email").toString();

            pDialog = new ProgressDialog(ChangePassword.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(Object[] params) {

            UserFunctions userFunction = new UserFunctions();
            JSONObject json = userFunction.chgPass(newpas, email);
            Log.d("Button", "Register");
            return json;

        }

        @Override
        protected void onPostExecute(Object o) {
            JSONObject json=(JSONObject)o;
            try {
                if (json.getString(KEY_SUCCESS) != null) {
                    alert.setText("");
                    String res = json.getString(KEY_SUCCESS);
                    String red = json.getString(KEY_ERROR);

                    if (Integer.parseInt(res) == 1) {
                        /**
                         * Dismiss the process dialog
                         **/
                        pDialog.dismiss();
                        alert.setText("Your Password is successfully changed.");

                    } else if (Integer.parseInt(red) == 2) {
                        pDialog.dismiss();
                        alert.setText("Invalid old Password.");
                    } else {
                        pDialog.dismiss();
                        alert.setText("Error occured in changing Password.");
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();

            }

        }
    }

    public void NetAsync(View view) {
        new NetCheck().execute();
    }
}
