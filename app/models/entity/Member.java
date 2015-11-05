package models.entity;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Tatsuya Oba
 */
@Entity
@Table(name = "tblmembers")
public class Member extends Model {
    @Id
    public Integer id;

    @Column(nullable = false)
    public String name;

    public Boolean state;

    @ManyToOne
    @Column(nullable = false)
    public User user;

    public Boolean author;

    @ManyToOne
    public Group group;

    public static final Finder<Integer, Member> finder = new Finder<>(Member.class);
}
