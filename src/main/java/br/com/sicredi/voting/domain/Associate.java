package br.com.sicredi.voting.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
@EqualsAndHashCode(of="associateId", callSuper = false)
@Builder
@Setter
@Entity 
@Table(name = "TB_ASSOCIADO")
public class Associate {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_ASSOCIADO")
	private Long associateId;

    @Column(name = "CPF", nullable = false, columnDefinition = "VARCHAR(11)")
    private String cpf;

    @OneToOne(mappedBy = "associate", cascade = CascadeType.ALL, optional = true)
    private Vote vote;

}
