package br.upe.finance.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.upe.finance.messaging.constants.BindingKeyName;
import br.upe.finance.messaging.constants.ExchangeName;
import br.upe.finance.messaging.constants.QueueName;

@Configuration
public class MessagingConfig {

    /// Resources ///

    @Bean
    MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    TopicExchange resourcesExchange() {
        return new TopicExchange(ExchangeName.RESOURCES);
    }

    @Bean
    Queue resourcesRequestQueue() {
        return new Queue(QueueName.RESOURCES_REQUEST);
    }

    @Bean
    Binding resourcesRequestBinding(
        Queue resourcesRequestQueue,
        TopicExchange resourcesExchange) {
        return BindingBuilder
            .bind(resourcesRequestQueue)
            .to(resourcesExchange)
            .with(BindingKeyName.RESOURCES_REQUEST_FINANCE);
    }

    /// Appointments ///

    @Bean
    TopicExchange appointmentsExchange() {
        return new TopicExchange(ExchangeName.APPOINTMENTS);
    }

    @Bean
    Queue appointmentsRegisterQueue() {
        return new Queue(QueueName.APPOINTMENTS_REGISTER);
    }

    @Bean
    Binding appointmentsRegisterBinding(
        Queue appointmentsRegisterQueue,
        TopicExchange appointmentsExchange) {
        return BindingBuilder
            .bind(appointmentsRegisterQueue)
            .to(appointmentsExchange)
            .with(BindingKeyName.APPOINTMENTS_CONFIRMED_FINANCE);
    }
}
