package io.ddnet.vuesimple.shiro;

import io.ddnet.vuesimple.domain.User;
import io.ddnet.vuesimple.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static io.ddnet.vuesimple.utils.PasswordUtils.PWD_HASH_NUMS;

/**
 * Created by Vinson.Ding on 2018/5/14.
 */
@Slf4j
@Component("authorizer")
public class VueUserAuthorizingRealm extends AuthorizingRealm {
    @Autowired
    private UserRepository userRepository;

    public VueUserAuthorizingRealm() {
        setName("adminRealm");
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-256");
        matcher.setStoredCredentialsHexEncoded(false);
        matcher.setHashIterations(PWD_HASH_NUMS);
        setCredentialsMatcher(matcher);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.fromRealm(getName()).iterator().next();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (user.getRoles() != null) {
            user.getRoles().stream().forEach(role -> {
                info.addRole(role.getName());
                info.addStringPermissions(role.getPermissions());
            });
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = ((UsernamePasswordToken) token).getUsername();
        User user = userRepository.findByEmail(userName);
        if (user == null) {
            log.debug("user not be found[username={}]", userName);
        }
        if (user != null && StringUtils.isNotEmpty(user.getName())) {
            String pwd = user.getPassword();
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, pwd, getName());
            info.setCredentialsSalt(
                    ByteSource.Util.bytes(user.getEmail().getBytes(StandardCharsets.UTF_8)));
            return info;
        }
        return null;
    }
}
