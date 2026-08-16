package com.portfolio.productservice;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProductControllerTest {
    private  ProductController controller = new ProductController();

    @Test
    void shouldReturnThreeproducts(){
        var response = controller.getAllProducts();
        assertThat(response.getBody()).hasSize(3);
    }

    @Test
    void shouldReturnLaptopforID1(){
        var response = controller.getProduct((1L));
        assertThat(response.getBody().getName()).isEqualTo("Laptop");
    }

    @Test
    void shouldCreateProduct(){
        var product = new Product(null, "Keyboard", 79.99, 200);
        var response = controller.createProduct(product);
        assertThat(response.getStatusCode().value()).isEqualTo(201);
        assertThat(response.getBody().getId()).isNotNull();
    }


}
