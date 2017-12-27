package com.system.aym.config;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("receiveConfirmTestListener")
public class ReceiveConfirmTestListener implements ChannelAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        // 业务处理，放到action层，并返回处理成功还是异常的flag
        boolean flag = false;
        try {
            String bm_message = new String(message.getBody(), "UTF-8");
//            flag = dataService.intoPersistenceDate(bm_message);
            // 根据 flag 返回数据，判断MQ中的数据是否需要重新处理，
            isFlagMQBasic(flag, message, channel);
        } catch (Exception e) {
            e.printStackTrace();// TODO 业务处理
            isFlagMQBasic(flag, message, channel);
//			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    // 正常消费掉后通知mq服务器移除此条mq
    private void basicACK(Message message, Channel channel) {
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            logger.error("通知服务器移除mq时异常，异常信息：" + e);
            logger.error("异常数据:" + message);
        }
    }

    // 处理异常，mq重回队列
    private void basicNACK(Message message, Channel channel) {
        try {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            logger.error("异常MQ数据：" + message);
            logger.error("异常BM数据：" + new String(message.getBody(), "UTF-8"));
        } catch (IOException e) {
            logger.error("mq重新进入服务器时出现异常，异常信息：" + e);
            logger.error("异常数据:" + message);
        }
    }

    /**
     * @param flag
     * @param message
     * @param channel
     */
    private void isFlagMQBasic(boolean flag, Message message, Channel channel) {
//        try {
//
//            String bm_message = new String(message.getBody(), "UTF-8");
//            // 生成当前时间字符串
//            String nowDateStr = new DateDistance().nowDateToStr(new Date());
//            if (flag) {
//                basicACK(message, channel);// 处理正常--ack
//            } else {
//                // 生成redis中Key的失效时间
//                long liveTime = new DateDistance().getMsgIdLiveTime(new Date());
//                // 获取message中的MsgId
//                String msgId = new JsonToBean().strToKeyForValue(bm_message, "msgId");
//                // redis中查询是否有该条msgId
//                String exception_msgId = redisService.get("exception-" + msgId);
//                if (exception_msgId == null) {
//                    // 将入库失败的msgId写入至redis中
//                    // 首次写库失败，redis记录msgId， 同时记录bm_message + nowDateStr
//                    redisService.set("exception-" + msgId, bm_message + " - " + nowDateStr, liveTime / 1000);
//                    // 作为异常处理，message重回MQ队列
//                    basicNACK(message, channel);
//                } else {
//                    // 第二次入库失败，redis记录exception-msgId的详细信息
//                    redisService.set("exception-" + msgId, bm_message + " - " + nowDateStr, liveTime / 1000);
//                    // 第二次处理失败跳过改信息，并记录该信息
//                    basicACK(message, channel);// 处理正常--ack
//                    logger.error(msgId + "号数据第二次入库异常，数据为：");
//                    logger.error(bm_message);
//                }
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            logger.error("MQ 取出 BM 数据错误！" + new String(message.getBody()));
//            throw new RuntimeException("MQ数据转换错误", e);
//        }
    }

}

