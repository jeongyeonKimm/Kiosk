package com.example.kiosk.api.service.product.response;

import com.example.kiosk.domain.product.Product;
import com.example.kiosk.domain.product.ProductSellingStatus;
import com.example.kiosk.domain.product.ProductType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {

    private Long id;
    private String productNumber;
    private String name;
    private Integer price;
    private ProductType productType;
    private ProductSellingStatus sellingStatus;

    @Builder
    private ProductResponse(Long id, String productNumber, String name, Integer price, ProductType productType, ProductSellingStatus sellingStatus) {
        this.id = id;
        this.productNumber = productNumber;
        this.name = name;
        this.price = price;
        this.productType = productType;
        this.sellingStatus = sellingStatus;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .productNumber(product.getProductNumber())
                .name(product.getName())
                .price(product.getPrice())
                .productType(product.getProductType())
                .sellingStatus(product.getSellingStatus())
                .build();
    }
}
