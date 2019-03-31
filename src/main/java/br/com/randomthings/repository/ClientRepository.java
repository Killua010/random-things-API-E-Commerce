package br.com.randomthings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.User;

public interface ClientRepository extends RepositoryImpl<Client>, IRepository<Client> {
	
	@Query("SELECT client FROM _client client WHERE client.user.email = ?1 AND client.user.password = ?2")
	Optional<Client> findByEmailAndPassword(String email, String password);
}
