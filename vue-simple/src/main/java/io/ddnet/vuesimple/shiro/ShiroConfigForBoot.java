package io.ddnet.vuesimple.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Vinson.Ding on 2018/5/11.
 */
@Configuration
@Slf4j
public class ShiroConfigForBoot {

    @Value("#{ @environment['shiro.loginUrl'] ?: '/login.jsp' }")
    protected String loginUrl;

    @Value("#{ @environment['shiro.successUrl'] ?: '/' }")
    protected String successUrl;

    @Value("#{ @environment['shiro.unauthorizedUrl'] ?: null }")
    protected String unauthorizedUrl;
    @Autowired
    private ShiroFilterChainDefinition shiroFilterChainDefinition;

    @Autowired
    private RealmSecurityManager securityManager;
    @Autowired
    private VueFormAuthenticationFilter vueFormAuthenticationFilter;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setLoginUrl(loginUrl);
        shiroFilter.setSuccessUrl(successUrl);
        shiroFilter.setUnauthorizedUrl(unauthorizedUrl);
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());

        //自定义拦截器

        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("authc", vueFormAuthenticationFilter);
        shiroFilter.setFilters(filtersMap);

        return shiroFilter;
    }


}
