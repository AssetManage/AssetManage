package com.project.assetManage.controller;

import com.project.assetManage.model.ProductApi;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private ProductApi productApi;

    public ProductController(ProductApi productApi) {
        this.productApi = productApi;
    }

    public void getProductData() {
        productApi.getDepositData();;
    }
}
