package br.com.randomthings.viewhelper;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class CategoryViewHelper extends EntityViewHelper {
	
	@NotNull(message="O nome da categoria é obrigatório")
	@NotEmpty(message="O nome da categoria é obrigatório")
	private String name;

	@Override
	public DomainEntity getEntity() {
		Category category = new Category();
		category.setName(name);
		return category;
	}
	
	@Override
	public DomainEntity getEntity(Long id) {
		Boolean status = (null == this.status) ? null : this.status;
		Category category = new Category();
		category.setName(name);
		category.setId(id);
		category.setStatus(status);
		return category;
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
	public EntityViewHelper getViewHelper(DomainEntity category) {
		CategoryViewHelper categoryDto = new CategoryViewHelper();
		categoryDto.setId(category.getId());
		categoryDto.setStatus(category.getStatus());
		categoryDto.setCreationDate(category.getCreationDate());
		categoryDto.setLastUpdate(category.getLastUpdate());
		categoryDto.setName(((Category)category).getName());
		return categoryDto;
	}

}
