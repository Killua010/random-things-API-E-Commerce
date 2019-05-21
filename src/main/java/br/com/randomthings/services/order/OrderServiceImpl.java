package br.com.randomthings.services.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.StatusOrder;
import br.com.randomthings.repository.OrderRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class OrderServiceImpl extends AbstractService<Order, Long> implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	public OrderServiceImpl(OrderRepository dao) {
		super(dao);
	}

	@Override
	public List<Order> getByClient(Client client) {
		return orderRepository.findAllByClient(client);
	}

	@Override
	public List<Order> getByStatus(StatusOrder order) {
		return orderRepository.findAllByStatusOrder(order);
	}

}
