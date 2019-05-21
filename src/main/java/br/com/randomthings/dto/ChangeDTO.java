package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.Change;
import br.com.randomthings.domain.ChangeItem;
import lombok.Data;

@Data
@Component
public class ChangeDTO extends AbstractDTO<Change> {
	
	@NotNull(message="O id do pedido é obrigatório")
	private Long orderId;
	
	@NotNull(message="O id do cliente é obrigatório")
	private Long clientId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long[] idItem;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer[] quantityItem;
	
	private String statusOrder;

	@JsonProperty(access = Access.READ_ONLY)
	private List<ChangeItemDTO> itens;
	
	@Override
	public IDTO from(Change change) {
		ChangeDTO changeDTO = new ChangeDTO();
		this.from(change, changeDTO);
		
		changeDTO.setClientId(change.getClient().getId());
		changeDTO.setOrderId(change.getOrder().getId());
		changeDTO.setStatusOrder(change.getStatusChange().getDescription());
		changeDTO.setItens(change.getItems());
		
		return changeDTO;
	}

	@Override
	public Change fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}
	
	private void setItens(Set<ChangeItem> itens) {
		this.itens = new ArrayList<>();
		for(ChangeItem item : itens) {
			this.itens.add((ChangeItemDTO) new ChangeItemDTO().from(item));
		}
	}

}
