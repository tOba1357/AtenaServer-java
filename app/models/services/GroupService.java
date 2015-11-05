package models.services;

import models.entity.Group;
import models.entity.Member;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author Tatsuya Oba
 */
public class GroupService {
    public Group getGroupByGroupId(@Nonnull final Integer groupId) {
        return Group.finder.where()
                .eq("groupId", groupId)
                .findUnique();
    }

    public List<Group> getAllGroup() {
        return Group.finder.all();
    }

    public void registerGroup(@Nonnull final Group group) {
        group.save();
        group.memberList.get(0).update();
    }

    public void addMember(
            @Nonnull final Group group,
            @Nonnull final Member member
    ) {
        member.group = group;
        member.author = false;
        member.update();
    }

    public void updateState(
            @Nonnull final Member member,
            @Nonnull final Boolean state
    ) {
        member.state = state;
        member.update();
    }
}
