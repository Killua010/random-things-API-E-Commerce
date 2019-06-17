package br.com.randomthings.controller.specific;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.randomthings.domain.StockInput;
import br.com.randomthings.dto.StockInputDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.stock.web.StockInputServiceWeb;

@CrossOrigin
@RestController
@RequestMapping("/stockInputs")
public class StockInputController {
	
	@Autowired
	private StockInputServiceWeb stockInputServiceWeb;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(path="", method=RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody StockInputDTO stockInputDTO) throws StrategyValidation {
		StockInput stockInput = stockInputServiceWeb.save(stockInputDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stockInput.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(path="", method=RequestMethod.GET)
	public ResponseEntity<List<StockInputDTO>> getAll() {
		return ResponseEntity.ok(stockInputServiceWeb.getAll());
	}
}
