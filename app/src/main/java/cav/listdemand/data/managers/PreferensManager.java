package cav.listdemand.data.managers;

import android.content.SharedPreferences;

import java.util.HashMap;

import cav.listdemand.utils.ListDemandApplication;

/**
 * Created by Kotov Alexandr on 31.01.17.
 *
 */
public class PreferensManager {
    private static final String SESSION_ID = "SESSION_ID";
    private SharedPreferences mSharedPreferences;

    public PreferensManager(){
        this.mSharedPreferences = ListDemandApplication.getSharedPreferences();
    }

    public void saveLoginedUser(HashMap<String, Object> userInfo,String sessionID){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(SESSION_ID,sessionID);
        editor.apply();
    }
    public String getSessionId(){
        return mSharedPreferences.getString(SESSION_ID,null);
    }
}
