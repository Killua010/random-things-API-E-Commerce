package br.com.randomthings.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_technical_row")
public class TechnicalRow extends DomainEntity {
	
	@OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
    @JoinColumn(name = "technical_field_id")
	private TechnicalField field;
	
	@Column(length = 100)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
}
