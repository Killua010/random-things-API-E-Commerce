package br.com.randomthings.services.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.StatusOrder;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order findById(Long id) {
		return orderRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Order.class.getSimpleName()));
	}

	@Override
	public Order save(Order domain) {
		return orderRepository.save(domain);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getByStatus(StatusOrder order) {
		return orderRepository.findAllByStatusOrder(order);
	}

}
