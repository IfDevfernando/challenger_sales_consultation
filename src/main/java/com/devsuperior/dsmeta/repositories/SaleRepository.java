package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devsuperior.dsmeta.dto.SellerReportDTO;
import com.devsuperior.dsmeta.dto.SellerSumDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	
	@Query(" SELECT new com.devsuperior.dsmeta.dto.SellerSumDTO ( t.seller.name , SUM( t.amount )) "
			+ " FROM Sale t "
			+ " WHERE t.date BETWEEN :minDate AND :maxDate "
			+ " GROUP BY t.seller.name ")
	public List<SellerSumDTO> sumSeller(@Param("minDate") LocalDate minDate,@Param("maxDate")LocalDate maxDate);
	
	@Query(" SELECT new com.devsuperior.dsmeta.dto.SellerSumDTO ( t.seller.name ,  t.amount ) "
			+ " FROM Sale t "
			+ " WHERE ( :minDate IS NULL OR  :maxDate IS NULL OR t.date BETWEEN :minDate AND :maxDate ) ")
	public List<SellerSumDTO> sellerSummary(@Param("minDate") LocalDate minDate,@Param("maxDate")LocalDate maxDate);
	
	
	@Query("SELECT NEW com.devsuperior.dsmeta.dto.SellerReportDTO (sl.id, t.date, t.amount, sl.name ) "
			+ "FROM Sale t "
			+ "JOIN t.seller sl "
			+ "WHERE t.date BETWEEN :minDate AND :maxDate "
			+ "AND sl.name LIKE %:sellerName% ")
	public Page<SellerReportDTO> sellerReportDate(@Param("minDate") LocalDate minDate,@Param("maxDate") LocalDate maxDate,
											  @Param("sellerName") String sellerName, Pageable pageable);
	
	@Query("SELECT NEW com.devsuperior.dsmeta.dto.SellerReportDTO (sl.id, t.date, t.amount, sl.name ) "
			+ "FROM Sale t "
			+ "JOIN t.seller sl "
			+ "WHERE t.date BETWEEN :minDate AND :maxDate ")
	public Page<SellerReportDTO> sellerReport(@Param("minDate") LocalDate minDate,@Param("maxDate") LocalDate maxDate, Pageable pageable);
	
	
}
