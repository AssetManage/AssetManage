package com.project.assetManage.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.project.assetManage.dto.ProductDto;
import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.entity.Product;
import com.project.assetManage.service.ProductOptionService;
import com.project.assetManage.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Recommand", description = "상품 목록 조회")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@RequestMapping("st/product")
public class RecommandController {
    private final ProductService productService;
    private final ProductOptionService productOptionService;

    @Autowired
    public RecommandController(ProductService productService, ProductOptionService productOptionService) {
        this.productService = productService;
        this.productOptionService = productOptionService;
    }

    @Operation(summary = "상품 목록 조회", description = "임시")
    @GetMapping("/selectProductList")
    public Map<String, Object> selectProductList(@Nullable @RequestParam Map<String,Object> param, HttpSession session) {
        Map<String, Object> ret = new HashMap<>();
        List<ProductDto.ResponseAll> productList = productService.selectProductList(param);
        ret.put("list", productList);
        return ret;
    }

    @Operation(summary = "상품 옵션 목록 조회", description = "임시")
    @GetMapping("/selectProductOptionList")
    public Map<String, Object> selectProductOptionListSub(@Nullable @RequestParam Map<String,Object> param, HttpSession session) {
        Map<String, Object> ret = new HashMap<>();

        int size = 0;
        String stat = "SUCCESS";
        // List<ProductOptionDto> productOptionList = productOptionService.selectProductOptionListSub(param);
        List<ProductOptionDto.ResponseAll> productOptionList = productOptionService.selectProductOptionList(param);

        // TO-DO :: 에러 및 리턴값 모듈화
        ret.put("size", productOptionList.size());
        ret.put("stat", stat);
        ret.put("list", productOptionList);

        return ret;
    }

}
