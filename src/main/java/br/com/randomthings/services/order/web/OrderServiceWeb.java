package br.com.randomthings.services.order.web;

import br.com.randomthings.domain.Order;
import br.com.randomthings.dto.OrderDTO;

public interface OrderServiceWeb {
	public Order save(OrderDTO orderDTO);
}
