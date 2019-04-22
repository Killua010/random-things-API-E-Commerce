package br.com.randomthings.dto;

import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.ShoppingCart;
import lombok.Data;

@Data
public class OrderDTO {
	private Long clientId;
	private Long addressId;
	private Long cardId;
	private Float orderValue;
	private Float shippingValue;
	private ShoppingCartDTO cart;
	
	public static OrderDTO from(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		
		
		return orderDTO;
	}
	
}
