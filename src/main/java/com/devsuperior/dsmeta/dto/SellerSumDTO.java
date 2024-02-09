package com.devsuperior.dsmeta.dto;

public class SellerSumDTO {
	
	private String sellerName;
	private Double total;
	
	
	public SellerSumDTO(String sellerName, Double total) {
		
		this.sellerName = sellerName;
		this.total = total;
	}


	public String getSellerName() {
		return sellerName;
	}

	public Double getTotal() {
		return total;
	}
	
	
	
	
	
	

}
