package br.com.randomthings.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name="_change_item")
public class ChangeItem extends DomainEntity {
	
	private Integer quantity;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "change_id")
	private Change change;
}