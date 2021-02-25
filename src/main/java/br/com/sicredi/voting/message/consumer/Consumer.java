package br.com.sicredi.voting.message.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sicredi.voting.domain.Session;
import br.com.sicredi.voting.domain.dto.session.response.SessionResponse;
import br.com.sicredi.voting.repository.SessionRepository;
import br.com.sicredi.voting.validation.Message;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Consumer {

    @Autowired
    private SessionRepository sessionRepository;
    
    @RabbitListener(queues = "${ampq.rabbitmq.session.event.queueName}")
    public void receiveMessage(SessionResponse sessionResponse) throws Exception{
        Session session=sessionRepository.findById(sessionResponse.getSessionId()).orElseThrow(Message.NOT_FOUND_SESSION::asBusinessException);
        sessionRepository.save(session);
        log.info("method=receiveMessage sessionId={}",session.getSessionId());
    }
}

