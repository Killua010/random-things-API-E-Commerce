package br.com.randomthings.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_inactivation")
public class Inactivation extends DomainEntity {
	
	@Column(length = 100)
	private String description;
	
	@Column(name = "status_inactivation", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusInactivation statusInactivation;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
	private Product product;
}
