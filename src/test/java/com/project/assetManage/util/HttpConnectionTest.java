package com.project.assetManage.util;

import com.project.assetManage.entity.Product;
import com.project.assetManage.model.ProductApi;
import com.project.assetManage.repository.ProductRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HttpConnectionTest {

    @Autowired
    private HttpConnection httpConnection;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductApi productApi;

    @Test
    void get2() throws Exception {
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

        HttpClientResult httpResult = httpConnection.doGet(url, header,params);

        List<Map<String, String>> resultList = getMaps(httpResult);

        List<Product> productList = new ArrayList<>();
        for (Map<String, String> resultMap : resultList) {
            Product product = Product.builder()
                    .finCoNo(resultMap.get("fin_co_no"))
                    .finPrdtCd(resultMap.get("fin_prdt_cd"))
//                    .actKind("deposit")
                    .dclsMonth(resultMap.get("dcls_month"))
                    .korCoNm(resultMap.get("kor_co_nm"))
                    .finPrdtNm(resultMap.get("fin_prdt_nm"))
                    .joinWay(resultMap.get("join_way"))
                    .mtrtInt(resultMap.get("mtrt_int"))
                    .spclCnd(resultMap.get("spcl_cnd"))
                    .joinDeny(resultMap.get("join_deny"))
                    .joinMember(resultMap.get("join_member"))
                    .etcNote(resultMap.get("etc_note"))
                    .maxLmit(resultMap.get("max_limit").equals("null") ? null : Integer.parseInt(resultMap.get("max_limit")))
                    .dclsStrtDay(resultMap.get("dcls_strt_day"))
                    .dclsEndDay(resultMap.get("dcls_end_day"))
                    .build();

            productList.add(product);
        }

        assertThat(productList.size() == 40);

//        productRepository.saveAll(productList);

    }

    private static List<Map<String, String>> getMaps(HttpClientResult httpResult) throws JSONException {
        JSONObject jsonObject = new JSONObject(httpResult.getContent());

        JSONObject resultObject = jsonObject.getJSONObject("result");
        JSONArray baseListArray = resultObject.getJSONArray("baseList");

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
    }

}