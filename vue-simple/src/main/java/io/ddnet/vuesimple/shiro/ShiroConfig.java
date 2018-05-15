package io.ddnet.vuesimple.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vinson.Ding on 2018/5/11.
 */
@Configuration
@Slf4j
public class ShiroConfig {

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

        // logged in users with the 'admin' role
        chainDefinition.addPathDefinition("/src/**", "anon");
        chainDefinition.addPathDefinition("/static/**", "anon");
        chainDefinition.addPathDefinition("/", "anon");
        chainDefinition.addPathDefinition("/index.html", "anon");

        chainDefinition.addPathDefinition("/authorize/**", "authc");
        chainDefinition.addPathDefinition("/admin/**", "authc");

        return chainDefinition;
    }

}
