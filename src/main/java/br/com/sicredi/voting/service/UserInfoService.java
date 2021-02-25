package br.com.sicredi.voting.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.sicredi.voting.validation.Message;

@Service
public class UserInfoService {

    public boolean checkAssociateCanVote(String cpf) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response;
        try {
            response = restTemplate.getForEntity("https://user-info.herokuapp.com/users/" + cpf,
                    String.class);
        } catch (Exception e) {
            throw Message.NOT_FOUND_VOTE.asBusinessException();
        }
        String body = response.getBody();
        if(body.contains("ABLE_TO_VOTE")) {
            return true;
        } else {
            return false;
        }
    }
    
}
