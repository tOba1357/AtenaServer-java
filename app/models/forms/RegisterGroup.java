package models.forms;

import models.entity.Group;
import models.services.GroupService;
import play.data.validation.Constraints;

import java.util.Random;

/**
 * @author Tatsuya Oba
 */
public class RegisterGroup {
    @Constraints.Required(message = "必須の項目です。")
    public String userId;

    @Constraints.Required(message = "必須の項目です。")
    public String name;

    @Constraints.Required(message = "必須の項目です。")
    public Double longitude;

    @Constraints.Required(message = "必須の項目です。")
    public Double latitude;

    final private GroupService groupService = new GroupService();
    public Group makeGroup() {
        final Random random = new Random();
        final Group group = new Group();
        while(true){
            group.groupId = random.nextInt(1000000000);
            if(groupService.getGroupByGroupId(group.groupId) == null) {
                break;
            }
        }
        group.latitude = this.latitude;
        group.longitude = this.longitude;
        group.name = this.name;
        return group;
    }
}
