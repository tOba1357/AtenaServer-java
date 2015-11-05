package models.entity;

import com.avaje.ebean.Model;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.Nonnull;
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
@Table(name = "tblusers")
public class User extends Model {
    @Id
    public Integer id;

    @Column(nullable = false)
    public String userId;

    @Column(nullable = false)
    public String hashedPassword;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Member> memberList;

    public static final Finder<Integer, User> finder = new Finder<>(User.class);

    public User() {
        this.memberList = new ArrayList<>();
    }

    public static String calcHashedPassword(@Nonnull final String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Boolean checkPassword(@Nonnull final String password) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
