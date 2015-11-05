package controllers;

import models.entity.User;
import models.services.GroupService;
import models.services.UserService;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author Tatsuya Oba
 */
public class WebController extends Controller {
    final UserService userService = new UserService();
    final GroupService groupService = new GroupService();

    public Result renderMemberInfo(final Integer id) {
        if (id == null) {
            return badRequest();
        }

        final User user = userService.getUserById(id);
        if (user == null) {
            return badRequest();
        }

        return ok(views.html.memberInfo.render(user.memberList.get(0)));
    }

    public Result renderUserList() {
        return ok(views.html.userList.render(userService.getAllUser()));
    }

    public Result renderGroupList() {
        return ok(views.html.groupList.render(groupService.getAllGroup()));
    }
}
