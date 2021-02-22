package br.com.sicredi.voting.domain.enums;

public enum Answer {
    
    YES("Sim"), NO("Não");

    private final String description;

    private Answer(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
}
