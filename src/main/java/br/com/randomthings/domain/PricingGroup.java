package br.com.randomthings.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_pricing_group")
public class PricingGroup extends NamedEntity {
	
	@Column(name = "profit_percentage")
	private Float profitPercentage;
}
