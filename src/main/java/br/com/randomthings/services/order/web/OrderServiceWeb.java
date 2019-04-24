package br.com.randomthings.services.order.web;

import java.util.List;

import br.com.randomthings.domain.Order;
import br.com.randomthings.dto.GetOrderDTO;
import br.com.randomthings.dto.OrderDTO;

public interface OrderServiceWeb {
	public Order save(GetOrderDTO orderDTO);
	public OrderDTO nextStep(Long id);
	public List<OrderDTO> getByStatus(String status);
}
