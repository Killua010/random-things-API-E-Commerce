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
import br.com.randomthings.services.AbstractService;

@Service
public class TechnicalFieldServiceImpl extends AbstractService<TechnicalField, Long> implements TechnicalFieldService {
	private final TechnicalFieldRepository technicalFieldRepository;
	
	private final TechnicalRowRepository technicalRowRepository;
	
	@Autowired
	public TechnicalFieldServiceImpl(TechnicalFieldRepository dao, TechnicalRowRepository technicalRowRepository) {
		super(dao);
		this.technicalFieldRepository = dao;
		this.technicalRowRepository = technicalRowRepository;
	}
	
	@Override
	@Transactional 
	public void deleteById(Long id) {
		TechnicalField technicalField = findById(id);
		for(TechnicalRow row: technicalRowRepository.findAllByField(technicalField)) {
			technicalRowRepository.delete(row);
		}
		technicalFieldRepository.delete(technicalField);
	}
}
