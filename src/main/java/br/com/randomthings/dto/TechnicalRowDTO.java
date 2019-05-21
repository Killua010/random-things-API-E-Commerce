package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.TechnicalField;
import br.com.randomthings.domain.TechnicalRow;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
public class TechnicalRowDTO extends AbstractDTO<TechnicalRow> {
	
	private String description;
	
	private TechnicalFieldDTO technicalField;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message="O id do campo tecnico é obrigatório")
	@Min(value=1, message="O id informado é invalido")
	private Long technicalFieldId;

	@Override
	public IDTO from(TechnicalRow technicalRow) {
		TechnicalRowDTO technicalRowDto = new TechnicalRowDTO();
		this.from(technicalRow, technicalRowDto);
		
		technicalRowDto.setDescription(((TechnicalRow)technicalRow).getDescription());
		technicalRowDto.setTechnicalField((TechnicalFieldDTO) new TechnicalFieldDTO().from(technicalRow.getField()));
		return technicalRowDto;
	}

	@Override
	public TechnicalRow fill(Long... params) {
		TechnicalRow technicalRow = new TechnicalRow();
		TechnicalField field = new TechnicalField();
		field.setId(technicalFieldId);
		technicalRow.setDescription(description);
		technicalRow.setId((null == params[0]) ? null : params[0]);
		technicalRow.setStatus((null == this.status) ? null : this.status);
		technicalRow.setField(field);
		return technicalRow;
	}
}
