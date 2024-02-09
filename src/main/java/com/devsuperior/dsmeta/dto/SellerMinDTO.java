package com.devsuperior.dsmeta.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.entities.Seller;

public class SellerMinDTO {
	
	private Long id;
	private String name;
	private String email;
	
	private List<SaleMinDTO> sale;

	public SellerMinDTO(Long id, String name, String email, List<SaleMinDTO> sale) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.sale = sale;
	}
	
	public SellerMinDTO(Seller seller) {

		id = seller.getId();
		name = seller.getName();
		email =seller.getEmail();
		sale =seller.getSales().stream().map(x -> new SaleMinDTO(x)).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<SaleMinDTO> getSale() {
		return sale;
	}
	
	

}
