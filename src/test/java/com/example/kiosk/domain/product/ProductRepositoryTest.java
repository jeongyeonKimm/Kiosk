package com.example.kiosk.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("전체 메뉴를 조회한다.")
    @Test
    void getProducts() {
        // given
        Product product1 = createProduct("001", "아메리카노", 4000, ProductType.BEVERAGE, ProductSellingStatus.SELLING);
        Product product2 = createProduct("002", "카페라떼", 4500, ProductType.BEVERAGE, ProductSellingStatus.SELLING);
        Product product3 = createProduct("003", "소금빵", 3500, ProductType.BAKERY, ProductSellingStatus.SOLD_OUT);

        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        List<Product> products = productRepository.findAll();

        // then
        assertThat(products).hasSize(3)
                .extracting("productNumber", "name", "price", "productType", "sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", 4000, ProductType.BEVERAGE, ProductSellingStatus.SELLING),
                        tuple("002", "카페라떼", 4500, ProductType.BEVERAGE, ProductSellingStatus.SELLING),
                        tuple("003", "소금빵", 3500, ProductType.BAKERY, ProductSellingStatus.SOLD_OUT)
                );
    }

    private Product createProduct(String productNumber, String name, Integer price, ProductType productType, ProductSellingStatus sellingStatus) {
        return Product.builder()
                .productNumber(productNumber)
                .name(name)
                .price(price)
                .productType(productType)
                .sellingStatus(sellingStatus)
                .build();
    }
}
