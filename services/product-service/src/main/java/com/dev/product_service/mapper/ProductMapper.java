package com.dev.product_service.mapper;

import com.dev.product_service.dto.ProductResponseDto;
import com.dev.product_service.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto fromProductToProductResponseDto(Product product){
        return new ProductResponseDto(
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
