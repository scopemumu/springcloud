package com.estone.erp.common.mq.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 消息模型
 * 
 * @author Kevin
 *
 */
@Data
public class MqMessage implements Serializable {
    private static final long serialVersionUID = -2885649815878293661L;

    /**
     * 消息类型
     */
    private MqType mqType;

    /**
     * 服务名 {@link com.estone.erp.common.module.Services}
     */
    private String service;

    /**
     * 分为系统和用户创建，系统名为System
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息队列名
     */
    private String queue;

    public MqMessage() {
        this.createTime = new Date();
    }

    public MqMessage(MqType mqType, String service, String userName, String content) {
        this.mqType = mqType;
        this.service = service;
        this.userName = userName;
        this.content = content;
        this.createTime = new Date();
    }

    @Override
    public String toString() {
        return "MqMessage [mqType=" + mqType + ", service=" + service + ", userName=" + userName + ", createTime="
                + createTime + ", content=" + content + ", queue=" + queue + "]";
    }
}
