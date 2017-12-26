package com.system.aym.common.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class AmqpParam {

    /**
     * MQ 服务器地址
     */
    private String host;
    /**
     * MQ 服务器端口
     */
    private Integer port;
    /**
     * MQ 服务器账号
     */
    private String username;
    /**
     * MQ 服务器密码
     */
    private String password;
    private String virtualHost;
    private boolean publisherConfirms;

    // 路由关键字
    private String routingkey;

    // 交换机名称
    private String fanoutExchange;
    private String headerExchange;
    private String directExchange;
    private String topicExchange;

    // 队列配置
    private String bmWSQueue;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public boolean isPublisherConfirms() {
        return publisherConfirms;
    }

    public void setPublisherConfirms(boolean publisherConfirms) {
        this.publisherConfirms = publisherConfirms;
    }

    public String getFanoutExchange() {
        return fanoutExchange;
    }

    public void setFanoutExchange(String fanoutExchange) {
        this.fanoutExchange = fanoutExchange;
    }

    public String getHeaderExchange() {
        return headerExchange;
    }

    public void setHeaderExchange(String headerExchange) {
        this.headerExchange = headerExchange;
    }

    public String getDirectExchange() {
        return directExchange;
    }

    public void setDirectExchange(String directExchange) {
        this.directExchange = directExchange;
    }

    public String getTopicExchange() {
        return topicExchange;
    }

    public void setTopicExchange(String topicExchange) {
        this.topicExchange = topicExchange;
    }

    public String getBmWSQueue() {
        return bmWSQueue;
    }

    public void setBmWSQueue(String bmWSQueue) {
        this.bmWSQueue = bmWSQueue;
    }

    public String getRoutingkey() {
        return routingkey;
    }

    public void setRoutingkey(String routingkey) {
        this.routingkey = routingkey;
    }

    @Override
    public String toString() {
        return "AmqpParam [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password
                + ", virtualHost=" + virtualHost + ", publisherConfirms=" + publisherConfirms + ", fanoutExchange="
                + fanoutExchange + ", headerExchange=" + headerExchange + ", directExchange=" + directExchange
                + ", topicExchange=" + topicExchange + ", bmWSQueue=" + bmWSQueue + "]";
    }

}

