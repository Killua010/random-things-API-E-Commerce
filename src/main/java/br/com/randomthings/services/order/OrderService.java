package br.com.randomthings.services.order;

import java.util.List;

import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.StatusOrder;
import br.com.randomthings.services.IService;

public interface OrderService extends IService<Order> {
	public List<Order> getByStatus(StatusOrder order);
}
