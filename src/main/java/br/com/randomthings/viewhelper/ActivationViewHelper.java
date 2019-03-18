package br.com.randomthings.viewhelper;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.DomainEntity;
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
public class ActivationViewHelper extends EntityViewHelper {
	
	@NotNull(message="A descrição da ativação é obrigatória")
	@NotEmpty(message="A descrição da ativação é obrigatória")
	private String description;
	
	@NotNull(message = "O tipo de ativação é obrigatório")
	private StatusActivation statusActivation;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message="O id do produto é obrigatório")
	private Long productId;
	
	private ProductViewHelper product;

	@Override
	public DomainEntity getEntity() {
		Activation activation = new Activation();
		activation.setDescription(description);
		Product product = new Product();
		product.setId(productId);
		activation.setProduct(product);
		activation.setStatusActivation(statusActivation);
		return activation;
	}
	
	@Override
	public DomainEntity getEntity(Long id) {
		Boolean status = (null == this.status) ? null : this.status;
		Activation activation = new Activation();
		activation.setDescription(description);
		Product product = new Product();
		product.setId(productId);
		activation.setProduct(product);
		activation.setStatusActivation(statusActivation);
		activation.setId(id);
		activation.setStatus(status);
		return activation;
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
	public EntityViewHelper getViewHelper(DomainEntity activation) {
		ActivationViewHelper activationViewHelper = new ActivationViewHelper();
		activationViewHelper.setId(activation.getId());
		activationViewHelper.setStatus(activation.getStatus());
		activationViewHelper.setCreationDate(activation.getCreationDate());
		activationViewHelper.setLastUpdate(activation.getLastUpdate());
		activationViewHelper.setDescription(((Activation)activation).getDescription());
		activationViewHelper.setStatusActivation(((Activation)activation).getStatusActivation());
		activationViewHelper.setProduct((ProductViewHelper) new ProductViewHelper().getViewHelper(((Activation)activation).getProduct()));
		return activationViewHelper;
	}

}
