package br.com.randomthings.services.reports.web;

import java.util.List;

import br.com.randomthings.dto.specific.ReportDTO;

public interface ReportServiceWeb {
	public Integer[] getOrdersByMounths();
	
	public List<List<ReportDTO>> getOrdersCategoryByMounths();
	
	public List<ReportDTO> getOrdersCategoryGender(String gender, int mounth);
	
	public List<ReportDTO> getOrdersCategoryGenderAge(String gender, int start, int end, int mounth);
	
	public List<ReportDTO> getOrdersByCategory();
	
	public List<ReportDTO> getOrdersByProducts();
	
	public List<List<ReportDTO>> getOrdersProductByMounths();
	
	public List<ReportDTO> getOrdersProductGender(String gender, int mounth);
	
	public List<ReportDTO> getOrdersProductGenderAge(String gender, int start, int end, int mounth);
}
