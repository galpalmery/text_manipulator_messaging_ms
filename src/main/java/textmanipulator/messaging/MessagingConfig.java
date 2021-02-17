package textmanipulator.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    static final String topicExchangeName = "text-manipulator-topic";
    static final String routingKey = "text.manipulator.action";
    static final String queueName = "tm-queue";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("text.manipulator.#");
    }

//    @Bean
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queueName);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }

//    @Bean
//    MessageListenerAdapter listenerAdapter(MessageReceiver messageReceiver) {
//        return new MessageListenerAdapter(messageReceiver, "receiveMessage");
//    }

    @Bean
    MessageReceiver receiver() {
        return new MessageReceiver();
    }

    @Bean
    MessageSender runner(MessageReceiver messageReceiver, RabbitTemplate rabbitTemplate) {
        return new MessageSender(messageReceiver, rabbitTemplate);
    }
}
