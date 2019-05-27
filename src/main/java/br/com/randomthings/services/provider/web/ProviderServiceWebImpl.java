package br.com.randomthings.services.provider.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Provider;
import br.com.randomthings.dto.ProviderDTO;
import br.com.randomthings.services.ExecuteStrategys;
import br.com.randomthings.services.category.CategoryService;
import br.com.randomthings.services.provider.ProviderService;

@Service
public class ProviderServiceWebImpl extends ExecuteStrategys<Provider> implements ProviderServiceWeb {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProviderService providerService;
	
	@Override
	public ProviderDTO findByName(String name) {
		return (ProviderDTO) new ProviderDTO().from(providerService.findByName(name));
	}

	@Override
	public List<ProviderDTO> findByCategory(Long id) {
		Category category = categoryService.findById(id);
		
		List<Provider> provider = providerService.findByCategory(category);
		List<ProviderDTO> providerDTOs = new ArrayList<ProviderDTO>();
		
		for(Provider p: provider) {
			providerDTOs.add((ProviderDTO) new ProviderDTO().from(p));
		}
		
		return providerDTOs;
	}

}
