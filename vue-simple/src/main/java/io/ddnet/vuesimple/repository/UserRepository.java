package io.ddnet.vuesimple.repository;

import io.ddnet.vuesimple.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinson.Ding on 2018/5/11.
 */
@Component
public class UserRepository {
    private List<User> users = new ArrayList<>();
    private final User defaultUser = new User();

    public User findByName(String name) {
        return users.stream().filter(u -> name.equals(u.getName())).findFirst().orElse(defaultUser);
    }

}
