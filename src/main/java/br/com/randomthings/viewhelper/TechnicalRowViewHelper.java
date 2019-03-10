package br.com.randomthings.viewhelper;

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
public class TechnicalRowViewHelper extends EntityViewHelper {
	
	private String description;
	
	private TechnicalFieldViewHelper technicalField;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotNull(message="O id do campo tecnico é obrigatório")
	@Min(value=1, message="O id informado é invalido")
	private Long technicalFieldId;

	@Override
	public DomainEntity getEntity() {
		TechnicalRow technicalRow = new TechnicalRow();
		TechnicalField field = new TechnicalField();
		field.setId(technicalFieldId);
		technicalRow.setDescription(description);
		technicalRow.setField(field);
		return technicalRow;
	}
	
	@Override
	public DomainEntity getEntity(Long id) {
		Boolean status = (null == this.status) ? null : this.status;
		TechnicalRow technicalRow = new TechnicalRow();
		TechnicalField field = new TechnicalField();
		field.setId(technicalFieldId);
		technicalRow.setDescription(description);
		technicalRow.setId(id);
		technicalRow.setStatus(status);
		technicalRow.setField(field);
		return technicalRow;
	}

	@Override
	public List<EntityViewHelper> setListEntity(List<DomainEntity> entities) {
		List<EntityViewHelper> dtos = new ArrayList<EntityViewHelper>();
		for(DomainEntity entity : entities) {
			dtos.add(new TechnicalRowViewHelper().setEntity(entity));
		}
		return dtos;
	}

	@Override
	public EntityViewHelper setEntity(DomainEntity technicalRow) {
		TechnicalRowViewHelper technicalRowDto = new TechnicalRowViewHelper();
		technicalRowDto.setId(technicalRow.getId());
		technicalRowDto.setStatus(technicalRow.getStatus());
		technicalRowDto.setCreationDate(technicalRow.getCreationDate());
		technicalRowDto.setLastUpdate(technicalRow.getLastUpdate());
		technicalRowDto.setDescription(((TechnicalRow)technicalRow).getDescription());
		technicalRowDto.setTechnicalField((TechnicalFieldViewHelper) new TechnicalFieldViewHelper().setEntity(((TechnicalRow)technicalRow).getField()));
		return technicalRowDto;
	}
}
