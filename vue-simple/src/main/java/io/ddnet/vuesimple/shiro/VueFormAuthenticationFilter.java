package io.ddnet.vuesimple.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

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
}
