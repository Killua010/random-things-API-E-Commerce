package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import br.com.randomthings.domain.TechnicalField;
import br.com.randomthings.domain.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class TechnicalFieldDTO extends AbstractDTO<TechnicalField> {
	
	@NotNull(message="O nome do campo é obrigatório")
	@NotEmpty(message="O nome do campo é obrigatório")
	private String name;

	@Override
	public IDTO from(TechnicalField technicalField) {
		TechnicalFieldDTO technicalFieldDto = new TechnicalFieldDTO();
		this.from(technicalField, technicalFieldDto);
		
		technicalFieldDto.setName(((TechnicalField)technicalField).getName());
		return technicalFieldDto;
	}

	@Override
	public TechnicalField fill(Long... params) {
		TechnicalField technicalField = new TechnicalField();
		technicalField.setName(name);
		technicalField.setId((null == params[0]) ? null : params[0]);
		technicalField.setStatus((null == this.status) ? null : this.status);
		return technicalField;
	}

}
