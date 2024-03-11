package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.rest;

import com.github.pakisan.podlodkajavacrew4.broadcastmessages.MessagesBroadcaster;
import com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.incoming.IncomingMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@Tag(name = "broadcast messages")
public class BroadcastMessageController {

    private final MessagesBroadcaster messagesBroadcaster;

    public BroadcastMessageController(MessagesBroadcaster messagesBroadcaster) {
        this.messagesBroadcaster = messagesBroadcaster;
    }

    @Operation(
            summary = "broadcast message",
            description = "Send message for further broadcasting to subscribed users"
    )
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponse(responseCode = "200", description = "Message sent successfully")
    public ResponseEntity<IncomingMessage> broadcastMessage(@RequestBody IncomingMessage incomingMessage) {
        messagesBroadcaster.sendMessage(incomingMessage);

        return ResponseEntity.ok(incomingMessage);
    }

}
