package com.ecommerce.maha.data.repository;

import com.ecommerce.maha.core.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
