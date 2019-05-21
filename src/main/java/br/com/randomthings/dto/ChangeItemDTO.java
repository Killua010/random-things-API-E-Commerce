package br.com.randomthings.dto;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.Change;
import br.com.randomthings.domain.ChangeItem;
import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.domain.ShoppingCartItem;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@Component
public class ChangeItemDTO extends AbstractDTO<ChangeItem> {
	
	private ProductDTO product;
	
	private Integer quantity;
	
	private Float subTotal;

	@Override
	public IDTO from(ChangeItem changeItem) {
		ChangeItemDTO changeItemDTO = new ChangeItemDTO();
		this.from(changeItem, changeItemDTO);
		
		Float subTotal = changeItem.getProduct().getPrice() * changeItem.getQuantity();
		changeItemDTO.setSubTotal(subTotal);
		changeItemDTO.setQuantity(changeItem.getQuantity());
		
		ProductDTO helper = (ProductDTO) new ProductDTO().from(changeItem.getProduct());
		changeItemDTO.setProduct(helper);
		
		return changeItemDTO;
	}

	@Override
	public ChangeItem fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}
	
}
