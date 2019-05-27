package br.com.randomthings.dto.specific;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportDTO {
	private String name;
	private Object value;
}
