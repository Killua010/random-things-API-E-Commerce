package br.com.randomthings.viewhelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.PricingGroup;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.domain.TechnicalField;
import br.com.randomthings.domain.TechnicalRow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class ProductViewHelper extends EntityViewHelper {

	@NotNull(message="O nome do produto é obrigatório")
	@NotEmpty(message="O nome do produto é obrigatório")
	@Size(min=3, max=100, message="O nome deve ter entre 3 e 100 caracteres")
	private String name;
	
	@NotNull(message="A descrição do produto é obrigatório")
	@NotEmpty(message="A descrição do produto é obrigatório")
	@Size(min=5, max=10000, message="a descrição deve ter entre 5 e 100 caracteres")
	private String description;

	@NotNull(message="O código de barras do produto é obrigatório")
	@NotEmpty(message="O código de barras do produto é obrigatório")
	@Size(max=255, message="O código de barras deve ter no máximo 255 caracteres")
	private String barCode;
	
	private Float price;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message="O id da categoria de preço é obrigatório")
	@Min(value=1, message="O id informado é invalido")
	private Long pricingGroupId;
	
	private PricingGroupViewHelper pricingGroup;
	
	private List<SubCategoryViewHelper> subCategories;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long[] subCategoryId;
	
	private List<TechnicalRowViewHelper> technicalRow;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String[] descriptionField;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long[] technicalFieldId;
	
	@Override
	public DomainEntity getEntity() {
		Product product = new Product();
		PricingGroup pricingGroup = new PricingGroup();
		pricingGroup.setId(pricingGroupId);
		product.setPricingGroup(pricingGroup);
		if(null != subCategoryId) {
			for(Long id: subCategoryId) {
				SubCategory subCategory = new SubCategory();
				subCategory.setId(id);
				product.getSubCategory().add(subCategory);
			}
		}
		
		if(null != technicalFieldId) {
			for(int i = 0; i < technicalFieldId.length; i++) {
				TechnicalRow technicalRow = new TechnicalRow();
				TechnicalField field = new TechnicalField();
				field.setId(technicalFieldId[i]);
				technicalRow.setDescription(descriptionField[i]);
				technicalRow.setField(field);
				technicalRow.setId(technicalFieldId[i]);
				product.getTechnicalRows().add(technicalRow);
			}	
		}
		product.setName(name);
		product.setBarCode(barCode);
		product.setDescription(description);
		return product;
	}
	
	@Override
	public DomainEntity getEntity(Long id) {
		Boolean status = (null == this.status) ? null : this.status;
		Product product = new Product();
		PricingGroup pricingGroup = new PricingGroup();
		pricingGroup.setId(pricingGroupId);
		product.setPricingGroup(pricingGroup);
		product.setName(name);
		product.setId(id);
		product.setStatus(status);
		if(null != subCategoryId) {
			for(Long idsubCat: subCategoryId) {
				SubCategory subCategory = new SubCategory();
				subCategory.setId(idsubCat);
				product.getSubCategory().add(subCategory);
			}
		}
		
		if(null != technicalFieldId) {
			List<TechnicalRow> rows = new ArrayList<TechnicalRow>();
			for(int i = 0; i < technicalFieldId.length; i++) {
				TechnicalRow technicalRow = new TechnicalRow();
				TechnicalField field = new TechnicalField();
				field.setId(technicalFieldId[i]);
				technicalRow.setDescription(descriptionField[i]);
				technicalRow.setField(field);
				technicalRow.setId(technicalFieldId[i]);
				rows.add(technicalRow);
			}
			Set<TechnicalRow> actualRows = new HashSet<TechnicalRow>(rows);
			product.setTechnicalRows(actualRows);
		}
		
		product.setName(name);
		product.setBarCode(barCode);
		product.setDescription(description);
		return product;
	}

	@Override
	public List<EntityViewHelper> getListViewHelper(List<DomainEntity> entities) {
		List<EntityViewHelper> dtos = new ArrayList<EntityViewHelper>();
		for(DomainEntity entity : entities) {
			dtos.add(getViewHelper(entity));
		}
		return dtos;
	}

	@Override
	public EntityViewHelper getViewHelper(DomainEntity product) {
		ProductViewHelper productViewHelper = new ProductViewHelper();
		productViewHelper.setId(product.getId());
		productViewHelper.setStatus(product.getStatus());
		productViewHelper.setCreationDate(product.getCreationDate());
		productViewHelper.setLastUpdate(product.getLastUpdate());
		productViewHelper.setName(((Product)product).getName());
		productViewHelper.setBarCode(((Product)product).getBarCode());
		productViewHelper.setDescription(((Product)product).getDescription());
		productViewHelper.setPricingGroup((PricingGroupViewHelper) new PricingGroupViewHelper().getViewHelper(((Product)product).getPricingGroup()));
		productViewHelper.setSubCategories(new ArrayList<>());
		for(SubCategory subCategory: ((Product)product).getSubCategory()) {
			productViewHelper.getSubCategories().add((SubCategoryViewHelper) new SubCategoryViewHelper().getViewHelper(subCategory));
		}
		productViewHelper.setTechnicalRow(new ArrayList<>());
		for(TechnicalRow technicalRow: ((Product)product).getTechnicalRows()) {
			productViewHelper.getTechnicalRow().add((TechnicalRowViewHelper) new TechnicalRowViewHelper().getViewHelper(technicalRow));
		}
		return productViewHelper;
	}

}
