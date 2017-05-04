package pappayaed.com.demoapp.login;


import org.json.JSONException;

import pappayaed.com.demoapp.BaseView;

/**
 * Created by yasar on 17/4/17.
 */

public interface LoginContract {

    interface LoginView extends BaseView {

        void onSuccess();

        void onfail(boolean isTrue);

        void validateuserName(boolean isTrue);

        void validatepassword(boolean isTrue);

        void showAlert(String title, String msg);

    }

    interface LoginPresenter {
        void validateCredentials(String username, String password);

    }

    interface LoginPresenterView extends BaseView {
        void showToast(String msg);

        void onSuccess();

        void onfail(boolean isTrue);

        void validateuserName(boolean isTrue);

        void validatepassword(boolean isTrue);

        void showAlert(String title, String msg);
    }

    interface LoginUserValidate {
        void validateUserCredentials(String username, String password, LoginPresenterView loginPresenterView) throws JSONException;
    }

}
