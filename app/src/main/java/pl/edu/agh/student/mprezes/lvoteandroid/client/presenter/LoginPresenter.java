package pl.edu.agh.student.mprezes.lvoteandroid.client.presenter;

import pl.edu.agh.student.mprezes.lvoteandroid.activities.LoginActivity;

/**
 * @author Krystian Majewski
 * @since 26.05.2017
 */

public class LoginPresenter extends AbstractPresenter {

    private LoginActivity loginActivity;

    public LoginPresenter(LoginActivity loginActivity) {
        super();
        this.loginActivity = loginActivity;
    }

    public void attemptLogin() {

    }
}
