package br.com.sicredi.voting.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.sicredi.voting.domain.dto.vote.request.VoteRequest;
import br.com.sicredi.voting.domain.enums.Answer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "voteId", callSuper = false)
@Builder
@Setter
@Entity
@Table(name = "TB_VOTO")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_VOTO", nullable = false)
    private Long voteId;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESPOSTA", nullable = false)
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "PAUTA", referencedColumnName = "CD_PAUTA")
    private Schedule schedule;

    @OneToOne
    private Associate associate;

    public static Vote of(VoteRequest request, Associate associate, Schedule schedule) {
        return Vote.builder()
        .answer((request.getAnswer().equals("Sim")) ? Answer.YES : Answer.NO)
        .associate(associate)
        .schedule(schedule)
        .build();
    }

}
