package br.com.randomthings.repository;

import org.springframework.stereotype.Repository;

import br.com.randomthings.domain.Image;

@Repository
public interface ImageRepository extends RepositoryImpl<Image>, IRepository<Image> {

}
