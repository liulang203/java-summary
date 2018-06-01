package io.ddnet.vuesimple.web;

import com.google.common.collect.Lists;
import io.ddnet.vuesimple.domain.Menu;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinson.Ding on 2018/5/25.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @RequiresAuthentication
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Menu> userInfo() {

        List<Menu> menus = new ArrayList<>();
        List atherMenus = Lists.newArrayList(new Menu("1-4-1",null,true,"系统配置",null,
                        null),
                new Menu("1-4-2",null,true,"用户管理","/user",
                        null),
                new Menu("1-4-3",null,true,"角色管理","/role",
                        null)
        );
        menus.add(new Menu("1","el-icon-location",true,"系统",null,
                Lists.newArrayList(new Menu("1-1",null,true,"系统配置","/sys",
                        null),
                        new Menu("1-2",null,true,"用户管理","/user",
                        null),
                        new Menu("1-3",null,true,"角色管理","/role",
                                null),
                        new Menu("1-4",null,true,"其它管理",null,
                                atherMenus)
                        )));
        menus.add(new Menu("2","el-icon-document",true,"产品管理","/product",null));

        menus.add(new Menu("3","el-icon-document",true,"交易管理","/product",null));
        return menus;
    }
}
