# Desafio projeto de pautas [![Build Status](https://www.travis-ci.com/Marcelocs19/Voting.svg?branch=master)](https://www.travis-ci.com/github/Marcelocs19/Voting)

### Enunciado 
No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. Imagine que você deve criar uma solução backend para gerenciar essas sessões de votação.

Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST: 

⦁	Cadastrar uma nova pauta
⦁	Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
⦁	Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
⦁	Contabilizar os votos e dar o resultado da votação na pauta


### Requisitos
Para compilar e rodar está aplicação você precisa:
* [Lombok](https://projectlombok.org/download)

* [Postgre](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)

* [Java 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)

* [Maven](https://maven.apache.org/download.cgi)

### Clonar o projeto
Você pode clonar este repositório, pelo git usando:
```
https://github.com/Marcelocs19/Voting.git
```
### Postgre
```
1. Criar o database voting;
```
```
2. Trocar a senha do banco Postgre no arquivo application.properties;
```


### Rodar a aplicação localmente
Existem varias jeitos para rodar uma aplicação Spring Boot no seu computador. Um jeito é executar a classe main VottingApplication pela sua IDE.

Ou você pode rodar a aplicação pela linha de comando usando:

```
mvn spring-boot:run
```

### Rotas
1. Método para listar todas as Pautas:
Get - http://localhost:8080/v1/schedules - Localmente

ou

Get - https://voting-sicredi.herokuapp.com/v1/schedules - Heroku

2. Método para listar todas as Sessões:
Get - http://localhost:8080/v1/sessions/listSessions - Localmente

ou

Get - https://voting-sicredi.herokuapp.com/v1/sessions/listSessions - Heroku

3. Método para listar uma Sessão
Get - http://localhost:8080/v1/sessions?sessionId=1 - Localmente

ou

Get - https://voting-sicredi.herokuapp.com/v1/sessions/listSessions - Heroku

3. Método para cadastrar um Associado:
Post - http://localhost:8080/v1/associates - Localmente

ou

Post - https://voting-sicredi.herokuapp.com/v1/associates - Heroku
```
Exemplo body json:
{
    "cpf": "77851829062"
}
```


4. Método para cadastrar uma Pauta:
Post - http://localhost:8080/v1/schedules - Localmente

ou

Post - https://voting-sicredi.herokuapp.com/v1/schedules - Heroku
```
Exemplo body json:
{
    "title": "Teste 1",
    "subject": "Teste 1"
}
```

5. Método para cadastrar uma Sessão:
Post - http://localhost:8080/v1/sessions - Localmente

ou

Post - https://voting-sicredi.herokuapp.com/v1/sessions - Heroku
```
Exemplo body json:
{    
    "scheduleId":[1]
}
```

6. Método para cadastrar um Voto:
Post - http://localhost:8080/v1/vote - Localmente

ou

Post - https://voting-sicredi.herokuapp.com/v1/vote - Heroku

```
Exemplo body json:
{
    "scheduleId": 1,
    "sessionId": 1,
    "cpf": "02064934014",
    "answer": "Sim"
}
```

7. Swagger
Browser - http://localhost:8080/v1/swagger-ui.html - Localmente

ou

Browser - voting-sicredi.herokuapp.com/v1/swagger-ui.html - Heroku


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
