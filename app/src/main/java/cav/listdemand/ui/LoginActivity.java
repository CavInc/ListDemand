package cav.listdemand.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import cav.listdemand.R;
import cav.listdemand.data.managers.DataManager;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackLoginUser;
import ru.profit_group.scorocode_sdk.Responses.user.ResponseLogin;
import ru.profit_group.scorocode_sdk.ScorocodeSdk;
import ru.profit_group.scorocode_sdk.scorocode_objects.User;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "LOGINACTIVITY";
    private static final String APPLICATION_ID = "9e1c8b8022964587af2f596b0228978e";
    private static final java.lang.String CLIENT_KEY = "1e3c8c0846c54a909d8cda5ea28002b4";
    private static final String FILE_KEY = "f310b2300e73406785c490e867c4a7ab";
    private static final String MESSAGE_KEY = "77754e03c6c1473d834492972c9821a8";
    private static final String SCRIPT_KEY = "26d15a96814f4e0b8fc1f82ec0e26a46";


    private EditText mLoginEmail;
    private EditText mLoginPass;
    private Button mLoginBt;
    private TextView mRegistry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginEmail = (EditText) findViewById(R.id.login_email);
        mLoginPass = (EditText) findViewById(R.id.login_pass);

        mLoginBt = (Button) findViewById(R.id.login_bt);
        mLoginBt.setOnClickListener(this);

        mRegistry = (TextView) findViewById(R.id.login_registry);
        mRegistry.setOnClickListener(this);

        ScorocodeSdk.initWith(APPLICATION_ID, CLIENT_KEY, null, FILE_KEY, MESSAGE_KEY, SCRIPT_KEY, null);

        if (isLogined()){
            startMaintActivity();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_bt:
                testAndStart();
                break;
            case R.id.login_registry:
                Intent intent = new Intent(this,RegistryActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void testAndStart() {
        mLoginPass.getText();
        User user = new User();
        user.login(mLoginEmail.getText().toString(),mLoginPass.getText().toString(),new CallbackLoginUser(){
            @Override
            public void onLoginSucceed(ResponseLogin responseLogin) {
                Log.d(TAG,"OK");
                Log.d(TAG,responseLogin.getResult().getSessionId());
                HashMap<String, Object> userContent = responseLogin.getResult().getUserInfo().getContent();
               // HashMap<String, Object> userFields = responseLogin.getResult().getUserInfo().getFields();
                DataManager dm = DataManager.getInstance();
                dm.getPreferensManager().saveLoginedUser(userContent,responseLogin.getResult().getSessionId());

                startMaintActivity();
            }

            @Override
            public void onLoginFailed(String errorCode, String errorMessage) {
                Log.d(TAG,errorMessage);
                showToast(errorMessage);
            }
        });

    }
    private void startMaintActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        //finish();
    }

    @NonNull
    private Boolean isLogined(){
        String sessionId = DataManager.getInstance().getPreferensManager().getSessionId();
        if (sessionId!=null && ScorocodeSdk.getSessionId()!=null) {
            return true;
        }
        return false;
    }
}
