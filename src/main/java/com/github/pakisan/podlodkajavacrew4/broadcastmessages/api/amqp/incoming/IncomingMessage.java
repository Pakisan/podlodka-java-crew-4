package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.incoming;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Message received by application for further broadcasting"
)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonClassDescription("Message received by application for further broadcasting")
public record IncomingMessage(

        @Schema(
                name = "message",
                description = "Ordinary text which will be send",
                example = "broadcast this message \uD83D\uDE80",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @JsonProperty(value="message", required=true)
        @JsonPropertyDescription("Ordinary text which will be send")
        String message

) {
}
