package com.dev.product_service.service;

import com.dev.product_service.dto.ProductRequest;
import com.dev.product_service.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductRequest productRequest);
    List<ProductResponseDto> findAllProducts();
}
