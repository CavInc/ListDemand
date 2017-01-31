package cav.listdemand.data.managers;

import android.content.SharedPreferences;

import cav.listdemand.utils.ListDemandApplication;

/**
 * Created by Kotov Alexandr on 31.01.17.
 *
 */
public class PreferensManager {
    private SharedPreferences mSharedPreferences;

    public PreferensManager(){
        this.mSharedPreferences = ListDemandApplication.getSharedPreferences();
    }
}
