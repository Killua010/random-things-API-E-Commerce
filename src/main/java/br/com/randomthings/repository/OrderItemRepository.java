package br.com.randomthings.repository;

import org.springframework.stereotype.Repository;

import br.com.randomthings.domain.OrderItem;

@Repository
public interface OrderItemRepository extends RepositoryImpl<OrderItem>, IRepository<OrderItem> {
	
}
