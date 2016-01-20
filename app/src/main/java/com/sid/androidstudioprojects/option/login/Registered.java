package com.sid.androidstudioprojects.option.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sid.androidstudioprojects.R;
import com.sid.androidstudioprojects.option.login.library.DatabaseHandler;

import java.util.HashMap;

/**
 * Created by siddhartha on 01-Jan-16.
 */


public class Registered extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lin_registered);

        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        HashMap user = new HashMap();
        user = db.getUserDetails();

        /**
         * Displays the registration details in Text view
         **/

        final TextView fname = (TextView) findViewById(R.id.fname);
        final TextView lname = (TextView) findViewById(R.id.lname);
        final TextView uname = (TextView) findViewById(R.id.uname);
        final TextView email = (TextView) findViewById(R.id.email);
        final TextView created_at = (TextView) findViewById(R.id.regat);
        fname.setText(user.get("fname").toString());
        lname.setText(user.get("lname").toString());
        uname.setText(user.get("uname").toString());
        email.setText(user.get("email").toString());
        created_at.setText(user.get("created_at").toString());

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Login.class);
                startActivityForResult(myIntent, 0);
                finish();
            }

        });

    }
}