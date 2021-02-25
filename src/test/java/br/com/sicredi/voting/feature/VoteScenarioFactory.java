package br.com.sicredi.voting.feature;

import br.com.sicredi.voting.domain.Vote;
import br.com.sicredi.voting.domain.dto.vote.request.VoteRequest;
import br.com.sicredi.voting.domain.dto.vote.response.VoteResponse;
import br.com.sicredi.voting.domain.enums.Answer;

public class VoteScenarioFactory {
    
    public static final Vote VOTE = loadVote();
    public static final Vote VOTE_NEW = loadNewVote();
    public static final Vote VOTE_BUILDER = loadVoteBuilder();
    public static final Vote VOTE_SET = loadVoteSet();
    public static final Vote VOTE_GET = loadVoteGet();
    public static final VoteRequest VOTE_REQUEST = loadVoteRequest();
    public static final VoteResponse VOTE_RESPONSE=loadVoteResponse();

    private static Vote loadVote() {
        return new Vote(1L, Answer.YES, ScheduleScenarioFactory.SCHEDULE, AssociateScenarioFactory.ASSOCIATE);
    }

    private static Vote loadNewVote() {
        return new Vote(1L, Answer.YES, ScheduleScenarioFactory.SCHEDULE_NEW, AssociateScenarioFactory.ASSOCIATE_NEW);
    }

    private static Vote loadVoteBuilder() {
        return Vote.builder()
        .voteId(1L)
        .answer(Answer.YES)
        .schedule(ScheduleScenarioFactory.SCHEDULE)
        .associate(AssociateScenarioFactory.ASSOCIATE)
        .build();
    }

    private static Vote loadVoteSet() {
        Vote vote = new Vote();
        vote.setVoteId(1L);
        vote.setAnswer(Answer.YES);
        vote.setSchedule(ScheduleScenarioFactory.SCHEDULE);
        vote.setAssociate(AssociateScenarioFactory.ASSOCIATE);
        return vote;
    }

    private static Vote loadVoteGet() {
        Vote vote = new Vote();
        vote.getVoteId();
        vote.getAnswer();
        vote.getSchedule();
        vote.getAssociate();
        return vote;
    }

    private static VoteRequest loadVoteRequest() {
        return VoteRequest.builder()
        .scheduleId(1L)
        .sessionId(1L)
        .cpf("58218785000")
        .answer("Sim")
        .build();
    }

    private static VoteResponse loadVoteResponse() {
        return new VoteResponse("Voto salvo com sucesso!");
    }
}
