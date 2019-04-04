package com.ddnet.httpsign;

import com.ddnet.httpsign.bo.Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * @author: Vinson.Ding
 * @create: 2019-04-03
 **/
@Slf4j
public class RestTemplateFilterTest {
    @Test
    public void test() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .requestFactory(()->new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .additionalInterceptors(new SignClientHttpRequestInterceptor())
                .build();


        Message message = new Message();
        message.setFrom("jerry");
        message.setTo("tom");
        message.setMessage("hello");
        message.setSendTime(LocalDateTime.now());

        Message message1 = restTemplate.postForObject("http://localhost:8111/message", message, Message.class);
        log.info("resp body :{}", message1);

    }
}
