package br.com.sicredi.voting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.sicredi.voting.domain.Session;
import br.com.sicredi.voting.domain.enums.Status;
import br.com.sicredi.voting.message.producer.Producer;
import br.com.sicredi.voting.repository.SessionRepository;
import br.com.sicredi.voting.validation.Message;

@Service
public class CloseSessionService {

    @Value("${ampq.rabbitmq.session.event.queueName}")
    private String queue;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private Producer producer;

    public void close(Long id) {
        Session response = sessionRepository.findById(id).orElseThrow(Message.NOT_FOUND_SESSION::asBusinessException);
        response.setStatus(Status.CLOSE);
        producer.publish(response.toDto(), queue);
    }
}
