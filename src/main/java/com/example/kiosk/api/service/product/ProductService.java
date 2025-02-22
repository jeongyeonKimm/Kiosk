package com.example.kiosk.api.service.product;

import com.example.kiosk.domain.product.Product;
import com.example.kiosk.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
