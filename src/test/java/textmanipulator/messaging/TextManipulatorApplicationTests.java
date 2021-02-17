package textmanipulator.messaging;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class TextManipulatorApplicationTests {

	@MockBean
	private MessageSender runner;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private MessageReceiver messageReceiver;

	@Test
	void contextLoads() {
	}

	@Test
	public void test() throws Exception {
		try {
			rabbitTemplate.convertAndSend(MessagingConfig.queueName,
					"Hello from RabbitMQ!");
			messageReceiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		}
		catch (AmqpConnectException e) {
			// ignore - rabbit is not running
		}
	}

}
