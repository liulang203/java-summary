package com.ddnet.httpsign.bo;

import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 消息主体
 * @author Vinson.Ding
 * @date 2019/3/8
 */
@ToString
public class Message {
    private String from;
    private String to;
    private String message;
    private LocalDateTime sendTime;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }



    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
