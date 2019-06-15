package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Image;
import br.com.randomthings.domain.SubCategory;
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
public class CategoryDTO extends AbstractDTO<Category> {
	
	@NotNull(message="O nome da categoria é obrigatório")
	@NotEmpty(message="O nome da categoria é obrigatório")
	private String name;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private MultipartFile image;
	
	private String imgSrc;
	
	private List<SubCategoryDTO> subCategories;

	@Override
	public IDTO from(Category category) {
		CategoryDTO categoryDto = new CategoryDTO();
		this.from(category, categoryDto);
		
		categoryDto.setName(category.getName());
		categoryDto.setSubCategories(category.getSubCategories());
		
		if(category.getImage() != null) {
			categoryDto.setImgSrc(SystemVariable.systemPath + "/images/" + category.getImage().getId());
		} else {
			categoryDto.setImgSrc(null);
		}
		
		return categoryDto;
	}

	@Override
	public Category fill(Long... params) {
		Category category = new Category();
		Image image = new Image();
		image.setFile(this.image);
		category.setImage(image);
		category.setName(name);
		category.setId((params.length == 0) ? null : params[0]);
		category.setStatus((null == this.status) ? null : this.status);
		return category;
	}
	
	private void setSubCategories(List<SubCategory> set) {
		subCategories = new ArrayList<SubCategoryDTO>();
		for(SubCategory subCategory: set) {
			SubCategoryDTO helper = new SubCategoryDTO();
			helper.setId(subCategory.getId());
			helper.setName(subCategory.getName());
			subCategories.add(helper);
			
		}
	}

}
