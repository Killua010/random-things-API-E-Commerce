package br.com.randomthings.controller.utils;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.dto.EntityDto;
import br.com.randomthings.dto.Idto;
import br.com.randomthings.utils.Result;

@Component
public class StandardResponse <dto extends EntityDto> implements HttpResponseEntity {
	
	@Autowired
	private List<Idto> dtos;
	
	@Override
	public ResponseEntity<?> Status200 (Result result) {
		if(result.getResultEntities().size() == 0) {
			return ResponseEntity.ok().body(new ArrayList<>());
		} else {
			Idto idto = searchDto(result.getResultEntities().get(0));
			return ResponseEntity.ok().body(idto.getListDto(result.getResultEntities()));
		}
	}
	
	@Override
	public ResponseEntity<?> Status201 (Result result) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getResultEntities().get(0).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Override
	public ResponseEntity<?> Status204 (Result result) {
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<?> Status400 (Result result) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getResponse());
	}
	
	@Override
	public ResponseEntity<?> Status401(Result result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> Status403(Result result) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ResponseEntity<?> Status404 (Result result) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result.getResponse());
	}
	
	private Idto searchDto(DomainEntity entity){
		return this.dtos.stream().filter(dto -> 
			(dto.getClass().getName().toLowerCase().contains(entity.getClass().getSimpleName().toLowerCase())))
			.findAny().orElse(null);
	}
	
}
