package com.centric.controller;

import com.centric.domain.Product;
import com.centric.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Api(value = "/v1/products")
@RestController
@RequestMapping(value = "/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "search products for given category {name}.", responseContainer = "Product")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error occurred")})
    @ApiParam(value = "Contact's {id}", required = true)
    @GetMapping(value = "/list")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam("categoryName") String categoryName,
                                                        @RequestParam(required = false, defaultValue = "0") Integer pageNo,
                                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        List<Product> list = productService.getAllProducts(categoryName, pageNo, pageSize);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Adds a new product. It is necessary to fill in the attributes as shown in the 'Example Value'.",
            responseContainer = "Product")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error occurred")})
    @PostMapping(value = "/create")
    public ResponseEntity<Product> createContact(@RequestBody Product product) {
        String isoDate = getISO8601StringForCurrentDate();
        product.setCreatedAt(isoDate);
        productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    /**
     * Return an ISO 8601 combined date and time string for specified date/time
     *
     * @return String with format "yyyy-MM-dd'T'HH:mm:ss'Z'"
     */
    private String getISO8601StringForCurrentDate() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        // Quoted "Z" to indicate UTC, no timezone offset
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        return df.format(new Date());
    }
}
