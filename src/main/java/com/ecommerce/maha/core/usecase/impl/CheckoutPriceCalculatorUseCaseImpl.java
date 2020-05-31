package com.ecommerce.maha.core.usecase.impl;

import com.ecommerce.maha.core.entity.Watch;
import com.ecommerce.maha.core.usecase.CheckoutPriceCalculatorUseCase;
import com.ecommerce.maha.data.repository.WatchRepository;
import com.ecommerce.maha.error.ValidationErrorType;
import com.ecommerce.maha.service.exception.ServiceException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CheckoutPriceCalculatorUseCaseImpl implements CheckoutPriceCalculatorUseCase {

    private final WatchRepository watchRepository;

    @Override
    public BigDecimal checkout(List<String> watchCodes) {

        Map<String, Long> watchCodesCount = getWatchCodesAndCount(watchCodes);

        List<Watch> watches = watchRepository.findByWatchCodeIn(new HashSet<>(watchCodes));

        if(CollectionUtils.isEmpty(watches)) {
            throw new ServiceException(ValidationErrorType.UNABLE_TO_FIND_CART);
        }

        return getTotalPrice(watchCodesCount, watches);
    }

    private BigDecimal getTotalPrice(Map<String, Long> watchCodesCount, List<Watch> watches) {

        BigDecimal price = BigDecimal.ZERO;

        for (Watch watch : watches) {
            if (watch.getDiscount() != null) {
                price = price.add(getPriceForDiscountedItems(watchCodesCount, watch));
            } else {
                price = price.add(getPriceForNonDiscountedWatches(watchCodesCount, watch));
            }
        }
        return price;
    }

    private BigDecimal getPriceForNonDiscountedWatches(Map<String, Long> watchCodesCount, Watch watch) {

        return BigDecimal.valueOf(watchCodesCount.get(watch.getWatchCode())).multiply(watch.getUnitPrice());
    }

    private BigDecimal getPriceForDiscountedItems(Map<String, Long> watchCodesCount, Watch watch) {

        return (BigDecimal
                .valueOf(watchCodesCount.get(watch.getWatchCode()) % watch.getDiscount().getMinimumQuantityRequired())
                .multiply(watch.getUnitPrice())).add((BigDecimal.valueOf(watchCodesCount.get(watch.getWatchCode()))
                .subtract(BigDecimal.valueOf(
                        watchCodesCount.get(watch.getWatchCode()) % watch.getDiscount().getMinimumQuantityRequired())))
                .divide(BigDecimal.valueOf(watch.getDiscount().getMinimumQuantityRequired()))
                .multiply(watch.getDiscount().getDiscountedValue()));
    }

    private Map<String, Long> getWatchCodesAndCount(List<String> watchCodes) {

        return watchCodes.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
