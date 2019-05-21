package br.com.randomthings.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.randomthings.domain.ChangeCoupon;
import br.com.randomthings.domain.Client;

@Repository
public interface ChangeCouponRepository extends IRepository<ChangeCoupon, Long>, JpaRepository<ChangeCoupon, Long> {

	List<ChangeCoupon> findAllByClient(Client client);

	Optional<ChangeCoupon> findByNameAndClient(String name, Client client);

}
