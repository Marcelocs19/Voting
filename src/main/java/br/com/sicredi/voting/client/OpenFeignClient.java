package br.com.sicredi.voting.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://user-info.herokuapp.com/users/", name = "vote")
public interface OpenFeignClient {
    
    @GetMapping("{cpf}")
    StatusVote canVote(@PathVariable("cpf") String cpf);
}
