package br.com.randomthings.dto;

import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.ShoppingCart;
import lombok.Data;

@Data
public class GetOrderDTO {
	private Long clientId;
	private Long addressId;
	private Long cardId;
	private Float orderValue;
	private Float shippingValue;
	private String coupon;
	private ShoppingCartDTO cart;
	
	public static GetOrderDTO from(Order order) {
		GetOrderDTO orderDTO = new GetOrderDTO();
		
		
		return orderDTO;
	}
	
}
