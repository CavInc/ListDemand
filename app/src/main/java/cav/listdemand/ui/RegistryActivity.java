package cav.listdemand.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cav.listdemand.R;
import ru.profit_group.scorocode_sdk.Callbacks.CallbackRegisterUser;
import ru.profit_group.scorocode_sdk.scorocode_objects.User;

public class RegistryActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "REGISTERACTIVITY";
    private EditText mUser;
    private EditText mEmail;
    private EditText mPassowrd;
    private EditText mPasswordTwo;

    private Button mRegistryBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        mUser = (EditText) findViewById(R.id.registry_user);
        mEmail = (EditText) findViewById(R.id.registry_email);
        mPassowrd = (EditText) findViewById(R.id.registry_pass);
        mPasswordTwo = (EditText) findViewById(R.id.registry_pass_two);

        mRegistryBt = (Button) findViewById(R.id.registry_bt);
        mRegistryBt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (mPassowrd.getText().toString().equals(mPasswordTwo.getText().toString())){
            registryNewUser();

        }else {
            showToast("Пароли не совпадают");
        }

    }

    private boolean isInputValid(String userName, String email, String password, String passwordCheck) {
        return !userName.isEmpty() && !email.isEmpty() && !password.isEmpty() && password.equals(passwordCheck);
    }

    private void registryNewUser() {
        User user = new User();
        user.register(mUser.getText().toString(),mEmail.getText().toString(),
                mPassowrd.getText().toString(), new CallbackRegisterUser() {
                    @Override
                    public void onRegisterSucceed() {
                        Log.d(TAG,"REGISTER OK");
                    }

                    @Override
                    public void onRegisterFailed(String errorCode, String errorMessage) {
                        Log.d(TAG,"NO REGISTER");
                        showToast(errorMessage);

                    }

        });

    }
}
