package io.ddnet.vuesimple.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Created by Vinson.Ding on 2018/5/11.
 */
@Getter
@Setter
public class User {
    private String uid;
    private String name;
    private String email;
    private String password;
    private List<Role> roles;

}
