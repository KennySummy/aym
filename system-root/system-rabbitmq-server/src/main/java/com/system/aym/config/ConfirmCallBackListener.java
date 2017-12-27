package com.system.aym.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

@Service("confirmCallBackListener")
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("confirm--:correlationData:" + correlationData + ",ack:" + ack + ",cause:" + cause);
    }

}
