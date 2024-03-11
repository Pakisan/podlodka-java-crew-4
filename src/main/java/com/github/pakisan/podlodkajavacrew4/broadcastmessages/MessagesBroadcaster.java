package com.github.pakisan.podlodkajavacrew4.broadcastmessages;

import com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.broadcast.MessageToBroadcast;
import com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.broadcast.MessageToBroadcastProducer;
import com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.incoming.IncomingMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

@Slf4j
@Service
public class MessagesBroadcaster {

    private final LinkedList<SseEmitter> subscribers = new LinkedList<>();

    private final MessageToBroadcastProducer messageToBroadcastProducer;

    public MessagesBroadcaster(MessageToBroadcastProducer messageToBroadcastProducer) {
        this.messageToBroadcastProducer = messageToBroadcastProducer;
    }

    public void sendMessage(IncomingMessage payload) {
        var messageToBroadcast = new MessageToBroadcast(
                payload.message(),
                Date.from(Instant.now())
        );

        log.info("Sending message to broadcasting queue: {}", messageToBroadcast);
        messageToBroadcastProducer.sendMessage(messageToBroadcast);
    }

    public void subscribe(SseEmitter subscriber) {
        subscribers.add(subscriber);
    }

    public void broadcast(MessageToBroadcast messageToBroadcast) {
        log.info("Broadcasting message to next number of subscribers: {}", subscribers.size());
        subscribers.forEach(subscriber -> {
            try {
                SseEmitter.SseEventBuilder event = SseEmitter.event()
                        .data(messageToBroadcast)
                        .id(UUID.randomUUID().toString())
                        .name("message");

                subscriber.send(event);
                log.info("Message was sent: {}", event);
            } catch (Exception ex) {
                subscribers.remove(subscriber);
            }
        });
    }

}
