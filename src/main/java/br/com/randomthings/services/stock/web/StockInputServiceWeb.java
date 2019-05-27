package br.com.randomthings.services.stock.web;

import java.util.List;

import br.com.randomthings.domain.StockInput;
import br.com.randomthings.dto.StockInputDTO;

public interface StockInputServiceWeb {
	public StockInput save(StockInputDTO stockInputDTO);
	public List<StockInputDTO> getAll();
}
