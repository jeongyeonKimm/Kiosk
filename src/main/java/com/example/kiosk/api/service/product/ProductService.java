package com.example.kiosk.api.service.product;

import com.example.kiosk.api.service.product.response.ProductResponse;
import com.example.kiosk.domain.product.Product;
import com.example.kiosk.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
