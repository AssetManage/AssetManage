package com.project.assetManage.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
public class HttpClientResult implements Serializable {

    private int code;
    private String content;

    public  HttpClientResult(int code){
        this.code=code;
    }

    public HttpClientResult(int code, String content) {
        this.code = code;
        this.content = content;
    }

}
