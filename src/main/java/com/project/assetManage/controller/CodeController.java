package com.project.assetManage.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.project.assetManage.dto.CodeDto;
import com.project.assetManage.service.CodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Code", description = "코드 목록 조회")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@RestController
@RequestMapping("st/code")
public class CodeController {
    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @Operation(summary = "코드 목록 조회", description = "공통 코드 목록을 조회한다.")
    @GetMapping("/selectCodeList")
    public CodeDto.Result selectCodeList(@Nullable @ModelAttribute CodeDto.Request param, HttpSession session) {

        String stat = "SUCCESS";
        List<CodeDto.ResponseAll> codeList = codeService.selectProductList(param);

        return new CodeDto.Result(stat, codeList);
    }

}
