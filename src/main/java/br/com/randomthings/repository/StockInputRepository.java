package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.Stock;
import br.com.randomthings.domain.StockInput;

public interface StockInputRepository extends IRepository<StockInput, Long>, JpaRepository<StockInput, Long> {

}
