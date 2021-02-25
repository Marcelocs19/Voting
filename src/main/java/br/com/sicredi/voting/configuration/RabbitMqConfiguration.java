package br.com.sicredi.voting.configuration;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMqConfiguration {
 
    @Value("${ampq.rabbitmq.session.event.queueName}")
    private String queue;
 
    @Bean(name = "votingConnectFactory")
    @Primary
    public ConnectionFactory votingConnectFactory(VotingAmqpProperties amqpConfig){
        CachingConnectionFactory connectionFactory=new CachingConnectionFactory();
        connectionFactory.setUri(amqpConfig.getAddresses());
        connectionFactory.setUsername(amqpConfig.getUsername());
        connectionFactory.setPassword(amqpConfig.getPassword());
        return connectionFactory;
    }

    @Bean(name = "votingRabbitTemplate")
	@Primary
    public RabbitTemplate votingRabbitTemplate(@Qualifier("votingConnectFactory") ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
	    rabbitTemplate.setEncoding("UTF-8");
        return rabbitTemplate;
    }

    @Bean	
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
		rabbitAdmin.setAutoStartup(true);
		rabbitAdmin.declareQueue(queueVoting());
		return rabbitAdmin;
	}

    public Queue queueVoting() {				
		return QueueBuilder.durable(queue).build();
	}
    
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
