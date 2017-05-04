package pappayaed.com.demoapp.preference;

import android.content.SharedPreferences;


import java.util.HashMap;
import java.util.Map;

import pappayaed.com.demoapp.App.App;

/**
 * Created by yasar on 27/4/17.
 */

public class SessionManagenent {

    private static SharedPreferences sharedPreferences = App.getApp().getPreferences();
    private static SessionManagenent sessionManagenent;

    public static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_STATUS = "status";


    private SessionManagenent() {
    }

    public static SessionManagenent getSessionManagenent() {
        if (sessionManagenent == null) {
            sessionManagenent = new SessionManagenent();
        }

        return sessionManagenent;
    }

    public void saveSession(String email, String password, boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PASSWORD, password);
        editor.putBoolean(KEY_STATUS, status);
        editor.commit();
    }

    public Map getSession() {
        Map hashMap = new HashMap();
        hashMap.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
        hashMap.put(KEY_PASSWORD, sharedPreferences.getString(KEY_PASSWORD, null));
        hashMap.put(KEY_STATUS, sharedPreferences.getBoolean(KEY_STATUS, false));
        return hashMap;
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_STATUS, false) == true ? true : false;
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();

    }


}
