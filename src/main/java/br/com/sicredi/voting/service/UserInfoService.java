package br.com.sicredi.voting.service;

import org.springframework.stereotype.Service;

import br.com.sicredi.voting.client.OpenFeignClient;
import br.com.sicredi.voting.client.StatusVote;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserInfoService {

    private OpenFeignClient openFeign;

    public boolean checkAssociateCanVote(String cpf) {
        StatusVote canVote = openFeign.canVote(cpf);
        if(canVote.getStatus().equals("ABLE_TO_VOTE")) {
            return true;
        } else {
            return false;
        }
    }
    
}
