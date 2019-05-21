package br.com.randomthings.dto;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.PromotionalCoupon;
import br.com.randomthings.domain.Provider;
import lombok.Data;

@Data
@Component
public class ProviderDTO extends AbstractDTO<Provider> {
	private CategoryDTO category;
	
	private Long categoryId;
	
	private String cnpj;
	
	private String name;


	@Override
	public IDTO from(Provider provider) {
		ProviderDTO providerDTO = new ProviderDTO();
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO = (CategoryDTO) categoryDTO.from(provider.getCategory());
		
		providerDTO.setCreationDate(provider.getCreationDate());
		providerDTO.setId(provider.getId());
		providerDTO.setLastUpdate(provider.getLastUpdate());
		providerDTO.setStatus(provider.getStatus());
		providerDTO.setCategory(categoryDTO);
		providerDTO.setCnpj(provider.getCnpj());
		providerDTO.setName(provider.getName());
		
		return providerDTO;
	}

	@Override
	public Provider fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}
	
}
