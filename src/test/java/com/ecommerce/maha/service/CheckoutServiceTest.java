package com.ecommerce.maha.service;

import com.ecommerce.maha.core.usecase.CheckoutPriceCalculatorUseCase;
import com.ecommerce.maha.dto.PriceDto;
import com.ecommerce.maha.error.ValidationErrorType;
import com.ecommerce.maha.service.exception.ServiceException;
import com.ecommerce.maha.service.impl.CheckoutServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceTest {

    @Mock
    private CheckoutPriceCalculatorUseCase checkoutPriceCalculatorUseCase;

    @InjectMocks
    private CheckoutServiceImpl checkoutService;

    @Test
    public void checkoutTestInvalidInput() {

        List<String> watchCodes = Collections.emptyList();

        ServiceException thrown =
                assertThrows(ServiceException.class, () -> checkoutService.checkout(watchCodes));
        assertEquals(thrown.getAppMessage(), ValidationErrorType.INVALID_DATA.getAppMessage());
    }

    @Test
    public void checkoutTest() {

        String watchCodesList[] = {"001","002","001","003", "004"};
        List<String> watchCodes = new ArrayList(Arrays.asList(watchCodesList));

        BigDecimal expectedPrice = new BigDecimal(360);
        when(checkoutPriceCalculatorUseCase.checkout(watchCodes)).thenReturn(new BigDecimal(360));

        PriceDto priceDto = checkoutService.checkout(watchCodes);

        assertEquals(priceDto.getPrice(), expectedPrice);
        verify(checkoutPriceCalculatorUseCase, times(1)).checkout(watchCodes);
    }
}
