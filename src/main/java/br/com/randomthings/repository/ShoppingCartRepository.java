package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.ShoppingCart;

public interface ShoppingCartRepository extends IRepository<ShoppingCart, Long>, JpaRepository<ShoppingCart, Long> {

}
