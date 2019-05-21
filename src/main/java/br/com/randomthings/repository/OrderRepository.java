package br.com.randomthings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.StatusOrder;

@Repository
public interface OrderRepository extends IRepository<Order, Long>, JpaRepository<Order, Long> {
	
	public List<Order> findAllByStatusOrder(StatusOrder statusOrder);
	
	public List<Order> findAllByClient(Client client); 

}
