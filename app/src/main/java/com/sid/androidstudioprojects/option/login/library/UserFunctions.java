package com.sid.androidstudioprojects.option.login.library;

import android.content.Context;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by siddhartha on 01-Jan-16.
 */
public class UserFunctions {
    private JSONParser jsonParser;
    //URL of the PHP API
    private static String loginURL = "http://192.168.0.100/login_api/index.php";
    private static String registerURL = "http://192.168.0.100/login_api/index.php";
    private static String forpassURL = "http://192.168.0.100/login_api/index.php";
    private static String chgpassURL = "http://192.168.0.100/login_api/index.php";
    private static String urgentURL = "http://192.168.0.100/login_api/index.php";

    private static String login_tag = "login";
    private static String register_tag = "register";
    private static String forpass_tag = "forpass";
    private static String chgpass_tag = "chgpass";
    private static String urgent_tag="urgent";

    public UserFunctions() {
        jsonParser =new JSONParser();
    }
    /**
     * Function to Login
     **/
    public JSONObject loginUser(String email, String password){
        /*
        Building Parameters
        TODO: 01-Jan-16 departure should be find better and new alternative
        */
        List params = new ArrayList();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("email",email));
        params.add(new BasicNameValuePair("passowrd",password));
        JSONObject json= jsonParser.getJSONFromUrl(loginURL,params);
        return  json;
    }
    public JSONObject urgentNotice(String email){
        /*
        Building Parameters
        TODO: 01-Jan-16 departure should be find better and new alternative
        */
        List params = new ArrayList();
        params.add(new BasicNameValuePair("tag", urgent_tag));
        params.add(new BasicNameValuePair("email",email));
        JSONObject json= jsonParser.getJSONFromUrl(urgentURL,params);
        return  json;
    }
    /**
     * Function to change password
     **/

    public JSONObject chgPass(String newpas, String email){
        List params = new ArrayList();
        params.add(new BasicNameValuePair("tag", chgpass_tag));

        params.add(new BasicNameValuePair("newpas", newpas));
        params.add(new BasicNameValuePair("email", email));
        JSONObject json = jsonParser.getJSONFromUrl(chgpassURL, params);
        return json;
    }

    /**
     * Function to reset the password
     **/

    public JSONObject forPass(String forgotpassword){
        List params = new ArrayList();
        params.add(new BasicNameValuePair("tag", forpass_tag));
        params.add(new BasicNameValuePair("forgotpassword", forgotpassword));
        JSONObject json = jsonParser.getJSONFromUrl(forpassURL, params);
        return json;
    }

    /**
     * Function to  Register
     **/
    public JSONObject registerUser(String fname, String lname, String email, String uname, String password){
        // Building Parameters
        List params = new ArrayList();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("fname", fname));
        params.add(new BasicNameValuePair("lname", lname));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("uname", uname));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(registerURL,params);
        return json;
    }

    /**
     * Function to logout user
     * Resets the temporary data stored in SQLite Database
     **/
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }

}
