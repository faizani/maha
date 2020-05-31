package com.ecommerce.maha.controller;

import com.ecommerce.maha.dto.PriceDto;
import com.ecommerce.maha.service.CheckoutService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class CheckoutController {

    private final CheckoutService catalogService;

    @PostMapping(value = "/checkout")
    public PriceDto checkout(@RequestBody List<String> watchCodes) {

        log.info("checkout processing starting...");
        return catalogService.checkout(watchCodes);

    }
}
