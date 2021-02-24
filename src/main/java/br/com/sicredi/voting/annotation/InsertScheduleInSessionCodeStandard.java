package br.com.sicredi.voting.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;

import br.com.sicredi.voting.domain.dto.schedule.response.ScheduleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pauta adicionada", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ScheduleResponse.class))),
        @ApiResponse(responseCode = "500", description = "Sistema indisponível", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
@Operation(summary = Constants.SESSION_INSERT_SCHEDULE_SUMMARY, description = Constants.SESSION_INSERT_SCHEDULE_DESCRIPTION)
public @interface InsertScheduleInSessionCodeStandard {
    
}
