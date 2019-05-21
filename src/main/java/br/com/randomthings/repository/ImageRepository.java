package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.randomthings.domain.Image;

@Repository
public interface ImageRepository extends IRepository<Image, Long>, JpaRepository<Image, Long> {

}
