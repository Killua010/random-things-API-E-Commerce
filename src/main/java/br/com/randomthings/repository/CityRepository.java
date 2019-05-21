package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.City;

public interface CityRepository extends IRepository<City, Long>, JpaRepository<City, Long> {

}
