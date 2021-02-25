package br.com.sicredi.voting.message.producer;
import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import br.com.sicredi.voting.domain.dto.session.response.SessionResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Producer {
    
    @Resource(name = "votingRabbitTemplate")
	private RabbitTemplate rabbitTemplate;

    public void publish(SessionResponse session,String queueName){
        rabbitTemplate.convertAndSend(queueName, session);
		log.info("method=publish queueName={}", queueName);
    }
}

