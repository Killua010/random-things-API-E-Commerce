package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.User;

public interface UserRepository extends IRepository<User, Long>, JpaRepository<User, Long> {

}
