package cav.listdemand.data.managers;

/**
 * Created by Kotov Alexandr on 31.01.17.
 *
 */
public class DataManager {
    private static DataManager INSTANCE = null;
    private PreferensManager mPreferensManager;

    public static DataManager getInstance() {
        if (INSTANCE==null){
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public DataManager(){
        this.mPreferensManager = new PreferensManager();
    }

    public PreferensManager getPreferensManager() {
        return mPreferensManager;
    }

}
