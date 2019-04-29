package br.com.randomthings.services.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public OrderItem findById(Long id) {
		return orderItemRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + OrderItem.class.getSimpleName()));
	}

	@Override
	public OrderItem save(OrderItem domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
