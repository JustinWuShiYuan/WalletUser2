package com.tong.gao.walletuser.bean;

import java.io.Serializable;

public class MessageBean implements Serializable {

    private String messageId;
    private String content;
    private String type;
    private String createdTime;

    public MessageBean(String messageId, String content, String type, String createdTime) {
        this.messageId = messageId;
        this.content = content;
        this.type = type;
        this.createdTime = createdTime;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "messageId='" + messageId + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", createdTime='" + createdTime + '\'' +
                '}';
    }
}
