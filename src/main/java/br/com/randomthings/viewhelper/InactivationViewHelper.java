package br.com.randomthings.viewhelper;

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
public class InactivationViewHelper extends EntityViewHelper {
	
	@NotNull(message="A descrição da inativação é obrigatória")
	@NotEmpty(message="A descrição da inativação é obrigatória")
	private String description;
	
	@NotNull(message = "O tipo de inativação é obrigatório")
	private StatusInactivation statusInactivation;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message="O id do produto é obrigatório")
	private Long productId;
	
	private ProductViewHelper product;

	@Override
	public DomainEntity getEntity() {
		Inactivation inactivation = new Inactivation();
		inactivation.setDescription(description);
		Product product = new Product();
		product.setId(productId);
		inactivation.setProduct(product);
		inactivation.setStatusInactivation(statusInactivation);
		return inactivation;
	}
	
	@Override
	public DomainEntity getEntity(Long id) {
		Boolean status = (null == this.status) ? null : this.status;
		Inactivation inactivation = new Inactivation();
		inactivation.setDescription(description);
		Product product = new Product();
		product.setId(productId);
		inactivation.setProduct(product);
		inactivation.setStatusInactivation(statusInactivation);
		inactivation.setId(id);
		inactivation.setStatus(status);
		return inactivation;
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
	public EntityViewHelper getViewHelper(DomainEntity inactivation) {
		InactivationViewHelper inactivationViewHelper = new InactivationViewHelper();
		inactivationViewHelper.setId(inactivation.getId());
		inactivationViewHelper.setStatus(inactivation.getStatus());
		inactivationViewHelper.setCreationDate(inactivation.getCreationDate());
		inactivationViewHelper.setLastUpdate(inactivation.getLastUpdate());
		inactivationViewHelper.setDescription(((Inactivation)inactivation).getDescription());
		inactivationViewHelper.setStatusInactivation(((Inactivation)inactivation).getStatusInactivation());
		inactivationViewHelper.setProduct((ProductViewHelper) new ProductViewHelper().getViewHelper(((Inactivation)inactivation).getProduct()));
		return inactivationViewHelper;
	}

}
