package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.broadcast;

import com.github.pakisan.podlodkajavacrew4.broadcastmessages.MessagesBroadcaster;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.RabbitConfiguration.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageToBroadcastConsumer {

    private final MessagesBroadcaster messagesBroadcaster;

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            exchange = @Exchange(name = MESSAGES_TO_BROADCAST_EXCHANGE, type = ExchangeTypes.TOPIC),
                            value =
                            @Queue(
                                    name = MESSAGES_TO_BROADCAST_QUEUE,
                                    durable = "false",
                                    exclusive = "false",
                                    autoDelete = "false"
                            ),
                            key = MESSAGES_TO_BROADCAST_ROUTING_KEY)
            }
    )
    public void receiveIncomingMessage(MessageToBroadcast payload) {
        log.info("Received new message in {}: {}", MESSAGES_TO_BROADCAST_QUEUE, payload.toString());

        messagesBroadcaster.broadcast(payload);
    }

}
