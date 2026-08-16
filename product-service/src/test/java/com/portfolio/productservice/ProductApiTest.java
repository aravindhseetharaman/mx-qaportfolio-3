package com.portfolio.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductApiTest {

    @Autowired
    private ProductController controller;

    @Test
    void contextLoadsWithController() {
        assertThat(controller).isNotNull();
    }

    @Test
    void shouldReturnThreeProducts() {
        var response = controller.getAllProducts();
        assertThat(response.getBody()).hasSize(3);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    void shouldReturnLaptopForId1() {
        var response = controller.getProduct(1L);
        assertThat(response.getBody().getName()).isEqualTo("Laptop");
        assertThat(response.getBody().getPrice()).isEqualTo(999.99);
        assertThat(response.getBody().getStock()).isEqualTo(50);
    }

    @Test
    void shouldCreateProductWithGeneratedId() {
        var product = new Product(null, "Keyboard", 79.99, 200);
        var response = controller.createProduct(product);
        assertThat(response.getStatusCode().value()).isEqualTo(201);
        assertThat(response.getBody().getName()).isEqualTo("Keyboard");
        assertThat(response.getBody().getId()).isNotNull();
    }
}