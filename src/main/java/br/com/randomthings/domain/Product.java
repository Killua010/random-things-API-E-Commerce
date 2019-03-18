package br.com.randomthings.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="_product")
public class Product extends NamedEntity {
	
	@Column(length = 10000)
	private String description;
	
	@Column(length = 255, name="bar_code")
	private String barCode;
	
	private Float price;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pricing_group_id")
	private PricingGroup pricingGroup;
	
	@JoinTable(name = "_product_sub_category", joinColumns = {
			@JoinColumn(name = "product_id2", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "sub_category_id", referencedColumnName = "id") })
	@ManyToMany(cascade = CascadeType.REFRESH	, fetch = FetchType.EAGER)
	private Set<SubCategory> subCategory = new HashSet<SubCategory>();
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL, orphanRemoval = true)
	private Set<TechnicalRow> technicalRows = new HashSet<TechnicalRow>();
}
