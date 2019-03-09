package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.PricingGroup;
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
public class PricingGroupDto extends EntityDto {
	
	@NotNull(message="O nome do grupo é obrigatório")
	@NotEmpty(message="O nome do grupo é obrigatório")
	private String name;
	
	@NotNull(message="O percentual não pode ser nulo")
	@Min(value=1, message="O percentual minimo é 1%")
	private Float profitPercentage;

	@Override
	public DomainEntity getEntity() {
		PricingGroup pricingGroup = new PricingGroup();
		pricingGroup.setName(name);
		pricingGroup.setProfitPercentage(profitPercentage);
		return pricingGroup;
	}
	
	@Override
	public DomainEntity getEntity(Long id) {
		Boolean status = (null == this.status) ? null : this.status;
		PricingGroup pricingGroup = new PricingGroup();
		pricingGroup.setName(name);
		pricingGroup.setProfitPercentage(profitPercentage);
		pricingGroup.setId(id);
		pricingGroup.setStatus(status);
		return pricingGroup;
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
	public EntityDto getDTO(DomainEntity pricingGroup) {
		PricingGroupDto pricingGroupDto = new PricingGroupDto();
		pricingGroupDto.setId(pricingGroup.getId());
		pricingGroupDto.setStatus(pricingGroup.getStatus());
		pricingGroupDto.setCreationDate(pricingGroup.getCreationDate());
		pricingGroupDto.setLastUpdate(pricingGroup.getLastUpdate());
		pricingGroupDto.setName(((PricingGroup)pricingGroup).getName());
		pricingGroupDto.setProfitPercentage(((PricingGroup)pricingGroup).getProfitPercentage());
		return pricingGroupDto;
	}

}
