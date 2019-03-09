package br.com.randomthings.dto;

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
public class TechnicalFieldDto extends EntityDto {
	
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
	public List<EntityDto> getListDto(List<DomainEntity> entities) {
		List<EntityDto> dtos = new ArrayList<EntityDto>();
		for(DomainEntity entity : entities) {
			dtos.add(getDTO(entity));
		}
		return dtos;
	}

	@Override
	public EntityDto getDTO(DomainEntity technicalField) {
		TechnicalFieldDto technicalFieldDto = new TechnicalFieldDto();
		technicalFieldDto.setId(technicalField.getId());
		technicalFieldDto.setStatus(technicalField.getStatus());
		technicalFieldDto.setCreationDate(technicalField.getCreationDate());
		technicalFieldDto.setLastUpdate(technicalField.getLastUpdate());
		technicalFieldDto.setName(((TechnicalField)technicalField).getName());
		return technicalFieldDto;
	}

}
