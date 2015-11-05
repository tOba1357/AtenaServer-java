package models.services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import models.entity.User;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author Tatsuya Oba
 */
public class UserService {
    public User getUserByUserId(@Nonnull final String userId) {
        return User.finder.where()
                .eq("userId", userId)
                .findUnique();
    }

    public void signupUser(@Nonnull final User user) {
        user.save();
    }

    public List<User> getAllUser() {
        return User.finder.all();
    }

    public User getUserById(@Nonnull final Integer id) {
        return User.finder.byId(id);
    }
}
