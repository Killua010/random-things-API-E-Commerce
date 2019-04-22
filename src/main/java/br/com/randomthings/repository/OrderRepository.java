package br.com.randomthings.repository;

import org.springframework.stereotype.Repository;

import br.com.randomthings.domain.Order;

@Repository
public interface OrderRepository extends RepositoryImpl<Order>, IRepository<Order> {

}
