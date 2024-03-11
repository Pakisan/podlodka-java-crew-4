package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.incoming;

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
public class IncomingMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    @AsyncPublisher(
            operation = @AsyncOperation(
                    channelName = INCOMING_MESSAGES_QUEUE,
                    description = "Publish message to broadcast"
            )
    )
    @AmqpAsyncOperationBinding()
    public void sendMessage(IncomingMessage message) {
        log.info("Publish message for broadcast: {}", message);
        rabbitTemplate.convertAndSend(INCOMING_MESSAGES_EXCHANGE, INCOMING_MESSAGES_ROUTING_KEY, message);
    }

}
