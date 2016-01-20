package com.sid.androidstudioprojects.option.Json;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.sid.androidstudioprojects.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json_Offline extends Activity {
    public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_activity);

    TextView output = (TextView) findViewById(R.id.textView1);
        String strJson="{" +
                "\"Employee\" :[" +
                "{" +
                "\"id\":\"01\"," +
                "\"name\":\"Gopal Varma\"," +
                "\"salary\":\"500000\"}," +
                "{" +
                "\"id\":\"02\"," +
                "\"name\":\"Sairamkrishna\"," +
                "\"salary\":\"500000\"}," +
                "{" +
                "\"id\":\"03\"," +
                "\"name\":\"Sathish kallakuri. what is happning i am not" +
                " understanding just checking.\n what is the actual limit of the data passing of the json parsing?" +
                "previously i failed. now i am again checking.\"," +
                "\"salary\":\"600000\"}" +
                "]" +
                "}";
        String data = "";
    try {
        JSONObject jsonRootObject = new JSONObject(strJson);

        //Get the instance of JSONArray that contains JSONObjects
//        JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");
        JSONArray jsonArray = jsonRootObject.getJSONArray("Employee");

        //Iterate the jsonArray and print the info of JSONObjects
        for(int i=0; i < jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            int id = Integer.parseInt(jsonObject.optString("id"));
//            String name = jsonObject.optString("name");
            String name =jsonObject.getString("name");
            float salary = Float.parseFloat(jsonObject.optString("salary"));

            data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
        }
        output.setText(data);
    } catch (JSONException e) {e.printStackTrace();}
}
    }

