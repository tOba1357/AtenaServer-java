package models.entity;

import com.avaje.ebean.Model;
import models.data.GroupAPI;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tatsuya Oba
 */
@Entity
@Table(name = "tblgroups")
public class Group extends Model {
    @Id
    public Integer id;

    @Column(nullable = false)
    public Integer groupId;

    @Column(nullable = false)
    public String name;

    public Double longitude;

    public Double latitude;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> memberList;

    public static final Finder<Integer, Group> finder = new Finder<>(Group.class);

    public Group() {
        this.memberList = new ArrayList<>();
    }

    public GroupAPI makeDataForAPI() {
        final GroupAPI groupAPI = new GroupAPI();
        groupAPI.groupId = this.groupId;
        groupAPI.name = this.name;
        groupAPI.latitude = this.latitude;
        groupAPI.longitude = this.longitude;
        return groupAPI;
    }
}
