package br.com.randomthings.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError {
	private Integer status;
	private String msg;
	private Long timeStamp;
	
}
