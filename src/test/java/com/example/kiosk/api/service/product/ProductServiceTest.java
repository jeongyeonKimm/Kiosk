package com.example.kiosk.api.service.product;

import com.example.kiosk.api.service.product.response.ProductResponse;
import com.example.kiosk.domain.product.Product;
import com.example.kiosk.domain.product.ProductRepository;
import com.example.kiosk.domain.product.ProductSellingStatus;
import com.example.kiosk.domain.product.ProductType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static com.example.kiosk.domain.product.ProductSellingStatus.SELLING;
import static com.example.kiosk.domain.product.ProductSellingStatus.SOLD_OUT;
import static com.example.kiosk.domain.product.ProductType.BAKERY;
import static com.example.kiosk.domain.product.ProductType.HANDMADE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @DisplayName("전체 상품을 조회하면 조회된 상품의 정보를 반환한다.")
    @Test
    void getProducts() {
        // given
        Product product1 = createProduct("001", "아메리카노", 4000, HANDMADE, SELLING);
        Product product2 = createProduct("002", "카페라떼", 4500, HANDMADE, SELLING);
        Product product3 = createProduct("003", "소금빵", 3500, BAKERY, SOLD_OUT);

        given(productRepository.findAll()).willReturn(List.of(product1, product2, product3));

        // when
        List<ProductResponse> products = productService.getProducts();

        // then
        assertThat(products).hasSize(3);
    }

    @DisplayName("등록된 상품이 없으면 전체 상품을 조회했을 때 크기가 0이다.")
    @Test
    void getProductsWhenProductsAreEmpty() {
        // given
        given(productRepository.findAll()).willReturn(Collections.emptyList());

        // when
        List<ProductResponse> products = productService.getProducts();

        // then
        assertThat(products).hasSize(0);
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
