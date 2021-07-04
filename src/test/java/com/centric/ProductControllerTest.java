package com.centric;

import com.centric.domain.Product;
import com.centric.repository.ProductRepository;
import com.centric.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @MockBean
    ProductRepository productRepository;


    @Before
    public void init() {
        Product p = new Product();
        p.setCreatedAt(new Date().toString());
        p.setBrand("Hugo Boss");
        p.setTags(Arrays.asList("shirt", "shirt", "slim fit"));
        p.setCategory("apparel");
        List<Product> productList = Arrays.asList(p);
        given(productService.getAllProducts(any(), any(), any())).willReturn(productList);
    }


    @Test
    public void save_product_OK() throws Exception {
        Product newProduct = new Product();
        when(productRepository.save(any(Product.class))).thenReturn(newProduct);
        mockMvc.perform(post("/v1/products/create")
                .content(new ObjectMapper().writeValueAsString(newProduct))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        verify(productService, times(1)).save(any(Product.class));
    }

    @Test
    public void get_product_list() throws Exception {
        this.mockMvc.perform(get("/v1/products/list?categoryName=apparel")).andExpect(status().isOk());
        verify(productService, times(1)).getAllProducts(any(), any(), any());
    }

    @Test
    public void find_product_404() throws Exception {
        mockMvc.perform(get("/v1/products/notfound"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
