package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.broadcast;

import com.github.pakisan.podlodkajavacrew4.broadcastmessages.MessagesBroadcaster;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.RabbitConfiguration.MESSAGES_TO_BROADCAST_QUEUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageToBroadcastConsumer {

    private final MessagesBroadcaster messagesBroadcaster;

    @RabbitListener(queues = MESSAGES_TO_BROADCAST_QUEUE)
    public void receiveIncomingMessage(MessageToBroadcast payload) {
        log.info("Received new message in {}: {}", MESSAGES_TO_BROADCAST_QUEUE, payload.toString());

        messagesBroadcaster.sendMessage(payload);
    }

}
