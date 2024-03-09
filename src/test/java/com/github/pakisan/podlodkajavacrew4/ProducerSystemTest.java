package com.github.pakisan.podlodkajavacrew4;

import com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.incoming.IncomingMessage;
import com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.incoming.IncomingMessageConsumer;
import io.github.springwolf.plugins.amqp.producer.SpringwolfAmqpProducer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.amqp.AmqpIOException;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

import static com.github.pakisan.podlodkajavacrew4.broadcastmessages.api.amqp.RabbitConfiguration.INCOMING_MESSAGES_QUEUE;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

/**
 * While the assertion of this test is identical to ApiIntegrationTests,
 * the setup uses a full docker-compose context with a real sqs instance.
 */
@SpringBootTest(
        classes = {PodlodkaJavaCrew4Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Testcontainers
@DirtiesContext
@TestMethodOrder(OrderAnnotation.class)
@TestPropertySource(properties = {"spring.rabbitmq.host=localhost"})
// @Ignore("Uncomment this line if you have issues running this test on your local machine.")
public class ProducerSystemTest {

    @Autowired
    SpringwolfAmqpProducer springwolfAmqpProducer;

    @SpyBean
    IncomingMessageConsumer incomingMessageConsumer;

    @Container
    public static DockerComposeContainer<?> environment =
            new DockerComposeContainer<>(new File("docker-compose.yml"))
                    .withServices("amqp");

    @Test
    @Order(1)
    void verifyAmqpIsAvailable() {
        ConnectionFactory factory = new CachingConnectionFactory("localhost");

        await().atMost(60, SECONDS).ignoreException(AmqpIOException.class).untilAsserted(() -> assertThat(
                        factory.createConnection().isOpen())
                .isTrue());
    }

    @Test
    @Order(2)
    void producerCanUseSpringwolfConfigurationToSendMessage() {
        // given
        IncomingMessage payload = new IncomingMessage("broadcast this message \uD83D\uDE80");

        // when
        springwolfAmqpProducer.send(INCOMING_MESSAGES_QUEUE, payload);

        // then
        verify(incomingMessageConsumer, timeout(10000)).receiveIncomingMessage(payload);
    }

}
