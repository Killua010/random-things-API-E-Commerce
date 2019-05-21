package br.com.randomthings.services.order;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.repository.OrderItemRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class OrderItemServiceImpl extends AbstractService<OrderItem, Long> implements OrderItemService{

	public OrderItemServiceImpl(OrderItemRepository dao) {
		super(dao);
	}
}
