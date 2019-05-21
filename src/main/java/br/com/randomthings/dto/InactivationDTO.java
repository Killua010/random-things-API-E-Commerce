package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.Inactivation;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.StatusInactivation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class InactivationDTO extends AbstractDTO<Inactivation> {
	
	@NotNull(message="A descrição da inativação é obrigatória")
	@NotEmpty(message="A descrição da inativação é obrigatória")
	private String description;
	
	@NotNull(message = "O tipo de inativação é obrigatório")
	private StatusInactivation statusInactivation;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message="O id do produto é obrigatório")
	private Long productId;
	
	private ProductDTO product;

	@Override
	public IDTO from(Inactivation inactivation) {
		InactivationDTO inactivationDTO = new InactivationDTO();
		this.from(inactivation, inactivationDTO);
		
		inactivationDTO.setDescription(((Inactivation)inactivation).getDescription());
		inactivationDTO.setStatusInactivation(((Inactivation)inactivation).getStatusInactivation());
		inactivationDTO.setProduct((ProductDTO) new ProductDTO().from(inactivation.getProduct()));
		return inactivationDTO;
	}

	@Override
	public Inactivation fill(Long... params) {
		Inactivation inactivation = new Inactivation();
		Product product = new Product();
		
		inactivation.setDescription(description);
		product.setId(productId);
		inactivation.setProduct(product);
		inactivation.setStatusInactivation(statusInactivation);
		inactivation.setId((null == params[0]) ? null : params[0]);
		inactivation.setStatus((null == this.status) ? null : this.status);
		
		return inactivation;
	}

}
