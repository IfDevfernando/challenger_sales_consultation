package com.devsuperior.dsmeta.services;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SellerReportDTO;
import com.devsuperior.dsmeta.dto.SellerSumDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public List<SellerSumDTO> findSellerAmout(String minDate,String maxDate) {
		
		try {
		LocalDate minDateStrin = null;
		LocalDate maxDateStrin = null;
		
		if(minDate != null && !minDate.isEmpty()) {
			minDateStrin = LocalDate.parse(minDate);
			
		}
		if(maxDate != null && !maxDate.isEmpty()) {
			maxDateStrin = LocalDate.parse(maxDate);
		}
		
		if(minDateStrin == null && maxDateStrin == null) {
			
			LocalDate local= LocalDate.now();
			
			minDateStrin = local.minusYears(1);
			maxDateStrin = local;
			
			List<SellerSumDTO> summary = repository.sellerSummary(minDateStrin, maxDateStrin);
			return summary;
			
		}
		
			List<SellerSumDTO> result = repository.sumSeller(minDateStrin, maxDateStrin);
			return result;
		
		
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Page<SellerReportDTO> sellerReport( String minDate,String maxDate,String sellerName,Pageable pageable){
		
		try {
			LocalDate minDateStrin = null;
			LocalDate maxDateStrin = null;
			
			if(minDate != null && !minDate.isEmpty()) {
				minDateStrin = LocalDate.parse(minDate);
				
			}
			if(maxDate != null && !maxDate.isEmpty()) {
				maxDateStrin = LocalDate.parse(maxDate);
			}
			if(sellerName == null) {
				sellerName = " ";
			}
			
			if(minDateStrin == null && maxDateStrin == null) {
				
				LocalDate today= LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
				
				minDateStrin = today.minusYears(1);
				maxDateStrin = today;
				
				Page<SellerReportDTO> summary = repository.sellerReport(minDateStrin, maxDateStrin,pageable);
				return summary;
				
			}
			
				Page<SellerReportDTO> result = repository.sellerReportDate(minDateStrin, maxDateStrin,sellerName,pageable);
				return result;
			
			
			}catch(DateTimeException e) {
				 e.printStackTrace();
				 return null;
				 
			}
		
	}
}
	

	
