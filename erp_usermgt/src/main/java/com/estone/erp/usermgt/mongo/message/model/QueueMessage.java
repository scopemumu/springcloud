package com.estone.erp.usermgt.mongo.message.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.estone.erp.common.mq.model.MqMessage;

/***
 * 队列消息
 * 
 * @author Kevin
 *
 */
@Document(collection = "queue_message")
public class QueueMessage implements Serializable {
    private static final long serialVersionUID = -6423805145829925340L;

    @Id
    private String id;

    /**
     * 消息类型
     */
    @Field(value = "mq_type")
    private String mqType;

    /**
     * 服务名 {@link com.estone.erp.common.module.Services}
     */
    @Field(value = "service")
    private String service;

    /**
     * 分为系统和用户创建，系统名为System
     */
    @Field(value = "user_uame")
    private String userName;

    /**
     * 创建时间
     */
    @Field(value = "create_time")
    private Date createTime;

    /**
     * 消息内容
     */
    @Field(value = "content")
    private String content;

    /**
     * 消息队列名
     */
    @Field(value = "queue")
    private String queue;

    public QueueMessage() {

    }

    public QueueMessage(MqMessage mqMessage) {
        this.mqType = mqMessage.getMqType().name();
        this.service = mqMessage.getService();
        this.userName = mqMessage.getUserName();
        this.content = mqMessage.getContent();
        this.createTime = mqMessage.getCreateTime();
        this.queue = mqMessage.getQueue();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMqType() {
        return mqType;
    }

    public void setMqType(String mqType) {
        this.mqType = mqType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }
}
