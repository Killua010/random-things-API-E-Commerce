package br.com.randomthings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.randomthings.domain.Change;
import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.StatusChange;

@Repository
public interface ChangeRepository extends IRepository<Change, Long>, JpaRepository<Change, Long> {
	public List<Change> findAllByStatusChange(StatusChange statusChange);

	public List<Change> findAllByClient(Client client);
	
	public List<Change> findAllByOrder(Order order);
}
