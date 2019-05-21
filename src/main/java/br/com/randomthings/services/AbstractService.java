package br.com.randomthings.services;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.IRepository;

public class AbstractService <domain extends DomainEntity, key> implements IService<domain, key>{

	private final IRepository<domain, key> daoJPA;
	private final JpaRepository<domain, key> jpa;
	
	public AbstractService(Object dao) {
		if (!(dao instanceof IRepository) || !(dao instanceof JpaRepository)) {
			throw new IllegalArgumentException("O DAO especificado não é uma instância das interfaces necessárias.");
		}
		this.daoJPA = (IRepository<domain, key>) dao;
		this.jpa = (JpaRepository<domain, key>) dao;
	}
	
	@Override
	public domain findByIdAndStatusTrue(key id) {
		return this.daoJPA.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + getInstace().getClass().getSimpleName()));
	}

	@Override
	public domain findById(key id) {
		return this.jpa.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + getInstace().getClass().getSimpleName()));
	}

	public domain save(domain domain) {
		return this.jpa.saveAndFlush(domain);
	}

	@Override
	public domain update(domain domain) {
		return this.jpa.saveAndFlush(domain);
	}

	@Override
	public void deleteById(key id) {
		this.jpa.delete(this.findById(id));
	}

	@Override
	public List<domain> findAllByStatusTrue() {
		return this.daoJPA.findByStatusTrue();
	}

	@Override
	public List<domain> findAll() {
		return this.jpa.findAll();
	}

	private domain getInstace() {
		domain domain = null;
		try {
			Class classDefinition = Class.forName(((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName());
			domain  = (domain) classDefinition.newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}
		return domain;
	}

}
