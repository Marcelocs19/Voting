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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of="scheduleId", callSuper = false)
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

    @ManyToOne(optional = true)
    @JoinColumn(name = "PAUTA", referencedColumnName = "CD_SESSAO")
    private Session session;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vote> vote;
    
}
