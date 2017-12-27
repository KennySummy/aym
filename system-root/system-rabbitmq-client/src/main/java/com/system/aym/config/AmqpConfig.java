package com.system.aym.config;

import com.system.aym.common.bean.AmqpParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;

@Configuration
public class AmqpConfig {

    private static final Logger logger = LoggerFactory.getLogger(AmqpConfig.class);

    @Resource
    private AmqpParam amqpParam;

    @Bean
    public ConnectionFactory connectionFactory() {
        logger.debug(" RabbitMq configure. Loading ......");
        System.out.println(amqpParam.toString());
        if (amqpParam != null)
            logger.debug(amqpParam.toString() + " RabbitMq configure. Load success ......");
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(amqpParam.getHost());
        connectionFactory.setPort(amqpParam.getPort());
        connectionFactory.setUsername(amqpParam.getUsername());
        connectionFactory.setPassword(amqpParam.getPassword());
        connectionFactory.setVirtualHost(amqpParam.getVirtualHost());
        connectionFactory.setPublisherConfirms(amqpParam.isPublisherConfirms()); // 必须要设置
        return connectionFactory;
    }

    @Bean
    // 必须是prototype类型
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ReturnCallBackListener returnCallback,
                                         ReceiveConfirmTestListener messageConfirm) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setReturnCallback(returnCallback);
        return template;
    }

    /**
     * 针对消费者配置 1. 设置交换机类型 2. 将队列绑定到交换机
     *
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange：通过添加属性key-value匹配 DirectExchange: 按照routingkey分发到指定队列
     * TopicExchange: 多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(amqpParam.getDirectExchange());
    }

    @Bean
    public Queue queue() {
        // 队列持久
        return new Queue(amqpParam.getBmWSQueue(), true);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(amqpParam.getBmWSQueue());
    }

    @Resource
    private ReceiveConfirmTestListener confirmTestListener;

    /**
     * 消费者数量，默认10
     */
    public static final int DEFAULT_CONCURRENT = 20;

    /**
     * 每个消费者获取最大投递数量 默认50
     */
    public static final int DEFAULT_PREFETCH_COUNT = 50;

    /**
     * 接受消息的监听，这个监听会接受消息队列1的消息 针对消费者配置
     *
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(DEFAULT_PREFETCH_COUNT);
        container.setConcurrentConsumers(DEFAULT_CONCURRENT);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 设置确认模式手工确认
        container.setMessageListener(confirmTestListener);
        return container;
    }

}
