package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class SubCategoryDTO extends AbstractDTO<SubCategory> {
	
	@NotNull(message="O nome da subcategoria é obrigatório")
	@NotEmpty(message="O nome da subcategoria é obrigatório")
	private String name;
	
	private CategoryDTO category;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message="O id da categoria é obrigatório")
	@Min(value=1, message="O id informado é invalido")
	private Long categoryId;

	@Override
	public IDTO from(SubCategory subCategory) {
		SubCategoryDTO subCategoryDto = new SubCategoryDTO();
		this.from(subCategory, subCategoryDto);
		
		subCategoryDto.setName(((SubCategory)subCategory).getName());
		subCategoryDto.setCategory((CategoryDTO) new CategoryDTO().from(subCategory.getCategory()));
		return subCategoryDto;
	}

	@Override
	public SubCategory fill(Long... params) {
		SubCategory subCategory = new SubCategory();
		Category category = new Category();
		category.setId(categoryId);
		subCategory.setName(name);
		subCategory.setId((null == params[0]) ? null : params[0]);
		subCategory.setStatus((null == this.status) ? null : this.status);
		subCategory.setCategory(category);
		return subCategory;
	}

}
