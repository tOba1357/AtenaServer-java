package models.forms;

import models.entity.Member;
import models.entity.User;
import models.services.UserService;
import play.data.validation.Constraints;

/**
 * @author Tatsuya Oba
 */
public class SignupUser {
    @Constraints.Required(message = "必須の項目です。")
    public String userId;

    @Constraints.Required(message = "必須の項目です。")
    public String password;

    @Constraints.Required(message = "必須の項目です。")
    public String name;

    private final UserService userService = new UserService();

    public String validate() {
        if (userService.getUserByUserId(userId) != null) {
            return "既に登録されているユーザIDです。";
        }
        return null;
    }

    public User makeEntity() {
        final User user = new User();
        user.userId = this.userId;
        user.hashedPassword = User.calcHashedPassword(this.password);
        final Member member = new Member();
        member.name = this.name;
        user.memberList.add(member);

        return user;
    }
}
