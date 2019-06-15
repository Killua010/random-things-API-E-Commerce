package br.com.randomthings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.User;

public interface ClientRepository extends IRepository<Client, Long>, JpaRepository<Client, Long> {
	
	@Query("SELECT client FROM _client client WHERE client.user.email = ?1 AND client.user.password = ?2")
	Optional<Client> findByEmailAndPassword(String email, String password);
	
	Optional<Client> findByCpf(String cpf);
	
	@Query("SELECT client FROM _client client WHERE client.user.email = ?1")
	Optional<Client> findByEmail(String email);
	
	@Query("SELECT card FROM _client client JOIN client.cards card WHERE card.number = ?1")
	Optional<CreditCard> findByCreditCardNumber(String number);
	
	@Query("SELECT address FROM _client client JOIN client.addresses address WHERE address.zipCode = ?1 AND address.number = ?2")
	Optional<DeliveryAddress> findByZipCodeAndNumberAddress(String zipCode, Integer number);
}
