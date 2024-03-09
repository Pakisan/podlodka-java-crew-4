package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.sse;

import com.github.pakisan.podlodkajavacrew4.broadcastmessages.MessagesBroadcaster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequestMapping("/messages")
public class ReceiveMessageController {

    private final MessagesBroadcaster messagesBroadcaster;

    public ReceiveMessageController(MessagesBroadcaster messagesBroadcaster) {
        this.messagesBroadcaster = messagesBroadcaster;
    }

    @GetMapping
    public SseEmitter broadcastMessage() {
        SseEmitter emitter = new SseEmitter(600_000L); // 20 minutes
        messagesBroadcaster.subscribe(emitter);
        log.info("New subscription");

        return emitter;
    }

}
