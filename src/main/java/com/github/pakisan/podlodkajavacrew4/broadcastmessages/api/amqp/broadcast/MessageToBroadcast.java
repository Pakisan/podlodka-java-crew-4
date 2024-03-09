package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.broadcast;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

@Schema(
        description = "Broadcasting ready message"
)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonClassDescription("Broadcasting ready message")
public record MessageToBroadcast(

        @Schema(
                name = "message",
                description = "Ordinary text which will be send",
                example = "broadcast this message \uD83D\uDE80",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @JsonProperty(value="message", required=true)
        @JsonPropertyDescription("Ordinary text which will be send")
        String message,

        @Schema(
                name = "receivedAt",
                description = "Date-time when application received this message",
                example = "2023-08-31T15:28:21.283+00:00",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @JsonProperty(value="receivedAt", required=true)
        @JsonPropertyDescription("Date-time when application received this message")
        Date receivedAt

) {
}
