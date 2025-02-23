package com.example.kiosk.domain.product;

import com.example.kiosk.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productNumber;
    private String name;
    private Integer price;

    @Enumerated(value = EnumType.STRING)
    private ProductType productType;

    @Enumerated(value = EnumType.STRING)
    private ProductSellingStatus sellingStatus;

    @Builder
    private Product(String productNumber, String name, Integer price, ProductType productType, ProductSellingStatus sellingStatus) {
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.productType = productType;
        this.sellingStatus = sellingStatus;
    }
}
