package com.sid.androidstudioprojects;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sid.androidstudioprojects.option.Json.Json_Offline;
import com.sid.androidstudioprojects.option.Json.Json_Online;
import com.sid.androidstudioprojects.option.login.Login;

public class MainActivity extends Activity {
    String msg = "Android : ";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "The onCreate() event");
//        call();
/**btn1 Called when the button to activity's Android Activity button will press then just parse json object from locally. */
        Button btn = (Button) findViewById(R.id.button_to_activity);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Json_Offline.class);
                startActivity(myIntent);
                Log.d(msg, "It is working but what is problem.");

            }

        });

/** btn2 Called when the button to activity's Go to Json button will press then just parse json object from internet. */
        Button btn1 = (Button) findViewById(R.id.button_to_json);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent1 = new Intent(MainActivity.this, Json_Online.class);
                startActivity(myIntent1);
                Log.d(msg, "It is working but what is problem.");

            }

        });

        /**btn3(image button) Called when the button to activity's Call image button will press then just call to my number. */
         ImageButton img_btn = (ImageButton) findViewById(R.id.imageButton);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contact_number = "+8801731005547";
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contact_number));
//                callIntent.setPackage("com.android.phone");
//                callIntent.setData(Uri.parse("tel:" + contact_number));
                try {
                    startActivity(callIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "Call Activity is not founded", Toast.LENGTH_SHORT).show();
                }
                Log.d(msg, "It is working but what is problem.");

            }

        });


        /**btn4(image button) Called when the button to activity's Call image button will press then connection will stablish with php database. */
//        ImageButton img_btn_php = (ImageButton) findViewById(R.id.imageButton2);
//        img_btn_php.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent phpIntent = new Intent(MainActivity.this, Php_Db_Connection.class);
//                startActivity(phpIntent);
//                Log.d(msg, "It is working but what is problem.");
//
//            }
//
//        });

        /**btn4(image button) Called when the button to activity's Call image button will press then connection will stablish with php database. */
        ImageButton img_btn_lin = (ImageButton) findViewById(R.id.imageButton3);
        img_btn_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linIntent = new Intent(MainActivity.this, Login.class);
                startActivity(linIntent);
                Log.d(msg, "It is working but what is problem.");

            }

        });

    }
/**Call a function during onstart() activity**/
//    private void call() {
//        String contact_number="+8801731005547";
//        Intent callIntent = new Intent(Intent.ACTION_CALL);
//        callIntent.setPackage("com.android.phone");
//        callIntent.setData(Uri.parse("tel:" + contact_number));
//        try{
//            startActivity(callIntent);
//        }
//        catch (android.content.ActivityNotFoundException ex){
//            Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
//        }
//    }

    /** Called when the activity is about to become visible. */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }
}
