package com.system.aym.config;

import com.system.aym.common.bean.AmqpParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    /**
     * 针对消费者配置 1. 设置交换机类型 2. 将队列绑定到交换机
     *
     * FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     * HeadersExchange：通过添加属性key-value匹配
     * DirectExchange: 按照routingkey分发到指定队列
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
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(amqpParam.getRoutingkey());
    }

}
