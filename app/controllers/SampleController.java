package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.data.GroupAPI;
import models.data.StateAPI;
import models.data.UserAPI;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author Tatsuya Oba
 */
public class SampleController extends Controller {
    public Result getSampleState() {
        final GroupAPI group = new GroupAPI();
        group.groupId = 123456789;
        group.name = "Attena?プロジェクト";
        group.latitude = 36.385913;
        group.longitude = 136.625977;

        final UserAPI user1 = new UserAPI();
        user1.name = "俺";
        user1.state = true;

        final UserAPI user2 = new UserAPI();
        user2.name = "私";
        user2.state = false;

        final StateAPI state = new StateAPI();
        state.group = group;
        state.userList.add(user1);
        state.userList.add(user2);

        final JsonNode json = Json.toJson(state);
        return ok(json);
    }
}
