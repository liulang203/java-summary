package io.ddnet.vuesimple.web;

import io.ddnet.vuesimple.domain.User;
import io.ddnet.vuesimple.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vinson.Ding on 2018/5/11.
 */
@RestController
@RequestMapping("/authorize")
public class AuthorizeInfoController {
    @Autowired
    private UserRepository repository;

    @RequiresAuthentication
    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    public User userInfo(){
            return (User) SecurityUtils.getSubject().getPrincipal();
    }
}
