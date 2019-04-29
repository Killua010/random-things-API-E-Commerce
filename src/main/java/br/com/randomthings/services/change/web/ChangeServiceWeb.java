package br.com.randomthings.services.change.web;

import java.util.List;

import br.com.randomthings.domain.Change;
import br.com.randomthings.dto.ChangeDTO;
import br.com.randomthings.dto.OrderDTO;

public interface ChangeServiceWeb {
	public Change save(ChangeDTO changeDTO);
	public List<ChangeDTO> getByStatus(String status);
	public Change aprovedChange(Long id);
	public Change reprovedChange(Long id);
	public List<ChangeDTO> getByIdClient(Long id);
}
