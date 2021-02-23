package br.com.sicredi.voting.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sicredi.voting.domain.dto.schedule.request.ScheduleRequest;
import br.com.sicredi.voting.domain.dto.schedule.response.ScheduleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "scheduleId", callSuper = false)
@Builder
@Setter
@Entity
@Table(name = "TB_PAUTA")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_PAUTA", nullable = false)
    private Long scheduleId;

    @Column(name = "TITULO", nullable = false, columnDefinition = "VARCHAR(50)")
    private String title;

    @Column(name = "ASSUNTO", nullable = false, columnDefinition = "VARCHAR(100)")
    private String subject;

    @JsonIgnore
    @ManyToOne(optional = true)
    @JoinColumn(name = "CD_SESSAO", referencedColumnName = "CD_SESSAO")
    private Session session;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vote> vote;

    public static Schedule of(ScheduleRequest request) {
        return Schedule.builder().title(request.getTitle()).subject(request.getSubject()).build();
    }

    public ScheduleResponse toDto() {
        return ScheduleResponse.builder().scheduleId(this.scheduleId).subject(this.subject).title(this.title).build();
    }

}
