package br.com.randomthings.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_sub_category")
public class SubCategory extends NamedEntity {
	
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	private Category category;
}
