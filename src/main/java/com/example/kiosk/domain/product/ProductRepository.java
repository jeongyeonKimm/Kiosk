package com.example.kiosk.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByProductTypeIn(List<ProductType> productTypes);

    List<Product> findAllBySellingStatusIn(List<ProductSellingStatus> sellingStatuses);
}
