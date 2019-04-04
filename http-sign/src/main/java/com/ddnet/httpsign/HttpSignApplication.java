package com.ddnet.httpsign;

import com.ddnet.httpsign.bo.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HttpSignApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpSignApplication.class, args);
    }
    @RequestMapping(value = "/message",method = RequestMethod.POST)
    public Message message(@RequestBody Message message){
        message.setMessage("you say:"+ message.getMessage());
        return message;
    }
    @RequestMapping(value = "/")
    public String  message(){
        return "OK";
    }
}
