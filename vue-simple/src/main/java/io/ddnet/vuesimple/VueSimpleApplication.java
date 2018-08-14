package io.ddnet.vuesimple;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Slf4j
public class VueSimpleApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueSimpleApplication.class, args);
    }




    //例外控制器
    @ExceptionHandler({AuthorizationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleException(AuthorizationException e, Model model) {
        log.debug("AuthorizationException was thrown", e);
        model.addAttribute("errors", getModel(HttpStatus.FORBIDDEN));

        return "error";
    }

    private Object getModel(HttpStatus status) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status.value());
        map.put("message", "No message available");
        return map;
    }
}
