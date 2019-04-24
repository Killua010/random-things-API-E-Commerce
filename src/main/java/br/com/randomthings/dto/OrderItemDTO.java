package br.com.randomthings.dto;

import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.domain.ShoppingCartItem;
import br.com.randomthings.viewhelper.ProductViewHelper;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class OrderItemDTO extends EntityDTO {
	
	private ProductViewHelper product;
	
	private Integer quantity;
	
	private Float subTotal;
		
	public static OrderItemDTO from(OrderItem orderItem) {
		OrderItemDTO orderDTO = new OrderItemDTO();
		Float subTotal = orderItem.getProduct().getPrice() * orderItem.getQuantity();
		orderDTO.setSubTotal(subTotal);
		orderDTO.setQuantity(orderItem.getQuantity());
		orderDTO.setId(orderItem.getId());
		orderDTO.setStatus(orderItem.getStatus());
		orderDTO.setCreationDate(orderItem.getCreationDate());
		orderDTO.setLastUpdate(orderItem.getLastUpdate());
		
		ProductViewHelper helper = (ProductViewHelper) new ProductViewHelper().getViewHelper(orderItem.getProduct());
		orderDTO.setProduct(helper);
		
		return orderDTO;
	}
	
}
