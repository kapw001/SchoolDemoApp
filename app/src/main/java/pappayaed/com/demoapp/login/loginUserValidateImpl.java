package pappayaed.com.demoapp.login;


import org.json.JSONException;

import pappayaed.com.demoapp.preference.SessionManagenent;

/**
 * Created by yasar on 17/4/17.
 */

class loginUserValidateImpl implements LoginContract.LoginUserValidate {

    private static final String TAG = "loginUserValidateImpl";


    @Override
    public void validateUserCredentials(final String username, final String password, final LoginContract.LoginPresenterView loginPresenterView) throws JSONException {

        if (username.length() > 0 && password.length() > 0) {

            SessionManagenent sessionManagenent = SessionManagenent.getSessionManagenent();
            sessionManagenent.saveSession(username, password, true);

            loginPresenterView.onSuccess();
        } else {
            loginPresenterView.showToast("Username and password not empty");
        }

    }
}
