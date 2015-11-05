package models.data;

import models.entity.Group;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tatasuya Oba
 */
public class StateAPI {
    public GroupAPI group;
    public List<UserAPI> userList;

    public StateAPI() {
        this.userList = new ArrayList<>();
    }

    public StateAPI(@Nonnull final Group group) {
        this.group = new GroupAPI(group);
        userList = new ArrayList<>();
        group.memberList.forEach(member -> userList.add(new UserAPI(member)));
    }
}
