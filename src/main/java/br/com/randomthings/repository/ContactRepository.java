package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.Contact;

public interface ContactRepository extends IRepository<Contact, Long>, JpaRepository<Contact, Long> {

}
