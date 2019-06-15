package br.com.randomthings.services.reports.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.randomthings.dto.specific.ReportDTO;

public interface ReportServiceWeb {
	public Integer[] getOrdersByMounths();
	
	public List<List<ReportDTO>> getOrdersCategoryByMounths();
	
	public List<ReportDTO> getOrdersCategoryGender(String gender, int mounth);
	
	public List<ReportDTO> getOrdersCategoryGenderAge(String gender, int start, int end, int mounth);
	
	public List<ReportDTO> getOrdersByCategory(int mounth);
	
	public List<ReportDTO> getOrdersByProducts(int mounth);
	
	public List<List<ReportDTO>> getOrdersProductByMounths();
	
	public List<ReportDTO> getOrdersProductGender(String gender, int mounth);
	
	public List<ReportDTO> getOrdersProductGenderAge(String gender, int start, int end, int mounth);

	public List<List<ReportDTO>> getOrdersCategoryByDate(LocalDateTime start, LocalDateTime end);

	public List<List<ReportDTO>> getOrdersProductByDate(LocalDateTime start, LocalDateTime end);
}
