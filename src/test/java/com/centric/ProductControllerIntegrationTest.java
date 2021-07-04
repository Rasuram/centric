package com.centric;

import com.centric.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() {
        Product p = new Product();
        p.setCreatedAt(new Date().toString());
        p.setBrand("Hugo Boss");
        p.setTags(Arrays.asList("shirt", "shirt", "slim fit"));
        p.setCategory("apparel");
        Product result = this.restTemplate
                .postForObject("http://localhost:" + port + "/v1/products/create", p, Product.class);
        assertEquals("apparel", result.getCategory());
    }

    @Test
    public void testProductList() {
        ResponseEntity<List<Product>> productResponse =
                restTemplate.exchange("http://localhost:" + port + "/v1/products/list?categoryName=apparel",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
                        });
        assertEquals(200, productResponse.getStatusCodeValue());
    }
}

