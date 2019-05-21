package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.DeliveryAddress;

public interface DeliveryAddressRepository extends IRepository<DeliveryAddress, Long>, JpaRepository<DeliveryAddress, Long> {

}
