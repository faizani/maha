package com.ecommerce.maha.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "watch_code")
    private String watchCode;

    @Column(name = "watch_name")
    private String watchName;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "watch")
    private Discount discount;

}
