package models.forms;

import play.data.validation.Constraints;

/**
 * @author Tatsuya
 */
public class LoginForm {
    @Constraints.Required(message = "必須の項目です。")
    public String userId;

    @Constraints.Required(message = "必須の項目です。")
    public String password;
}
