package models.forms;

import play.data.validation.Constraints;

/**
 * @author Tatsya Oba
 */
public class AddMember {
    @Constraints.Required(message = "必須の項目です。")
    public String userId;

    @Constraints.Required(message = "必須の項目です。")
    public Integer groupId;
}
