package pappayaed.com.demoapp.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pappayaed.com.demoapp.main.MainActivity;
import pappayaed.com.demoapp.R;
import pappayaed.com.demoapp.common.Utils;


public class LoginActivity extends Activity implements LoginContract.LoginView {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;




    private LoginContract.LoginPresenter loginPresenter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);


        loginPresenter = new LoginPresenterImpl(this);
    }


    @OnClick(R.id.login)
    public void loginClick(View view) {

        String userName = username.getText().toString();
        String passWord = password.getText().toString();

        loginPresenter.validateCredentials(userName, passWord);

    }


    @Override
    public void showProgress(String msg) {
        progressDialog.setMessage(msg);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {

        if (progressDialog != null) {
            progressDialog.cancel();
        }

    }

    @Override
    public void showToast(String msg) {

        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess() {
        startActivity(new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void onfail(boolean isTrue) {

        if (isTrue) {
            username.setError("Please enter a valid email ");
            password.setError("Please enter a password ");
        } else {
            username.setError(null);
            password.setError(null);
        }

    }

    @Override
    public void validateuserName(boolean isTrue) {
        if (isTrue)
            username.setError("Please enter a vaild email ");
        else
            username.setError(null);
    }

    @Override
    public void validatepassword(boolean isTrue) {

        if (isTrue)
            password.setError("Please enter a password ");
        else
            password.setError(null);
    }

    @Override
    public void showAlert(String title, String msg) {

        Utils.show(this, title, msg);
    }
}
