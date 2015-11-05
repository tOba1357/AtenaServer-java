package models.data;

import models.entity.Member;
import models.entity.User;

import javax.annotation.Nonnull;

/**
 * @author Tatsuya Oba
 */
public class UserAPI {
    public String name;

    public Boolean state;

    public Boolean author;

    public UserAPI() {
    }

    public UserAPI(@Nonnull final Member member) {
        this.name = member.name;
        this.state = member.state;
        this.author = member.author;
    }
}
