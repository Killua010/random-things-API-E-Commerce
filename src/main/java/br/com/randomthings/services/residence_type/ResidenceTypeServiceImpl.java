package br.com.randomthings.services.residence_type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.ResidenceType;
import br.com.randomthings.repository.ResidenceTypeRepository;

@Service
public class ResidenceTypeServiceImpl implements ResidenceTypeService {
	
	@Autowired
	private ResidenceTypeRepository residenceTypeRepository;

	@Override
	public List<ResidenceType> findAll() {
		return residenceTypeRepository.findAll();
	}

}
