package br.com.sicredi.voting.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
		  info = @Info(
		  title = "Voting",
		  description = "" +
		    "Documentção da api ",
		  contact = @Contact(
		    name = "Marcelo Campos" ,  
			email = "marcelo.campos@ntconsult.com.br"			
		  ),
		  license = @License(
		    name = "MIT Licence")),
		  servers = @Server(url = "http://localhost:8080/v1"))
public class Swagger {
    
}
