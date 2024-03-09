package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.incoming;

import com.github.pakisan.podlodkajavacrew4.broadcastmessages.MessagesBroadcaster;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.RabbitConfiguration.INCOMING_MESSAGES_QUEUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class IncomingMessageConsumer {

    private final MessagesBroadcaster messagesBroadcaster;

    @RabbitListener(queues = INCOMING_MESSAGES_QUEUE)
    public void receiveIncomingMessage(IncomingMessage payload) {
        log.info("Received new message in {}: {}", INCOMING_MESSAGES_QUEUE, payload.toString());

        messagesBroadcaster.sendMessage(payload);
    }

}
