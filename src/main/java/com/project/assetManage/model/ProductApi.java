package com.project.assetManage.model;


import com.project.assetManage.entity.Product;
import com.project.assetManage.repository.ProductRepository;
import com.project.assetManage.util.HttpClientResult;
import com.project.assetManage.util.HttpConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ProductApi {

    private HttpConnection httpConnection;
    private ProductRepository productRepository;

    public ProductApi(HttpConnection httpConnection, ProductRepository productRepository) {
        this.httpConnection = httpConnection;
        this.productRepository = productRepository;
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

        List<Map<String, String>> resultList = getMaps(httpResult);

        List<Product> productList = new ArrayList<>();
        for (Map<String, String> resultMap : resultList) {
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
                .maxLmit(resultMap.get("max_limit").equals("null") ? null
                    : Integer.parseInt(resultMap.get("max_limit")))
                .dclsStrtDay(resultMap.get("dcls_strt_day"))
                .dclsEndDay(resultMap.get("dcls_end_day"))
                .finCoSubmDay(resultMap.get("fin_co_subm_day"))
                .intrRateType(resultMap.get("intr_rate_type"))
                .intrRateTypeNm(resultMap.get("intr_rate_type_nm"))
                .saveTrm(resultMap.get("save_trm"))
                .intrRate(resultMap.get("intr_rate") != null ? Double.parseDouble(resultMap.get("intr_rate")) : null)
                .intrRate2(resultMap.get("intr_rate2") != null ? Double.parseDouble(resultMap.get("intr_rate2")) : null)
                .rsrvType(resultMap.get("rsrv_type"))
                .rsrvTypeNm(resultMap.get("rsrv_type_nm"))
                .build();

            productList.add(product);
        }

        productRepository.saveAll(productList);
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

        List<Map<String, String>> resultList = getMaps(httpResult);

        List<Product> productList = new ArrayList<>();
        for (Map<String, String> resultMap : resultList) {
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
                .maxLmit(resultMap.get("max_limit").equals("null") ? null
                    : Integer.parseInt(resultMap.get("max_limit")))
                .dclsStrtDay(resultMap.get("dcls_strt_day"))
                .dclsEndDay(resultMap.get("dcls_end_day"))
                .intrRateType(resultMap.get("intr_rate_type"))
                .intrRateTypeNm(resultMap.get("intr_rate_type_nm"))
                .saveTrm(resultMap.get("save_trm"))
                .intrRate(resultMap.get("intr_rate") != null ? Double.parseDouble(resultMap.get("intr_rate")) : null)
                .intrRate2(resultMap.get("intr_rate2") != null ? Double.parseDouble(resultMap.get("intr_rate2")) : null)
                .build();

            productList.add(product);
        }

        productRepository.saveAll(productList);
    }


    private static List<Map<String, String>> getMaps(HttpClientResult httpResult) {
        try {
            JSONObject jsonObject = new JSONObject(httpResult.getContent());

            JSONObject resultObject = jsonObject.getJSONObject("result");
            JSONArray baseListArray = resultObject.getJSONArray("baseList");
            JSONArray optionListArray = resultObject.getJSONArray("optionList");

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

            for(int i=0; i<optionListArray.length(); i++){
                JSONObject optionObject = optionListArray.getJSONObject(i);
                Map<String, String> optsMap = new HashMap<>();

                Iterator<String> keys = optionObject.keys();
                while(keys.hasNext()){
                    String key = keys.next();
                    String value = optionObject.getString(key);
                    optsMap.put(key, value);
                }

                resultList.add(optsMap);
            }

            return resultList;
        } catch (JSONException e) {
            throw new IllegalArgumentException("Error parsing JSON content");
        }
    }

}
