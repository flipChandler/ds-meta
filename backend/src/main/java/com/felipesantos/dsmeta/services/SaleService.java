package com.felipesantos.dsmeta.services;

import com.felipesantos.dsmeta.entities.Sale;
import com.felipesantos.dsmeta.repositories.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;

    public Page<Sale> findSales(String initialDateParam,
                                String finalDateParam,
                                Pageable pageable) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate initialDate = initialDateParam.equals("")
                ? today.minusDays(365)
                : LocalDate.parse(initialDateParam);

        LocalDate finalDate = finalDateParam.equals("")
                ? today
                : LocalDate.parse(finalDateParam);

        return saleRepository.findSales(initialDate, finalDate, pageable);
    }

    public Sale findById(Long id) {
        return saleRepository.findById(id).get();
    }
}
