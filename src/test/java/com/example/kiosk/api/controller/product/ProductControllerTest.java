package com.example.kiosk.api.controller.product;

import com.example.kiosk.api.service.product.ProductService;
import com.example.kiosk.api.service.product.response.ProductResponse;
import com.example.kiosk.domain.product.Product;
import com.example.kiosk.domain.product.ProductSellingStatus;
import com.example.kiosk.domain.product.ProductType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.antlr.v4.runtime.misc.IntegerStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.stream.IntStream;

import static com.example.kiosk.domain.product.ProductSellingStatus.SELLING;
import static com.example.kiosk.domain.product.ProductSellingStatus.SOLD_OUT;
import static com.example.kiosk.domain.product.ProductType.BAKERY;
import static com.example.kiosk.domain.product.ProductType.HANDMADE;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;


    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .build();
        objectMapper = new ObjectMapper();
    }

    @DisplayName("전체 상품을 조회한다.")
    @Test
    void getProducts() throws Exception {
        // given
        Product product1 = createProduct("001", "아메리카노", 4000, HANDMADE, SELLING);
        Product product2 = createProduct("002", "카페라떼", 4500, HANDMADE, SELLING);
        Product product3 = createProduct("003", "소금빵", 3500, BAKERY, SOLD_OUT);
        List<Product> products = List.of(product1, product2, product3);
        List<ProductResponse> productResponses = IntStream.range(0, 3)
                .mapToObj(i -> ProductResponse.builder()
                        .id((long) (i + 1))
                        .productNumber(products.get(i).getProductNumber())
                        .name(products.get(i).getName())
                        .price(products.get(i).getPrice())
                        .productType(products.get(i).getProductType())
                        .sellingStatus(products.get(i).getSellingStatus())
                        .build()
                )
                .toList();

        given(productService.getProducts()).willReturn(productResponses);

        // when, then
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].productNumber").value("001"))
                .andExpect(jsonPath("$[0].name").value("아메리카노"))
                .andExpect(jsonPath("$[0].price").value("4000"))
                .andExpect(jsonPath("$[0].productType").value(HANDMADE.toString()))
                .andExpect(jsonPath("$[0].sellingStatus").value(SELLING.toString()))
                .andDo(print());
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
