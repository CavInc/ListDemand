package cav.listdemand.data.managers;

import android.content.SharedPreferences;

import java.util.HashMap;

import cav.listdemand.data.storage.models.UserModel;
import cav.listdemand.utils.ConstantManager;
import cav.listdemand.utils.ListDemandApplication;

/**
 * Created by Kotov Alexandr on 31.01.17.
 *
 */
public class PreferensManager {
    private static final String SESSION_ID = "SESSION_ID";
    private SharedPreferences mSharedPreferences;

    private static final String[] USER_LOGIN_INFO = new String[]{ConstantManager.USER_LOGIN_ID, ConstantManager.USER_LOGIN_EMAIL, ConstantManager.USER_LOGIN_NAME};

    public PreferensManager(){
        this.mSharedPreferences = ListDemandApplication.getSharedPreferences();
    }

    public void saveLoginedUser(HashMap<String, Object> userInfo,String sessionID){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(SESSION_ID,sessionID);
        editor.putString(ConstantManager.USER_LOGIN_ID, (String) userInfo.get("_id"));
        editor.putString(ConstantManager.USER_LOGIN_NAME, (String) userInfo.get("username"));
        editor.putString(ConstantManager.USER_LOGIN_EMAIL, (String) userInfo.get("email"));
        editor.apply();
    }
    public String getSessionId(){
        return mSharedPreferences.getString(SESSION_ID,null);
    }
    public String getUserID(){
        return mSharedPreferences.getString(ConstantManager.USER_LOGIN_ID,"");
    }
}
