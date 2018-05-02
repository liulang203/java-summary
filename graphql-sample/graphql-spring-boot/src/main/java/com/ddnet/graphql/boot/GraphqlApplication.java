package com.ddnet.graphql.boot;

import com.ddnet.graphql.boot.datetime.DateScalars;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Vinson.Ding on 2018/5/2.
 */
@Controller
@SpringBootApplication
public class GraphqlApplication {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
    public static void main(String[] args) {
        DateScalars.initScalars();
        SpringApplication.run(GraphqlApplication.class, args);
    }

}
