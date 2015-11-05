package models.data;

import models.entity.Group;

import javax.annotation.Nonnull;

/**
 * @author Tatsuya Oba
 */
public class GroupAPI {
    public Integer groupId;

    public String name;

    public Double longitude;

    public Double latitude;

    public GroupAPI() {
    }

    public GroupAPI(@Nonnull final Group group) {
        this.groupId = group.groupId;
        this.name = group.name;
        this.longitude = group.longitude;
        this.latitude = group.latitude;
    }
}
