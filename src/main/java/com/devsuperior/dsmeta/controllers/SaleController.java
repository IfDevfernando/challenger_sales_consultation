package com.devsuperior.dsmeta.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerReportDTO;
import com.devsuperior.dsmeta.dto.SellerSumDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SellerReportDTO>>  getReport(@RequestParam(name ="minDate", required = false)String minDate,
															@RequestParam(name ="maxDate", required = false)String maxDate,
															@RequestParam(name="sellerName", required = false) String sellerName,
															Pageable pageable){
						
		Page<SellerReportDTO> report = service.sellerReport(minDate, maxDate, sellerName, pageable);
		
		return ResponseEntity.ok(report);
}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SellerSumDTO>> getSummary(@RequestParam(name ="minDate", required = false) String minDate,
														 @RequestParam(name ="maxDate", required = false)  String maxDate) {
		
		 List<SellerSumDTO> seller = service.findSellerAmout(minDate, maxDate);
		 
		 return ResponseEntity.ok(seller);
	
}
		
	
}
