package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.CreditCard;

public interface CreditCardRepository extends  IRepository<CreditCard, Long>, JpaRepository<CreditCard, Long> {

}
