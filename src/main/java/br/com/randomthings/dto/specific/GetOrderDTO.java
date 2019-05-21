package br.com.randomthings.dto.specific;

import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.ShoppingCart;
import br.com.randomthings.dto.ShoppingCartDTO;
import lombok.Data;

@Data
public class GetOrderDTO {
	private Long clientId;
	private Long addressId;
	private Long cardId;
	private Float orderValue;
	private Float shippingValue;
	private String changeCoupon;
	private String promotionalCoupon;
	private ShoppingCartDTO cart;
	
}
