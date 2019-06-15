package br.com.randomthings.services.category.web;

import java.util.List;

import br.com.randomthings.dto.CategoryDTO;

public interface CategoryServiceWeb {
	public List<CategoryDTO> getPopularCategory();
}
