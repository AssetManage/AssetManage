package com.project.assetManage.controller;

import com.project.assetManage.service.ComputeIncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Products", description = "회원 산출 소득")
@RestController
@RequestMapping("user/computeIncome")
public class ComputeIncomeController {

    private ComputeIncomeService computeIncomeService;

    public ComputeIncomeController(ComputeIncomeService computeIncomeService) {
        this.computeIncomeService = computeIncomeService;
    }

    @Operation(summary = "회원산출소득", description = "회원산출소득 데이터를 저장한다.")
    @PostMapping("/insert")
    public ResponseEntity<String> insertComputeIncome(@RequestParam String userSeq) {
        computeIncomeService.insertComputeIncome(userSeq);
        return ResponseEntity.ok("ComputeIncome inserted successfully for userSeq: " + userSeq);
    }

    @Operation(summary = "회원산출소득 업데이트", description = "회원산출소득 데이터를 업데이트한다.")
    @PostMapping("/update")
    public void updateComputeIncome(){
        computeIncomeService.updateComputeIncome();
    }


}
