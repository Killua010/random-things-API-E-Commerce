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
@Entity(name="_activation")
public class Activation extends DomainEntity {
	
	@Column(length = 100)
	private String description;
	
	@Column(name = "status_activation", nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusActivation statusActivation;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
	private Product product;
}
