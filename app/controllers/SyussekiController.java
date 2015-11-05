package controllers;

import models.data.GroupAPI;
import models.data.StateAPI;
import models.entity.Group;
import models.entity.Member;
import models.entity.User;
import models.forms.AddMember;
import models.forms.FindGroup;
import models.forms.LoginForm;
import models.forms.RegisterGroup;
import models.forms.SignupUser;
import models.forms.UpdateState;
import models.services.GroupService;
import models.services.UserService;
import play.cache.Cache;
import play.data.Form;
import play.data.validation.ValidationError;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import security.AuthenticatorUser;

import java.util.Collections;
import java.util.UUID;

/**
 * @author Tatsuya Oba
 */
public class SyussekiController extends Controller {
    public final UserService userService = new UserService();
    public final GroupService groupService = new GroupService();

    @Security.Authenticated(AuthenticatorUser.class)
    public Result updateState() {
        final Form<UpdateState> form = Form.form(UpdateState.class)
                .bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }
        final UpdateState updateState = form.get();

        final User user = userService.getUserByUserId(updateState.userId);
        if (user == null) {
            form.errors().put("", Collections.singletonList(new ValidationError("", "ユーザーが見つかりませんでした。")));
            return badRequest(form.errorsAsJson());
        }

        final Group group = groupService.getGroupByGroupId(updateState.groupId);
        if (group == null) {
            form.errors().put("", Collections.singletonList(new ValidationError("", "グループが見つかりませんでした。")));
            return badRequest(form.errorsAsJson());
        }

        final Member member = user.memberList.get(0);
        if (!group.memberList.contains(member)) {
            form.errors().put("", Collections.singletonList(new ValidationError("", "グループに所属していません。")));
            return badRequest(form.errorsAsJson());
        }
        groupService.updateState(member, updateState.state);

        return ok("ok");
    }

    @Security.Authenticated(AuthenticatorUser.class)
    public Result getGroupMemberState() {
        final Form<FindGroup> form = Form.form(FindGroup.class)
                .bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        final Group group = groupService.getGroupByGroupId(form.get().groupId);
        if (group == null) {
            form.errors().put("", Collections.singletonList(new ValidationError("", "グループが見つかりませんでした。")));
            return badRequest(form.errorsAsJson());
        }
        final StateAPI state = new StateAPI(group);
        return ok(Json.toJson(state));
    }

    @Security.Authenticated(AuthenticatorUser.class)
    public Result addMemberToGroup() {
        final Form<AddMember> form = Form.form(AddMember.class)
                .bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }
        final AddMember addMember = form.get();

        final User user = userService.getUserByUserId(addMember.userId);
        if (user == null) {
            form.errors().put("", Collections.singletonList(new ValidationError("", "ユーザーが見つかりませんでした。")));
            return badRequest(form.errorsAsJson());
        }

        final Group group = groupService.getGroupByGroupId(addMember.groupId);
        if (group == null) {
            form.errors().put("", Collections.singletonList(new ValidationError("", "グループが見つかりませんでした。")));
            return badRequest(form.errorsAsJson());
        }

        final Member member = user.memberList.get(0);
        if (member.group != null) {
            form.errors().put("", Collections.singletonList(new ValidationError("", "既に登録されています。")));
            return badRequest(form.errorsAsJson());
        }

        groupService.addMember(group, member);
        return ok("ok");
    }

    @Security.Authenticated(AuthenticatorUser.class)
    public Result registerGroup() {
        final Form<RegisterGroup> form = Form.form(RegisterGroup.class)
                .bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }
        final RegisterGroup registerGroup = form.get();
        final User user = userService.getUserByUserId(registerGroup.userId);
        if (user == null) {
            form.errors().put("", Collections.singletonList(new ValidationError("", "ユーザーが見つかりませんでした。")));
            return badRequest(form.errorsAsJson());
        }
        final Member member = user.memberList.get(0);
        if (member.group != null) {
            form.errors().put("", Collections.singletonList(new ValidationError("", "既に登録されています。")));
            return badRequest(form.errorsAsJson());
        }

        final Group group = registerGroup.makeGroup();
        member.author = true;
        group.memberList.add(member);
        member.group = group;
        groupService.registerGroup(group);
        return ok(Json.toJson(group.groupId));
    }

    @Security.Authenticated(AuthenticatorUser.class)
    public Result searchGroup() {
        final Form<FindGroup> form = Form.form(FindGroup.class)
                .bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }
        final Group group = groupService.getGroupByGroupId(form.get().groupId);
        if (group == null) {
            form.errors().put("", Collections.singletonList(new ValidationError("", "グループが見つかりませんでした。")));
            return badRequest(form.errorsAsJson());
        }
        final GroupAPI groupAPI = group.makeDataForAPI();

        return ok(Json.toJson(groupAPI));
    }

    public Result signup() {
        final Form<SignupUser> form = Form.form(SignupUser.class)
                .bindFromRequest();
        if (form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }
        final SignupUser userSignup = form.get();
        final User user = userSignup.makeEntity();
        userService.signupUser(user);
        return ok("ok");
    }

    public Result login() {
        final Form<LoginForm> form = Form.form(LoginForm.class)
                .bindFromRequest();
        if(form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }
        final LoginForm loginForm = form.get();
        final User user = userService.getUserByUserId(loginForm.userId);
        if (user == null || !user.checkPassword(loginForm.password)) {
            form.errors().put("", Collections.singletonList(new ValidationError("", "IDまたはパスワードが間違っています。")));
            return badRequest(form.errorsAsJson());
        }

        final String sessionId = UUID.randomUUID().toString();
        Cache.set(sessionId, user.userId);

        return ok(Json.toJson(sessionId));
    }
}
