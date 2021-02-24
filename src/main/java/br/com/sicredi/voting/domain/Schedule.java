package br.com.sicredi.voting.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.sicredi.voting.domain.dto.schedule.request.ScheduleRequest;
import br.com.sicredi.voting.domain.dto.schedule.response.ScheduleResponse;
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

    @JsonIgnore
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vote> vote;

    @Transient
    private Integer totalAnswerYes;

    @Transient
    private Integer totalAnswerNo;

    public static Schedule of(ScheduleRequest request) {
        return Schedule.builder().title(request.getTitle()).subject(request.getSubject()).build();
    }

    public ScheduleResponse toDto() {
        return ScheduleResponse.builder().scheduleId(this.scheduleId).subject(this.subject).title(this.title).totalAnswerYes(0).totalAnswerNo(0).build();
    }

    public static List<ScheduleResponse> toList(List<Schedule> schedules) {        
        List<ScheduleResponse> scheduleResponse = new ArrayList<>();
        schedules.forEach(s -> {          
            scheduleResponse.add(ScheduleResponse.builder().scheduleId(s.getScheduleId()).subject(s.getSubject()).title(s.getTitle()).totalAnswerYes(s.getTotalAnswerYes()).totalAnswerNo(s.getTotalAnswerNo()).build());
        });            
        return scheduleResponse;        
    }

    public Integer getTotalAnswerYes() {
        return vote.stream().filter(v -> v.getAnswer().equals(Answer.YES)).collect(Collectors.toList()).size();
    }

    public Integer getTotalAnswerNo() {
        return vote.stream().filter(v -> v.getAnswer().equals(Answer.NO)).collect(Collectors.toList()).size();
    }
}
