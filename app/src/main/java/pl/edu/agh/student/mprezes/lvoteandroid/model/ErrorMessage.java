package pl.edu.agh.student.mprezes.lvoteandroid.model;

import pl.edu.agh.student.mprezes.lvoteandroid.R;

/**
 * @author Krystian Majewski
 * @since 30.05.2017
 */

public enum ErrorMessage {
    ERROR_INCORRECT_USERNAME_OR_PASSWORD(R.string.error_incorrect_password);

    private final int resId;

    ErrorMessage(int resId) {
        this.resId = resId;
    }

    public int getResourrceId() {
        return resId;
    }
}
