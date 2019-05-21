package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.randomthings.domain.OrderItem;

@Repository
public interface OrderItemRepository extends IRepository<OrderItem, Long>, JpaRepository<OrderItem, Long> {
	
}
