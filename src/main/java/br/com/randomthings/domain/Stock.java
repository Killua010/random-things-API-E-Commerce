package br.com.randomthings.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name="_stock")
public class Stock extends DomainEntity {
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
	private Product product;
	
	private Integer totalQuantity;
	
	@OneToMany(mappedBy="stock")
	private Set<StockInput> stockInputs = new HashSet<>(); 
}
