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
			@JoinColumn(name = "product_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "sub_category_id", referencedColumnName = "id") })
	@ManyToMany(cascade = CascadeType.REFRESH	, fetch = FetchType.EAGER)
	private Set<SubCategory> subCategory = new HashSet<SubCategory>();
	
	@OneToMany(mappedBy="product", cascade=CascadeType.ALL)
	private Set<TechnicalRow> technicalRows = new HashSet<TechnicalRow>();
	
	@JoinTable(name = "_images_product", joinColumns = {
			@JoinColumn(name = "product_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "image_id", referencedColumnName = "id") })
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<Image> imagens = new HashSet<>();
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_id")
	private Stock stock;
}
