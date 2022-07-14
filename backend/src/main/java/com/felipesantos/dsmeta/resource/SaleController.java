package com.felipesantos.dsmeta.resource;

import com.felipesantos.dsmeta.entities.Sale;
import com.felipesantos.dsmeta.services.SaleService;
import com.felipesantos.dsmeta.services.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;
    private final SmsService smsService;

    @GetMapping
    public Page<Sale> findSales(@RequestParam(defaultValue = "") String initialDate,
                                @RequestParam(defaultValue = "") String finalDate,
                                Pageable pageable) {
        return saleService.findSales(initialDate, finalDate, pageable);
    }

    @PostMapping("{id}/notification")
    public void notifySms(@PathVariable Long id) {
        smsService.sendSms(id);
    }
}
