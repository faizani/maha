package com.ecommerce.maha.core.usecase;

import com.ecommerce.maha.core.entity.Discount;
import com.ecommerce.maha.core.entity.Watch;
import com.ecommerce.maha.core.usecase.impl.CheckoutPriceCalculatorUseCaseImpl;
import com.ecommerce.maha.data.repository.WatchRepository;
import com.ecommerce.maha.error.ValidationErrorType;
import com.ecommerce.maha.service.exception.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutPriceCalculatorUseCaseTest {

    @Mock
    private WatchRepository watchRepository;

    @InjectMocks
    private CheckoutPriceCalculatorUseCaseImpl checkoutPriceCalculatorUseCase;

    @Test
    public void checkoutWatchesNotFoundTest() {

        String watchCodesList[] = { "005" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        Mockito.when(watchRepository.findByWatchCodeIn(new HashSet<>(watchCodes))).thenReturn(Collections.emptyList());
        ServiceException thrown = assertThrows(ServiceException.class,
                () -> checkoutPriceCalculatorUseCase.checkout(watchCodes));
        assertEquals(thrown.getAppMessage(), ValidationErrorType.UNABLE_TO_FIND_CART.getAppMessage());
    }

    @Test
    public void CheckoutTest() {

        String watchCodesList[] = { "003", "004" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        BigDecimal expectedPrice = new BigDecimal(80);
        Mockito.when(watchRepository.findByWatchCodeIn(new HashSet<>(watchCodes))).thenReturn(getWatches());
        BigDecimal price = checkoutPriceCalculatorUseCase.checkout(watchCodes);

        assertEquals(price, expectedPrice);
        verify(watchRepository, times(1)).findByWatchCodeIn(new HashSet<>(watchCodes));
    }

    private List<Watch> getWatches() {

        List<Watch> watches = new ArrayList<>();
        Watch Swatch = getWatch(3, "003", "Swatch", new BigDecimal(50), null);
        Watch Casio = getWatch(4, "004", "Casio", new BigDecimal(30), null);

        watches.add(Swatch);
        watches.add(Casio);

        return watches;
    }

    private Watch getWatch(Integer id, String watchCode, String watchName, BigDecimal unitPrice, Discount discount) {

        return new Watch(id, watchCode, watchName, unitPrice, discount);
    }

    private Discount getDiscount(Integer id, Watch watch, Integer minimumQunatityRequired, BigDecimal discountedValue) {
        return new Discount(id, watch, minimumQunatityRequired, discountedValue);
    }
}
