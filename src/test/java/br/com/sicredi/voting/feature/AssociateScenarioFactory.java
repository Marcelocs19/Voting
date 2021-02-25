package br.com.sicredi.voting.feature;

import br.com.sicredi.voting.domain.Associate;
import br.com.sicredi.voting.domain.dto.associate.request.AssociateRequest;

public class AssociateScenarioFactory {
    
    public static final Associate ASSOCIATE = loadAssociate();
    public static final Associate ASSOCIATE_NEW = loadNewAssociate();
    public static final Associate ASSOCIATE_BUILDER = loadAssociateBuilder();
    public static final Associate ASSOCIATE_SET = loadAssociateSet();
    public static final Associate ASSOCIATE_GET = loadAssociateGet();
    public static final AssociateRequest ASSOCIATE_REQUEST = loadAssociateRequest();

    private static Associate loadAssociate() {
        return new Associate(1L, "58218785000", null);
    }

    private static Associate loadNewAssociate() {
        return new Associate(1L, "12345678911", null);
    }

    private static Associate loadAssociateBuilder() {
        return Associate.builder()
        .associateId(2L)
        .cpf("21365487905")
        .vote(null)
        .build();
    }

    private static Associate loadAssociateSet() {
        Associate associate = new Associate();
        associate.setAssociateId(3L);
        associate.setCpf("74125896325");
        associate.setVote(null);
        return associate;
    }

    private static Associate loadAssociateGet() {
        Associate associate = new Associate();
        associate.getAssociateId();
        associate.getCpf();
        associate.getVote();
        return associate;
    }

    private static AssociateRequest loadAssociateRequest() {
        return new AssociateRequest("58218785000");
    }
}
