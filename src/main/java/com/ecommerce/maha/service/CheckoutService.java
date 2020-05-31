package com.ecommerce.maha.service;

import com.ecommerce.maha.dto.PriceDto;

import java.util.List;

public interface CheckoutService {

    PriceDto checkout(List<String> cart);
}
