package com.project.assetManage.controller;

import com.project.assetManage.entity.Product;
import com.project.assetManage.model.ProductApi;
import com.project.assetManage.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Products", description = "상품 API")
@RestController
@RequestMapping("api/product")
public class ProductController {
    private ProductApi productApi;

    public ProductController(ProductApi productApi) {
        this.productApi = productApi;
    }

    @Operation(summary = "예적금 API 호출", description = "예적금API를 호출하여 데이터를 저장한다.")
    @GetMapping("/productData")
    public void getProductData() {
        productApi.getDepositData();
        productApi.getSavingData();
    }

    @PostMapping("/test")
    public String test(){
        return "success";
    }
}
