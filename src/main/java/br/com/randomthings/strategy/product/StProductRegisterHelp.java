package br.com.randomthings.strategy.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.PricingGroup;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.domain.TechnicalField;
import br.com.randomthings.domain.TechnicalRow;
import br.com.randomthings.repository.PricingGroupRepository;
import br.com.randomthings.repository.SubCategoryRepository;
import br.com.randomthings.repository.TechnicalFieldRepository;
import br.com.randomthings.strategy.IStrategy;
import br.com.randomthings.strategy.standard.StRegistration;

@Service
public class StProductRegisterHelp implements IStrategy<Product> {

	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private TechnicalFieldRepository technicalFieldRepository;
	
	@Autowired
	private PricingGroupRepository pricingGroupRepository;
	
	@Autowired
	private StRegistration stRegistration;
	
	@Override
	public String execute(Product entity) {
		
		Optional<PricingGroup> optionalPricingGroup = pricingGroupRepository.findById(entity.getPricingGroup().getId());
		if(!optionalPricingGroup.isPresent()) {
			return "A categoria de preço com id = " + entity.getPricingGroup().getId() + " é inexistente";
		}
		entity.setPricingGroup(optionalPricingGroup.get());
		
		for(SubCategory subCategory: entity.getSubCategory()) {
			Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(subCategory.getId());
			if(!optionalSubCategory.isPresent()) {
				return "SubCategoria com id = " + subCategory.getId() + " é inexistente";
			}
			subCategory = optionalSubCategory.get();
//			entity.getSubCategory().add(optionalSubCategory.get());
		}
		
		for(TechnicalRow row: entity.getTechnicalRows()) {
			Optional<TechnicalField> optionalTechnicalField = technicalFieldRepository.findById(row.getField().getId());
			if(!optionalTechnicalField.isPresent()) {
				return "O campo técnico com id = " + row.getField().getId() + " é inexistente";
			}
			row.setProduct(entity);
			row.setField(optionalTechnicalField.get());
			stRegistration.execute(row);
			entity.getTechnicalRows().add(row);
		}
		
		return "";
	}

}
