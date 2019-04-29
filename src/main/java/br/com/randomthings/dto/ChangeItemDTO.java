package br.com.randomthings.dto;

import br.com.randomthings.domain.ChangeItem;
import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.domain.ShoppingCartItem;
import br.com.randomthings.viewhelper.ProductViewHelper;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class ChangeItemDTO extends EntityDTO {
	
	private ProductViewHelper product;
	
	private Integer quantity;
	
	private Float subTotal;
		
	public static ChangeItemDTO from(ChangeItem changeItem) {
		ChangeItemDTO orderDTO = new ChangeItemDTO();
		Float subTotal = changeItem.getProduct().getPrice() * changeItem.getQuantity();
		orderDTO.setSubTotal(subTotal);
		orderDTO.setQuantity(changeItem.getQuantity());
		orderDTO.setId(changeItem.getId());
		orderDTO.setStatus(changeItem.getStatus());
		orderDTO.setCreationDate(changeItem.getCreationDate());
		orderDTO.setLastUpdate(changeItem.getLastUpdate());
		
		ProductViewHelper helper = (ProductViewHelper) new ProductViewHelper().getViewHelper(changeItem.getProduct());
		orderDTO.setProduct(helper);
		
		return orderDTO;
	}
	
}
