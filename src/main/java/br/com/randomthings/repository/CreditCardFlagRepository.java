package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.CreditCardFlag;

public interface CreditCardFlagRepository extends IRepository<CreditCardFlag, Long>, JpaRepository<CreditCardFlag, Long> {

}
