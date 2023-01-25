package com.distancelearning.notification.consumers;

import com.distancelearning.notification.dtos.NotificationCommandDto;
import com.distancelearning.notification.models.NotificationModel;
import com.distancelearning.notification.services.NotificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class NotificationConsumer {

    @Autowired
    NotificationService notificationService;

//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "${distancelearning.broker.queue.notificationCommandQueue.name}", durable = "true"),
//            exchange = @Exchange(value = "${distancelearning.broker.exchange.notificationCommandExchange}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
//            key = "${distancelearning.broker.key.notificationCommandKey}")
//    )
//    public void listen(@Payload NotificationCommandDto notificationCommandDto){
////        NotificationModel notificationModel = notificationCommandDto.convertToNotificationModel();
////        notificationService.saveNotification(notificationModel);
//        log.info("Entering listen method...");
//        var notificationModel = new NotificationModel();
//        BeanUtils.copyProperties(notificationCommandDto, notificationModel);
//        notificationModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
//        notificationModel.setNotificationStatus(NotificationStatus.CREATED);
//        notificationService.saveNotification(notificationModel);
//        log.info("Exiting listen method...");
//    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${distancelearning.broker.queue.notificationCommandQueue.name}", durable = "true"),
            exchange = @Exchange(value = "${distancelearning.broker.exchange.notificationCommandExchange}", type = ExchangeTypes.FANOUT, ignoreDeclarationExceptions = "true")))
    public void listen(@Payload NotificationCommandDto notificationCommandDto){
        NotificationModel notificationModel = notificationCommandDto.convertToNotificationModel();
        notificationService.saveNotification(notificationModel);
    }
}
