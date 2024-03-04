package com.project.assetManage.controller;

import com.project.assetManage.service.ComputeIncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Products", description = "회원 산출 소득")
@RestController
@RequestMapping("visitor/computeIncome")
public class VisitorController {

    private ComputeIncomeService computeIncomeService;

    @Autowired
    public VisitorController(ComputeIncomeService computeIncomeService) {
        this.computeIncomeService = computeIncomeService;
    }

    @Operation(summary = "비회원산출소득 산출", description = "비회원산출소득을 산출한다.")
    @RequestMapping(value = "/select", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> selectVisitorComputeIncome(@RequestParam(name = "yearIncome") Long yearIncome, @RequestParam(name = "savingRatios") Integer savingRatios, Model model) {
        // 서비스로 파라미터 전달하고 결과 받기
        String cnsmpInclnCd = computeIncomeService.selectVisitorComputeIncome(yearIncome, savingRatios);

        // 결과를 모델에 추가
        model.addAttribute("cnsmpInclnCd", cnsmpInclnCd);

        return ResponseEntity.ok("insertComputeIncome Request received successfully");
    }




}
