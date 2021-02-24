package br.com.sicredi.voting.validation;

import org.springframework.http.HttpStatus;

import br.com.sicredi.voting.exception.BusinessException;

public enum Message {

    NOT_FOUND_ASSOCIATE("Associado não encontrado", HttpStatus.NOT_FOUND),
    NOT_FOUND_SCHEDULE("Pauta não encontrada", HttpStatus.NOT_FOUND),
    NOT_FOUND_VOTE("Cpf inválido", HttpStatus.NOT_FOUND),
    NOT_FOUND_SESSION("Sessão não encontrada", HttpStatus.NOT_FOUND),
    NOT_FOUND_SCHEDULE_AT_SESSION("Pauta não encontrada nessa sessão", HttpStatus.NOT_FOUND),
    BAD_REQUEST_VOTE("Associado não pode votar", HttpStatus.BAD_REQUEST) ;

    private String value;
	private String description;
	private HttpStatus statusCode;

	private Message(String value, HttpStatus statusCode) {
		this.value = value;
		this.statusCode = statusCode;
	}

	private Message(String value, String description, HttpStatus statusCode) {
		this.value = value;
		this.description = description;
		this.statusCode = statusCode;
	}

	private Message(String value) {
		this.value = value;
	}

	public String getMessage() {
		return this.value;
	}

	public HttpStatus getStatus() {
		return this.statusCode;
	}

	public String getDescription() {
		return description;
	}

	public BusinessException asBusinessException() {
		return BusinessException.builder().httpStatusCode(this.getStatus())
				.code(String.valueOf(this.getStatus().value())).message(this.getMessage())
				.description(this.getDescription()).build();
	}
    
}
