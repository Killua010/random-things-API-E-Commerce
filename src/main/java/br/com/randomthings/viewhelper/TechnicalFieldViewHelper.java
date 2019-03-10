package br.com.randomthings.viewhelper;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.TechnicalField;
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
public class TechnicalFieldViewHelper extends EntityViewHelper {
	
	@NotNull(message="O nome do campo é obrigatório")
	@NotEmpty(message="O nome do campo é obrigatório")
	private String name;

	@Override
	public DomainEntity getEntity() {
		TechnicalField technicalField = new TechnicalField();
		technicalField.setName(name);
		return technicalField;
	}
	
	@Override
	public DomainEntity getEntity(Long id) {
		Boolean status = (null == this.status) ? null : this.status;
		TechnicalField technicalField = new TechnicalField();
		technicalField.setName(name);
		technicalField.setId(id);
		technicalField.setStatus(status);
		return technicalField;
	}

	@Override
	public List<EntityViewHelper> setListEntity(List<DomainEntity> entities) {
		List<EntityViewHelper> dtos = new ArrayList<EntityViewHelper>();
		for(DomainEntity entity : entities) {
			dtos.add(setEntity(entity));
		}
		return dtos;
	}

	@Override
	public EntityViewHelper setEntity(DomainEntity technicalField) {
		TechnicalFieldViewHelper technicalFieldDto = new TechnicalFieldViewHelper();
		technicalFieldDto.setId(technicalField.getId());
		technicalFieldDto.setStatus(technicalField.getStatus());
		technicalFieldDto.setCreationDate(technicalField.getCreationDate());
		technicalFieldDto.setLastUpdate(technicalField.getLastUpdate());
		technicalFieldDto.setName(((TechnicalField)technicalField).getName());
		return technicalFieldDto;
	}

}
