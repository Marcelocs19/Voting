package br.com.sicredi.voting.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class VotingAmqpProperties extends AmqpProperties {
    
}
