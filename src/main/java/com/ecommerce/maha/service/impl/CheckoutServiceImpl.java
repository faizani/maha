package com.ecommerce.maha.service.impl;

import com.ecommerce.maha.core.usecase.CheckoutPriceCalculatorUseCase;
import com.ecommerce.maha.dto.PriceDto;
import com.ecommerce.maha.error.ValidationErrorType;
import com.ecommerce.maha.service.CheckoutService;
import com.ecommerce.maha.service.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutPriceCalculatorUseCase checkoutPriceCalculatorUseCase;

    @Override
    public PriceDto checkout(List<String> watchCodes) {

        if (CollectionUtils.isEmpty(watchCodes)) {
            log.error("Invalid data for cart watches.");
            throw new ServiceException(ValidationErrorType.INVALID_DATA);
        }

        BigDecimal price = checkoutPriceCalculatorUseCase.checkout(watchCodes);
        return toDto(price);
    }

    private PriceDto toDto(BigDecimal price) {
        return PriceDto.builder().price(price).build();
    }
}
