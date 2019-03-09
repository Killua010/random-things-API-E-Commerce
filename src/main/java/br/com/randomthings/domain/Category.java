package br.com.randomthings.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_category")
public class Category extends NamedEntity {
	
	@OneToMany(mappedBy="category")
	private List<SubCategory> subCategories;
}
