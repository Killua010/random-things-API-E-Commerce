package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.ShoppingCartItem;

public interface ShoppingCartItemRepository extends IRepository<ShoppingCartItem, Long>, JpaRepository<ShoppingCartItem, Long> {

}
