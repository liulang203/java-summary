package com.ddnet.httpsign;

import com.ddnet.httpsign.bo.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 *
 * @author : Vinson.Ding
 * @date : 2019-04-03
 **/
public class JsonTest {
    @Test
    public void test() throws JsonProcessingException {
        Message message = new Message();
        message.setFrom("jerry");
        message.setTo("tom");
        message.setMessage("hello");
        message.setSendTime(LocalDateTime.now());
        System.out.println(new ObjectMapper().writer().writeValueAsString(message));
    }

}
