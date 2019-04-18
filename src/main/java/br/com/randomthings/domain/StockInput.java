package br.com.randomthings.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity(name="_stock_input")
@Data
public class StockInput extends DomainEntity{
	
	private Integer quantity;
	
	private LocalDateTime inputDate;
	
	private Float value;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
	private Stock stock;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
	private Provider provider;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
	private Product product;
}
