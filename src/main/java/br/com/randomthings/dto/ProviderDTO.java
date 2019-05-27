package br.com.randomthings.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.PromotionalCoupon;
import br.com.randomthings.domain.Provider;
import lombok.Data;

@Data
@Component
public class ProviderDTO extends AbstractDTO<Provider> {
	
	private CategoryDTO category;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message="O id da categoria de é obrigatório")
	@Min(value=1, message="O id informado é invalido")
	private Long categoryId;
	
	@NotNull(message="O cnpj é obrigatório")
	@NotEmpty(message="O cnpj é obrigatório")
	@CNPJ
	private String cnpj;
	
	@NotNull(message="O nome é obrigatório")
	@NotEmpty(message="O nome é obrigatório")
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
		Provider provider = new Provider();
		Category category = new Category();
		
		category.setId(categoryId);
		provider.setCategory(category);
		provider.setCnpj(cnpj);
		provider.setName(name);
		provider.setId((params.length == 0) ? null : params[0]);
		provider.setStatus((null == this.status) ? null : this.status);
		
		return provider;
	}
	
}
