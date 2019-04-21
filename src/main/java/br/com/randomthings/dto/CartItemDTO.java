package br.com.randomthings.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.ShoppingCartItem;
import br.com.randomthings.viewhelper.ProductViewHelper;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class CartItemDTO extends EntityDTO {
	
	private ProductViewHelper product;
	
	private Integer quantity;
	
	private Float subTotal;
		
	public static CartItemDTO from(ShoppingCartItem cartItem) {
		CartItemDTO cartItemDTO = new CartItemDTO();
		Float subTotal = cartItem.getProduct().getPrice() * cartItem.getQuantity();
		cartItemDTO.setSubTotal(subTotal);
		cartItemDTO.setQuantity(cartItem.getQuantity());
		cartItemDTO.setId(cartItem.getId());
		cartItemDTO.setStatus(cartItem.getStatus());
		cartItemDTO.setCreationDate(cartItem.getCreationDate());
		cartItemDTO.setLastUpdate(cartItem.getLastUpdate());
		
		ProductViewHelper helper = (ProductViewHelper) new ProductViewHelper().getViewHelper(cartItem.getProduct());
		cartItemDTO.setProduct(helper);
		
		return cartItemDTO;
	}
	
}
