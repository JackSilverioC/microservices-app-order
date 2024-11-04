package com.dev.product_service.service;

import com.dev.product_service.dto.ProductRequest;
import com.dev.product_service.dto.ProductResponseDto;
import com.dev.product_service.mapper.ProductMapper;
import com.dev.product_service.model.Product;
import com.dev.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequest productRequest) {
        Product product = productRepository.save(
                Product.builder()
                        .name(productRequest.name())
                        .description(productRequest.description())
                        .price(productRequest.price())
                        .build()
        );

        log.info("Product created: {}",product);
        return productMapper.fromProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> findAllProducts() {
        return productRepository.findAll()
                .stream().map(productMapper::fromProductToProductResponseDto)
                .collect(Collectors.toList());
    }
}
