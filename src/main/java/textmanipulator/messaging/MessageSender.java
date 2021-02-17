package textmanipulator.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;

import java.util.concurrent.TimeUnit;

public class MessageSender implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final MessageReceiver messageReceiver;

    public MessageSender(MessageReceiver messageReceiver, RabbitTemplate rabbitTemplate) {
        this.messageReceiver = messageReceiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        String message = "Hello from RabbitMQ!";
        String inputFileName, outputFileName, action;
        if (args.length == 3) {
            inputFileName = args[0];
            outputFileName = args[1];
            action = args[2];
            message = "Executing action: " + action +
                    ", Input file: " + inputFileName +
                    ", Output file: " + outputFileName;
        }
        System.out.println("Sending message... " + message);
        rabbitTemplate.convertAndSend(MessagingConfig.topicExchangeName, MessagingConfig.routingKey, message);
        messageReceiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
