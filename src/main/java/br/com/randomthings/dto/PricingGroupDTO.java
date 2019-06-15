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
public class PricingGroupDTO extends AbstractDTO<PricingGroup> {
	
	@NotNull(message="O nome do grupo é obrigatório")
	@NotEmpty(message="O nome do grupo é obrigatório")
	private String name;
	
	@NotNull(message="O percentual não pode ser nulo")
	@Min(value=1, message="O percentual minimo é 1%")
	private Float profitPercentage;

	@Override
	public IDTO from(PricingGroup pricingGroup) {
		PricingGroupDTO pricingGroupDto = new PricingGroupDTO();
		this.from(pricingGroup, pricingGroupDto);
		
		pricingGroupDto.setName(((PricingGroup)pricingGroup).getName());
		pricingGroupDto.setProfitPercentage(((PricingGroup)pricingGroup).getProfitPercentage());
		return pricingGroupDto;
	}

	@Override
	public PricingGroup fill(Long... params) {
		PricingGroup pricingGroup = new PricingGroup();
		pricingGroup.setName(name);
		pricingGroup.setProfitPercentage(profitPercentage);
		pricingGroup.setId((params.length == 0) ? null : params[0]);
		pricingGroup.setStatus((null == this.status) ? null : this.status);
		return pricingGroup;
	}

}
