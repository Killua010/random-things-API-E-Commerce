package br.com.randomthings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.User;

public interface UserRepository extends IRepository<User, Long>, JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
}
