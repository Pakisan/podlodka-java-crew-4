package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.broadcast;

import io.github.springwolf.core.asyncapi.annotations.AsyncOperation;
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher;
import io.github.springwolf.plugins.amqp.asyncapi.annotations.AmqpAsyncOperationBinding;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.RabbitConfiguration.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageToBroadcastProducer {

    private final RabbitTemplate rabbitTemplate;

    @AsyncPublisher(
            operation = @AsyncOperation(
                    channelName = MESSAGES_TO_BROADCAST_QUEUE,
                    description = "Publish broadcasting ready message"
            )
    )
    @AmqpAsyncOperationBinding()
    public void sendMessage(MessageToBroadcast message) {
        log.info("Sending message to broadcast: {}", message.toString());
        rabbitTemplate.convertAndSend(MESSAGES_TO_BROADCAST_EXCHANGE, MESSAGES_TO_BROADCAST_ROUTING_KEY, message);
    }

}
