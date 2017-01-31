package cav.listdemand.ui;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import cav.listdemand.utils.ConstantManager;

/**
 * Created by Kotov Alexandr on 31.01.17.
 *
 */
public class BaseActivity extends AppCompatActivity{
    private final String TAG = ConstantManager.TAG_PREFIX;

    public void showError(String message,Exception error){
        showToast(message);
        Log.e(TAG,String.valueOf(error));

    }

    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }
}
