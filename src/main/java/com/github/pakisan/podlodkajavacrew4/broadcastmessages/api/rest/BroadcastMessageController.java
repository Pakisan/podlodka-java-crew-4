package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.rest;

import com.github.pakisan.podlodkajavacrew4.broadcastmessages.MessagesBroadcaster;
import com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.incoming.IncomingMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class BroadcastMessageController {

    private final MessagesBroadcaster messagesBroadcaster;

    public BroadcastMessageController(MessagesBroadcaster messagesBroadcaster) {
        this.messagesBroadcaster = messagesBroadcaster;
    }

    @PostMapping
    public ResponseEntity<IncomingMessage> broadcastMessage(@RequestBody IncomingMessage incomingMessage) {
        messagesBroadcaster.sendMessage(incomingMessage);

        return ResponseEntity.ok(incomingMessage);
    }

}
