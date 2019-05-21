package br.com.randomthings.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.StatusActivation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class ActivationDTO extends AbstractDTO<Activation> {
	
	@NotNull(message="A descrição da ativação é obrigatória")
	@NotEmpty(message="A descrição da ativação é obrigatória")
	private String description;
	
	@NotNull(message = "O tipo de ativação é obrigatório")
	private StatusActivation statusActivation;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message="O id do produto é obrigatório")
	private Long productId;
	
	private ProductDTO product;

	@Override
	public IDTO from(Activation activation) {
		ActivationDTO activationDTO = new ActivationDTO();
		this.from(activation, activationDTO);
		
		activationDTO.setDescription(((Activation)activation).getDescription());
		activationDTO.setStatusActivation(((Activation)activation).getStatusActivation());
		activationDTO.setProduct((ProductDTO) new ProductDTO().from(activation.getProduct()));
		return activationDTO;
	}


	@Override
	public Activation fill(Long... params) {
		Activation activation = new Activation();
		Product product = new Product();
		product.setId(productId);
		
		activation.setDescription(description);
		activation.setProduct(product);
		activation.setStatusActivation(statusActivation);
		
		activation.setId((null == params[0]) ? null : params[0]);
		activation.setStatus((null == this.status) ? null : this.status);
		
		return activation;
	}

}
