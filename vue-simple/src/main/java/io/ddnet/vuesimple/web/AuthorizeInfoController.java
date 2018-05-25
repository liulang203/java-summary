package io.ddnet.vuesimple.web;

import io.ddnet.vuesimple.domain.User;
import io.ddnet.vuesimple.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Vinson.Ding on 2018/5/11.
 */
@RestController
@RequestMapping("/authorize")
public class AuthorizeInfoController {
    @Autowired
    private UserRepository repository;

    @RequiresAuthentication
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public Map<String, Object> userInfo() {
        Map<String, Object> res = new HashMap<>();
        User returnUser = new User();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        returnUser.setUid(user.getUid());
        returnUser.setName(user.getName());
        returnUser.setEmail(user.getEmail());
        res.put("userInfo", returnUser);
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        if (user.getRoles() != null) {
            user.getRoles().stream().forEach(role -> {
                roles.add(role.getName());
                permissions.addAll(role.getPermissions());
            });
        }

        res.put("roles", roles);
        res.put("permissions", permissions);
        return res;
    }
}
