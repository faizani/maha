package com.ecommerce.maha.core.usecase;

import java.math.BigDecimal;
import java.util.List;

public interface CheckoutPriceCalculatorUseCase {

    BigDecimal checkout(List<String> watchCodes);
}