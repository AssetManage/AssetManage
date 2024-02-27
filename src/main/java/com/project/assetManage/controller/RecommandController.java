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
    public ProductDto.Result selectProductList(@Nullable @ModelAttribute ProductOptionDto.Request param, HttpSession session) {

        String stat = "SUCCESS";
        // TO-DO :: 로그인한 경우, 사용자의 소득산출정보 조회
        List<ProductDto.ResponseAll> productList = productService.selectProductList(param);

        return new ProductDto.Result(stat, productList);
    }

    @Operation(summary = "상품 옵션 목록 조회", description = "임시")
    @GetMapping("/selectProductOptionList")
    public ProductOptionDto.Result selectProductOptionList(@Nullable @ModelAttribute ProductOptionDto.Request param, HttpSession session) {

        String stat = "SUCCESS";
        // List<ProductOptionDto.ResponseSimple> productOptionList = productOptionService.selectProductOptionListSub(param);
        List<ProductOptionDto.ResponseAll> productOptionList = productOptionService.selectProductOptionList(param);

        return new ProductOptionDto.Result(stat, productOptionList);
    }

    @Operation(summary = "상품과 대표 상품 옵션 통합 목록 조회", description = "임시")
    @GetMapping("/selectProductListWithOpt")
    public ProductDto.Result selectProductListWithOpt(@Nullable @ModelAttribute ProductOptionDto.Request param, HttpSession session) {

        String stat = "SUCCESS";
        // TO-DO :: 로그인한 경우, 사용자의 소득산출정보 조회
        List<ProductDto.ResponseCustom> productList = productService.selectProductListWithOpt(param);

        return new ProductDto.Result(stat, productList);
    }

}
