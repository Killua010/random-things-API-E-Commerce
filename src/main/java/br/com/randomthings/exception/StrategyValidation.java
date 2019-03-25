package br.com.randomthings.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StrategyValidation extends RuntimeException {
	
	StringBuilder errors;

}
