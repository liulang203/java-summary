package io.ddnet.vuesimple.repository;

import com.google.common.collect.Lists;
import io.ddnet.vuesimple.domain.Role;
import io.ddnet.vuesimple.domain.User;
import io.ddnet.vuesimple.utils.PasswordUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinson.Ding on 2018/5/11.
 */
@Component
public class UserRepository implements InitializingBean {
    private List<User> users = new ArrayList<>();
    private final User defaultUser = new User();

    public User findByName(String name) {
        return users.stream().filter(u -> name.equals(u.getName())).findFirst().orElse(defaultUser);
    }

    public User findByEmail(String email) {
        return users.stream().filter(u -> email.equals(u.getEmail())).findFirst().orElse(defaultUser);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        User admin = new User();
        admin.setEmail("admin@email.com");
        admin.setName("admin");
        admin.setPassword(PasswordUtils.getSecretPassword(admin.getEmail(), "test12345"));
        admin.setUid("admin");
        Role role = new Role();
        role.setName("admin");
        role.setPermissions(Lists.asList("p1", "p2", new String[]{"p3", "p4"}));
        admin.setRoles(Lists.newArrayList(role));

        users.add(admin);
    }
}
