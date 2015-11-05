package models.forms;

import play.data.validation.Constraints;

/**
 * @author Tatsuya Oba
 */
public class FindGroup {
    @Constraints.Required(message = "必須の項目です。")
    public Integer groupId;
}
