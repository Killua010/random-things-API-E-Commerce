package br.com.randomthings.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity(name="_provider")
public class Provider extends NamedEntity {
	private String cnpj;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
	private Category category;
}
