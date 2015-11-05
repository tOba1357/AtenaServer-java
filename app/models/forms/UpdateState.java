package models.forms;

import play.data.validation.Constraints;

/**
 * @author Tatsuya Oba
 */
public class UpdateState {
    @Constraints.Required(message = "必須の項目です。")
    public Integer groupId;

    @Constraints.Required(message = "必須の項目です。")
    public String userId;

    @Constraints.Required(message = "必須の項目です。")
    public Boolean state;
}
