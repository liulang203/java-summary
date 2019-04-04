package com.ddnet.httpsign;

import com.ddnet.httpsign.bo.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpSignApplicationTests {
    @Autowired
    HttpSignApplication httpSignApplication;

    @Test
    public void contextLoads() {

    }
    @Test
    public void message(){
        Message message = new Message();
        message.setFrom("jerry");
        message.setTo("tom");
        message.setMessage("hello");
        message.setSendTime(LocalDateTime.now());
        httpSignApplication.message(message);
    }

}
