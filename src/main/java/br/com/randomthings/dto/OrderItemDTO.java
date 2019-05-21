package br.com.randomthings.dto;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.domain.ShoppingCartItem;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@Component
public class OrderItemDTO extends AbstractDTO<OrderItem> {
	
	private ProductDTO product;
	
	private Integer quantity;
	
	private Float subTotal;

	@Override
	public IDTO from(OrderItem orderItem) {
		OrderItemDTO orderDTO = new OrderItemDTO();
		this.from(orderItem, orderDTO);
		
		Float subTotal = orderItem.getProduct().getPrice() * orderItem.getQuantity();
		orderDTO.setSubTotal(subTotal);
		orderDTO.setQuantity(orderItem.getQuantity());
		
		ProductDTO helper = (ProductDTO) new ProductDTO().from(orderItem.getProduct());
		orderDTO.setProduct(helper);
		
		return orderDTO;
	}

	@Override
	public OrderItem fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}
	
}
