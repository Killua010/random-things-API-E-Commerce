package br.com.randomthings.controller.utils;

import org.springframework.http.ResponseEntity;

import br.com.randomthings.utils.Result;

public interface HttpResponseEntity {
	ResponseEntity<?> Status200 (Result result);
	ResponseEntity<?> Status201 (Result result);
	ResponseEntity<?> Status204 (Result result);
	ResponseEntity<?> Status400 (Result result);
	ResponseEntity<?> Status401 (Result result);
	ResponseEntity<?> Status403 (Result result);
	ResponseEntity<?> Status404 (Result result);
}
