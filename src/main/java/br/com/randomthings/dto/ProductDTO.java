package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.Image;
import br.com.randomthings.domain.PricingGroup;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.domain.TechnicalField;
import br.com.randomthings.domain.TechnicalRow;
import br.com.randomthings.utils.SystemVariable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class ProductDTO extends AbstractDTO<Product> {

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
	
	@JsonProperty(access = Access.READ_ONLY)
	private Float price;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Integer totalPage;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message="O id da categoria de preço é obrigatório")
	@Min(value=1, message="O id informado é invalido")
	private String pricingGroupId;
	
	@JsonProperty(access = Access.READ_ONLY)
	private PricingGroupDTO pricingGroup;
	
	@JsonProperty(access = Access.READ_ONLY)
	private List<SubCategoryDTO> subCategories;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String[] subCategoryId;
	
	@JsonProperty(access = Access.READ_ONLY)
	private List<TechnicalRowDTO> technicalRow;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String[] descriptionField;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String[] technicalFieldId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	protected MultipartFile[] images;
	
	@JsonProperty(access = Access.READ_ONLY)
	private List<String> imgSrc;
	
	private Integer stockQuantity;

	@Override
	public IDTO from(Product product) {
		ProductDTO productDTO = new ProductDTO();
		this.from(product, productDTO);
		
		productDTO.setName(product.getName());
		productDTO.setBarCode(product.getBarCode());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setPricingGroup((PricingGroupDTO) new PricingGroupDTO().from(product.getPricingGroup()));
		productDTO.setStockQuantity(product.getStock().getTotalQuantity());
		productDTO.setTechnicalRows(product.getTechnicalRows());
		productDTO.setSubCategories(product.getSubCategory());
		
		if(product.getImagens().size() > 0) {
			productDTO.setImgSrc(new ArrayList<>());
			for(Image image: product.getImagens()) {
				
				String path = SystemVariable.systemPath + "/images/" + image.getId();
				productDTO.getImgSrc().add(path);
			}
		}
		
		return productDTO;
	}

	@Override
	public Product fill(Long... params) {
		Product product = new Product();
		PricingGroup pricingGroup = new PricingGroup();
		
		if(pricingGroupId != null) {
			pricingGroup.setId(Long.parseLong(pricingGroupId));
		}
		
		if(null != images && images.length > 0) {
			for(int i = 0; i < images.length; i++) {
				Image image = new Image();
				Long idImg = (long) i;
				image.setId(idImg);
				image.setFile(images[i]);
				product.getImagens().add(image);
			}
		}
		
		product.setPricingGroup(pricingGroup);
		product.setName(name);
		product.setId((params.length == 0) ? null : params[0]);
		product.setStatus((null == this.status) ? null : this.status);
		if(null != subCategoryId) {
			for(String idsubCat: subCategoryId) {
				SubCategory subCategory = new SubCategory();
				subCategory.setId(Long.parseLong(idsubCat));
				product.getSubCategory().add(subCategory);
			}
		}
		
		if(null != technicalFieldId) {
			List<TechnicalRow> rows = new ArrayList<TechnicalRow>();
			for(int i = 0; i < technicalFieldId.length; i++) {
				TechnicalRow technicalRow = new TechnicalRow();
				TechnicalField field = new TechnicalField();
				field.setId(Long.parseLong(technicalFieldId[i]));
				technicalRow.setDescription(descriptionField[i]);
				technicalRow.setField(field);
				technicalRow.setId(Long.parseLong(technicalFieldId[i]));
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
	
	private void setTechnicalRows(Set<TechnicalRow> set) {
		technicalRow = new ArrayList<>();
		for(TechnicalRow row: set) {
			technicalRow.add((TechnicalRowDTO) new TechnicalRowDTO().from(row));
		}
	}
	
	private void setSubCategories(Set<SubCategory> set) {
		subCategories = new ArrayList<>();
		for(SubCategory subCategory: set) {
			subCategories.add((SubCategoryDTO) new SubCategoryDTO().from(subCategory));
		}
	}

}
