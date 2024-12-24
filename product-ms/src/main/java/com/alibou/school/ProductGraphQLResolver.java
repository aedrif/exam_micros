package com.alibou.school;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGraphQLResolver {
    private final ProductService productService;

    public ProductGraphQLResolver(ProductService productService) {
        this.productService = productService;
    }

    public List<ProductDTO> getProducts(DataFetchingEnvironment env) {
        return productService.getAllProducts();
    }
}

