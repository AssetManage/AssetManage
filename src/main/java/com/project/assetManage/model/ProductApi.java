package com.project.assetManage.model;


import com.project.assetManage.controller.ProductController;
import com.project.assetManage.entity.Product;
import com.project.assetManage.entity.ProductOption;
import com.project.assetManage.repository.ProductOptionRepository;
import com.project.assetManage.repository.ProductRepository;
import com.project.assetManage.util.HttpClientResult;
import com.project.assetManage.util.HttpConnection;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ProductApi {

    private HttpConnection httpConnection;
    private ProductRepository productRepository;
    private ProductOptionRepository productOptionRepository;

    public ProductApi(HttpConnection httpConnection, ProductRepository productRepository,
                      ProductOptionRepository productOptionRepository) {
        this.httpConnection = httpConnection;
        this.productRepository = productRepository;
        this.productOptionRepository = productOptionRepository;
    }


    public void getDepositData() {
        //예금
        String url = "http://finlife.fss.or.kr/finlifeapi/depositProductsSearch.json";

        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/octet-stream,application/json");
        header.put("Connection", "keep-alive");
        header.put("Content-Type", "application/json");

        Map<String, String> params = new HashMap<>();
        params.put("auth", "b34c0caf387a108a56f4bb8ef80341ed");
        params.put("topFinGrpNo", "020000");
        params.put("pageNo", "1");

        HttpClientResult httpResult = null;

        try {
            httpResult = httpConnection.doGet(url, header, params);
        } catch (Exception e) {
            throw new RuntimeException("httpConnection error : deposit data api error");
        }

        List<Map<String, String>> resultList = getMaps(httpResult, "baseList");

        List<Product> productList = new ArrayList<>();
        for (Map<String, String> resultMap : resultList) {

            if(resultMap.isEmpty() || resultMap == null){
                throw new RuntimeException("map is null");
            }

            String joinWayCd = getJoinWayCode(resultMap.get("join_way"));

            Product product = Product.builder()
                .finCoNo(resultMap.get("fin_co_no"))
                .finPrdtCd(resultMap.get("fin_prdt_cd"))
                .actKindCd("DP")
                .dclsMonth(resultMap.get("dcls_month"))
                .korCoNm(resultMap.get("kor_co_nm"))
                .finPrdtNm(resultMap.get("fin_prdt_nm"))
                .joinWay(resultMap.get("join_way"))
                .mtrtInt(resultMap.get("mtrt_int"))
                .spclCnd(resultMap.get("spcl_cnd"))
                .joinDeny(resultMap.get("join_deny"))
                .joinMember(resultMap.get("join_member"))
                .etcNote(resultMap.get("etc_note"))
                .maxLmit(resultMap.get("max_limit") == null || resultMap.get("max_limit").equals("null") ? null
                    : Integer.parseInt(resultMap.get("max_limit")))
                .dclsStrtDay(resultMap.get("dcls_strt_day"))
                .dclsEndDay(resultMap.get("dcls_end_day") == null || resultMap.get("dcls_end_day").equals("null") ? null : resultMap.get("dcls_end_day"))
                .joinWayCd(joinWayCd)
                .build();

            productList.add(product);
        }

        //option
        List<Map<String, String>> resultOptionList = getMaps(httpResult, "optionList");

        List<ProductOption> optionList = new ArrayList<>();

        // t_api_product_option :: prd_option_seq 수동 채번 2024.02.01
        // t_api_product pk 변수 :: 비교값
        String cmpFinCoNo = "";
        String cmpFinPrdtCd = "";
        String cmpDclsMonth = "";

        // t_api_product_option pk 변수 :: 수동 채번
        long prdOptionSeq = 1;
        // for문 cnt
        int cnt = 0;

        for (Map<String, String> resultMap : resultOptionList) {
            /*
            System.out.println("현재 resultMap ====================================");
            System.out.println(resultMap.toString());
            */
            final String finCoNo = resultMap.get("fin_co_no");
            final String finPrdtCd = resultMap.get("fin_prdt_cd");
            final String dclsMonth = resultMap.get("dcls_month");

            // 2번 로우부터 비교
            if(0<cnt){
                /*
                System.out.println("이전 resultMap ====================================");
                System.out.println(resultOptionList.get(cnt-1).toString());
                */
                cmpFinCoNo = resultOptionList.get(cnt-1).get("fin_co_no");
                cmpFinPrdtCd = resultOptionList.get(cnt-1).get("fin_prdt_cd");
                cmpDclsMonth = resultOptionList.get(cnt-1).get("dcls_month");

                if(finCoNo.equals(cmpFinCoNo) && finPrdtCd.equals(cmpFinPrdtCd) && dclsMonth.equals(cmpDclsMonth)){
                    prdOptionSeq ++;
                }else{
                    prdOptionSeq = 1;
                }
            }

            ProductOption productOpts = ProductOption.builder()
                .prdOptionSeq(prdOptionSeq)
                .finCoNo(finCoNo)
                .finPrdtCd(finPrdtCd)
                .dclsMonth(dclsMonth)
                .intrRateType(resultMap.get("intr_rate_type"))
                .intrRateTypeNm(resultMap.get("intr_rate_type_nm"))
                .saveTrm(Integer.parseInt(resultMap.get("save_trm")))
                .intrRate(resultMap.get("intr_rate") != null ? Double.parseDouble(resultMap.get("intr_rate")) : null)
                .intrRate2(resultMap.get("intr_rate2") != null ? Double.parseDouble(resultMap.get("intr_rate2")) : null)
                .build();

            optionList.add(productOpts);

            cnt ++;
        }

        saveProducts(productList);

        saveProductOptions(optionList);

    }


    public void getSavingData() {
        //적금
        String url = "http://finlife.fss.or.kr/finlifeapi/savingProductsSearch.json";

        Map<String, String> header = new HashMap<>();
        header.put("Accept", "application/octet-stream,application/json");
        header.put("Connection", "keep-alive");
        header.put("Content-Type", "application/json");

        Map<String, String> params = new HashMap<>();
        params.put("auth", "b34c0caf387a108a56f4bb8ef80341ed");
        params.put("topFinGrpNo", "020000");
        params.put("pageNo", "1");

        HttpClientResult httpResult = null;

        try {
            httpResult = httpConnection.doGet(url, header, params);
        } catch (Exception e) {
            throw new RuntimeException("httpConnection error : deposit data api error");
        }

        List<Map<String, String>> resultList = getMaps(httpResult, "baseList");

        List<Product> productList = new ArrayList<>();
        for (Map<String, String> resultMap : resultList) {
            if(resultMap.isEmpty() || resultMap == null){
                throw new RuntimeException("map is null");
            }


            String joinWayCd = getJoinWayCode(resultMap.get("join_way"));
            /*
            System.out.println("joinWay ====================================");
            System.out.println(joinWay);
            System.out.println("joinWayCd ====================================");
            System.out.println(joinWayCd);
            */
            Product product = Product.builder()
                    .finCoNo(resultMap.get("fin_co_no"))
                    .finPrdtCd(resultMap.get("fin_prdt_cd"))
                    .actKindCd("SV")
                    .dclsMonth(resultMap.get("dcls_month"))
                    .korCoNm(resultMap.get("kor_co_nm"))
                    .finPrdtNm(resultMap.get("fin_prdt_nm"))
                    .joinWay(resultMap.get("join_way"))
                    .mtrtInt(resultMap.get("mtrt_int"))
                    .spclCnd(resultMap.get("spcl_cnd"))
                    .joinDeny(resultMap.get("join_deny"))
                    .joinMember(resultMap.get("join_member"))
                    .etcNote(resultMap.get("etc_note"))
                    .maxLmit(resultMap.get("max_limit") == null || resultMap.get("max_limit").equals("null") ? null
                            : Integer.parseInt(resultMap.get("max_limit")))
                    .dclsStrtDay(resultMap.get("dcls_strt_day"))
                    .dclsEndDay(resultMap.get("dcls_end_day") == null || resultMap.get("dcls_end_day").equals("null") ? null : resultMap.get("dcls_end_day"))
                    .joinWayCd(joinWayCd)
                    .build();

            productList.add(product);
        }

        List<Map<String, String>> resultOptionList = getMaps(httpResult, "optionList");

        List<ProductOption> optionList = new ArrayList<>();

        // t_api_product_option :: prd_option_seq 수동 채번 2024.02.01
        // t_api_product pk 변수 :: 비교값
        String cmpFinCoNo = "";
        String cmpFinPrdtCd = "";
        String cmpDclsMonth = "";

        // t_api_product_option pk 변수 :: 수동 채번
        long prdOptionSeq = 1;
        // for문 cnt
        int cnt = 0;

        for (Map<String, String> resultMap : resultOptionList) {
            /*
            System.out.println("현재 resultMap ====================================");
            System.out.println(resultMap.toString());
            */
            final String finCoNo = resultMap.get("fin_co_no");
            final String finPrdtCd = resultMap.get("fin_prdt_cd");
            final String dclsMonth = resultMap.get("dcls_month");

            // 2번 로우부터 비교
            if(0<cnt){
                /*
                System.out.println("이전 resultMap ====================================");
                System.out.println(resultOptionList.get(cnt-1).toString());
                */
                cmpFinCoNo = resultOptionList.get(cnt-1).get("fin_co_no");
                cmpFinPrdtCd = resultOptionList.get(cnt-1).get("fin_prdt_cd");
                cmpDclsMonth = resultOptionList.get(cnt-1).get("dcls_month");

                if(finCoNo.equals(cmpFinCoNo) && finPrdtCd.equals(cmpFinPrdtCd) && dclsMonth.equals(cmpDclsMonth)){
                    prdOptionSeq ++;
                }else{
                    prdOptionSeq = 1;
                }
            }

            ProductOption productOpts = ProductOption.builder()
                    .prdOptionSeq(prdOptionSeq)
                    .finCoNo(finCoNo)
                    .finPrdtCd(finPrdtCd)
                    .dclsMonth(dclsMonth)
                    .intrRateType(resultMap.get("intr_rate_type"))
                    .intrRateTypeNm(resultMap.get("intr_rate_type_nm"))
                    .saveTrm(Integer.parseInt(resultMap.get("save_trm")))
                    .intrRate(resultMap.get("intr_rate") != null ? Double.parseDouble(resultMap.get("intr_rate")) : null)
                    .intrRate2(resultMap.get("intr_rate2") != null ? Double.parseDouble(resultMap.get("intr_rate2")) : null)
                    .rsrvType(resultMap.get("rsrv_type"))
                    .rsrvTypeNm(resultMap.get("rsrv_type_nm"))
                    .build();

            optionList.add(productOpts);

            cnt ++;
        }

        saveProducts(productList);

        saveProductOptions(optionList);
    }

    @Transactional
    public void saveProducts(List<Product> productList){
        productRepository.saveAll(productList);
    }
    @Transactional
    public void saveProductOptions(List<ProductOption> optionList){
        productOptionRepository.saveAll(optionList);
    }

    private static List<Map<String, String>> getMaps(HttpClientResult httpResult, String list) {
        try {
            JSONObject jsonObject = new JSONObject(httpResult.getContent());

            JSONObject resultObject = jsonObject.getJSONObject("result");
            JSONArray baseListArray = resultObject.getJSONArray(list);

            // Convert JSONArray to List of Maps
            List<Map<String, String>> resultList = new ArrayList<>();
            for (int i = 0; i < baseListArray.length(); i++) {
                JSONObject baseObject = baseListArray.getJSONObject(i);
                Map<String, String> baseMap = new HashMap<>();

                // Iterate over keys and put them into the map
                Iterator<String> keys = baseObject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    String value = baseObject.getString(key);
                    baseMap.put(key, value);
                }

                resultList.add(baseMap);
            }

            return resultList;
        } catch (JSONException e) {
            throw new IllegalArgumentException("Error parsing JSON content");
        }
    }

    public String getJoinWayCode(String joinWay){
        // 가입방법코드 추출 2024.02.02
        // join_way :: 영업점,인터넷,스마트폰,전화(텔레뱅킹) - 기타 제외
        String isin = "영업점";
        String joinWayCd = "A"; // 기본값 :: 전체
        // 비대면인 경우
        if(joinWay.indexOf(isin)<0){
            joinWayCd = "N";
        }else{
            // 대면인 경우
            if(joinWay.replaceAll("\\s+", "").equals(isin)){
                joinWayCd = "F";
            }
        }
        return joinWayCd;
    }

}