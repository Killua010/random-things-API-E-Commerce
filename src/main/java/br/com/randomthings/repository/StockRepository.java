package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.Stock;

public interface StockRepository extends IRepository<Stock, Long>, JpaRepository<Stock, Long> {

}
