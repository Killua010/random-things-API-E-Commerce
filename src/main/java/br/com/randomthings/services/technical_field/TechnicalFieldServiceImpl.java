package br.com.randomthings.services.technical_field;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.TechnicalField;
import br.com.randomthings.domain.TechnicalRow;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.TechnicalFieldRepository;
import br.com.randomthings.repository.TechnicalRowRepository;

@Service
public class TechnicalFieldServiceImpl implements TechnicalFieldService {
	
	@Autowired
	private TechnicalFieldRepository technicalFieldRepository;
	
	@Autowired
	private TechnicalRowRepository technicalRowRepository;
	
	public TechnicalField findById(Long id) {		
		return technicalFieldRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + TechnicalField.class.getSimpleName()));
	}

	@Override
	@Transactional 
	public TechnicalField save(TechnicalField domain) {
		domain = technicalFieldRepository.save(domain);
		return domain;
	}

	@Override
	@Transactional 
	public void delete(Long id) {
		TechnicalField technicalField = findById(id);
		for(TechnicalRow row: technicalRowRepository.findAllByField(technicalField)) {
			technicalRowRepository.delete(row);
		}
		technicalFieldRepository.delete(technicalField);
	}

	@Override
	public List<TechnicalField> findAll() {
		return technicalFieldRepository.findAll(); 
	}
	
}
