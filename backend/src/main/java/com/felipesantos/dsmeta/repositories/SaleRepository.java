package com.felipesantos.dsmeta.repositories;

import com.felipesantos.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.date BETWEEN ?1 AND ?2 ORDER BY s.amount DESC")
    Page<Sale> findSales(LocalDate initialDate, LocalDate finalDate, Pageable pageable);
}
