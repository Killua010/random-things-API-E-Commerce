package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.OrderItem;
import lombok.Data;

@Data
public class OrderDTO extends EntityDTO {
	
	private Float shippingPrice;
	
	private Float TotalPrice;
	
	private String statusOrder;

	@JsonProperty(access = Access.READ_ONLY)
	private List<OrderItemDTO> itens;
	
	private AddressDTO address;
	
	private void setItens(Set<OrderItem> orderItems) {
		this.itens = new ArrayList<OrderItemDTO>();
		for(OrderItem item: orderItems) {
			this.itens.add(OrderItemDTO.from(item));
		}
	}
	
	public static OrderDTO from(Order order) {
		OrderDTO dto = new OrderDTO();
		
		dto.setId(order.getId());
		dto.setStatus(order.getStatus());
		dto.setCreationDate(order.getCreationDate());
		dto.setLastUpdate(order.getLastUpdate());
		dto.setItens(order.getItems());
		dto.setTotalPrice(order.getOrderValue() + order.getShippingPrice().getValue());
		dto.setShippingPrice(order.getShippingPrice().getValue());
		dto.setAddress(AddressDTO.from(order.getShippingPrice().getAddress()));
		dto.setStatusOrder(order.getStatusOrder().getDescription());
		
		return dto;
	}
	
}
