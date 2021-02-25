package br.com.sicredi.voting.job;

import br.com.sicredi.voting.service.CloseSessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CloseSession implements Runnable {

	private Long sessionId;

	private CloseSessionService service;

	@Override
	public void run() {
		log.info("method=close sessionId={}", sessionId);
		service.close(sessionId);
	}

}
