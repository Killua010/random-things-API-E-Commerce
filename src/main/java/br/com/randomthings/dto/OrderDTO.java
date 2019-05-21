package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.OrderItem;
import lombok.Data;

@Data
@Component
public class OrderDTO extends AbstractDTO<Order> {
	
	private Float shippingPrice;
	
	private Float TotalPrice;
	
	private String statusOrder;

	@JsonProperty(access = Access.READ_ONLY)
	private List<OrderItemDTO> itens;
	
	private AddressDTO address;
	
	private void setItens(Set<OrderItem> orderItems) {
		this.itens = new ArrayList<OrderItemDTO>();
		for(OrderItem item: orderItems) {
			this.itens.add((OrderItemDTO) new OrderItemDTO().from(item));
		}
	}

	@Override
	public IDTO from(Order order) {
		OrderDTO dto = new OrderDTO();
		this.from(order, dto);
		
		dto.setItens(order.getItems());
		dto.setTotalPrice(order.getOrderValue() + order.getShippingPrice().getValue());
		dto.setShippingPrice(order.getShippingPrice().getValue());
		dto.setAddress((AddressDTO) new AddressDTO().from(order.getShippingPrice().getAddress()));
		dto.setStatusOrder(order.getStatusOrder().getDescription());
		
		return dto;
	}

	@Override
	public Order fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}
	
}
