package com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitConfiguration {

    public static final String INCOMING_MESSAGES_QUEUE           = "incoming-messages-queue";
    public static final String INCOMING_MESSAGES_EXCHANGE        = "incoming-messages-exchange";
    public static final String INCOMING_MESSAGES_ROUTING_KEY     = "messages-to-broadcast-routing-key";

    public static final String MESSAGES_TO_BROADCAST_QUEUE       = "messages-to-broadcast-queue";
    public static final String MESSAGES_TO_BROADCAST_EXCHANGE    = "messages-to-broadcast-exchange";
    public static final String MESSAGES_TO_BROADCAST_ROUTING_KEY = "messages-to-broadcast-routing-key";

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue messagesToBroadcastQueue() {
        return new Queue(MESSAGES_TO_BROADCAST_QUEUE, false);
    }

    @Bean
    public Exchange messagesToBroadcastExchange() {
        return new TopicExchange(MESSAGES_TO_BROADCAST_EXCHANGE);
    }

    @Bean
    public Binding messagesToBroadcastBinding(Queue messagesToBroadcastQueue, Exchange messagesToBroadcastExchange) {
        return BindingBuilder.bind(messagesToBroadcastQueue)
                .to(messagesToBroadcastExchange)
                .with(MESSAGES_TO_BROADCAST_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Queue incomingMessagesQueue() {
        return new Queue(INCOMING_MESSAGES_QUEUE, false);
    }

    @Bean
    public Exchange incomingMessagesExchange() {
        return new TopicExchange(INCOMING_MESSAGES_EXCHANGE);
    }

    @Bean
    public Binding incomingMessagesBinding(Queue incomingMessagesQueue, Exchange incomingMessagesExchange) {
        return BindingBuilder.bind(incomingMessagesQueue)
                .to(incomingMessagesExchange)
                .with(INCOMING_MESSAGES_ROUTING_KEY)
                .noargs();
    }

}
