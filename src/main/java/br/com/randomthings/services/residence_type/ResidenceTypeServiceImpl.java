package br.com.randomthings.services.residence_type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.ResidenceType;
import br.com.randomthings.repository.ResidenceTypeRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class ResidenceTypeServiceImpl extends AbstractService<ResidenceType, Long> implements ResidenceTypeService {

	@Autowired
	private ResidenceTypeRepository residenceTypeRepository;
	
	public ResidenceTypeServiceImpl(ResidenceTypeRepository dao) {
		super(dao);
	}

}
