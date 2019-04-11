package br.com.randomthings.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	
	@ManyToOne (fetch=FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToMany(mappedBy="subCategory")
	private List<Product> products = new ArrayList<Product>();
}
