package io.ddnet.vuesimple.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Vinson.Ding on 2018/5/14.
 */
@Component("authc")
@Slf4j
public class VueFormAuthenticationFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                log.trace("Login submission detected.  Attempting to execute login.");
                return executeLogin(request, response);
            } else {
                log.trace("Login page view.");
                //allow them to see the login page ;)
                return true;
            }
        } else {
            log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                    "Authentication url [" + getLoginUrl() + "]");
            ((HttpServletResponse) response).setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.info("user login fail");
        log.error("", e);
        try {
            response.setContentType("application/json");
            response.getWriter().write("{\"login\":false}");
        } catch (IOException e1) {
            log.error("", e1);
        }
        return false;
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        log.info("user login secess");
        try {
            response.setContentType("application/json");
            response.getWriter().write("{\"login\":true}");
        } catch (IOException e1) {
            log.error("", e1);
        }
        return false;
    }
}
