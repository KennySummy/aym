package com.system.aym.service.impl;

import com.system.aym.common.bean.AmqpParam;
import com.system.aym.service.ProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private AmqpParam amqpParam;

    @Override
    public void sendMessage(String message) {
        // 执行保存
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(amqpParam.getDirectExchange(), amqpParam.getRoutingkey(), message, correlationId);
    }

}
