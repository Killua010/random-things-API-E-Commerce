package br.com.randomthings.controller.specific;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.services.reports.web.ReportServiceWeb;

@CrossOrigin
@RestController
@RequestMapping("/reports")
public class ReportController {
	
	@Autowired
	private ReportServiceWeb reportServiceWeb;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/ordersCategory", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersCategory(){
		return ResponseEntity.ok(reportServiceWeb.getOrdersCategoryByMounths());
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/ordersCategory/{startDate}/{endDate}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersCategoryByDate(@PathVariable("startDate") String start, @PathVariable("endDate") String end){
		return ResponseEntity.ok(reportServiceWeb.getOrdersCategoryByDate(LocalDateTime.parse(start+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), LocalDateTime.parse(end+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/ordersCategory/gender/{gender}/{mounth}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersCategoryGender(@PathVariable("gender") String gender, @PathVariable("mounth") int mounth){
		return ResponseEntity.ok(reportServiceWeb.getOrdersCategoryGender(gender, mounth));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/ordersCategory/gender/{gender}/age/{start}/{end}/{mounth}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersCategoryGenderAge(@PathVariable("gender") String gender, @PathVariable("start") int start, @PathVariable("end") int end,
			@PathVariable("mounth") int mounth){
		return ResponseEntity.ok(reportServiceWeb.getOrdersCategoryGenderAge(gender, start, end, mounth));
	}
		
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/orders", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrders(){
		return ResponseEntity.ok(Arrays.asList(reportServiceWeb.getOrdersByMounths()));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/orders/categories/{mounth}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersByCategories(@PathVariable("mounth") int mounth){
		return ResponseEntity.ok(reportServiceWeb.getOrdersByCategory(mounth));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/orders/products/{mounth}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersByProducts(@PathVariable("mounth") int mounth){
		return ResponseEntity.ok(reportServiceWeb.getOrdersByProducts(mounth));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/ordersProduct", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersProduct(){
		return ResponseEntity.ok(reportServiceWeb.getOrdersProductByMounths());
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/ordersProduct/{startDate}/{endDate}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersProductByDate(@PathVariable("startDate") String start, @PathVariable("endDate") String end){
		return ResponseEntity.ok(reportServiceWeb.getOrdersProductByDate(LocalDateTime.parse(start+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), LocalDateTime.parse(end+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/ordersProduct/gender/{gender}/{mounth}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersProductGender(@PathVariable("gender") String gender, @PathVariable("mounth") int mounth){
		return ResponseEntity.ok(reportServiceWeb.getOrdersProductGender(gender, mounth));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/ordersProduct/gender/{gender}/age/{start}/{end}/{mounth}", method=RequestMethod.GET)
	public ResponseEntity<?> getAllOrdersProductGenderAge(@PathVariable("gender") String gender, @PathVariable("start") int start, @PathVariable("end") int end,
			@PathVariable("mounth") int mounth){
		return ResponseEntity.ok(reportServiceWeb.getOrdersProductGenderAge(gender, start, end, mounth));
	}
	
}
