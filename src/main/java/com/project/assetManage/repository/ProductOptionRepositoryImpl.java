package com.project.assetManage.repository;

import com.project.assetManage.dto.ProductOptionDto;
import com.project.assetManage.entity.QProductOption;
import com.project.assetManage.util.expression.ProductOptionExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductOptionRepositoryImpl implements ProductOptionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    // 상품 옵션 목록 조회
    @Override
    public List<ProductOptionDto.ResponseAll> selectProductOptionList(ProductOptionDto.Request param) {
        System.out.println("selectProductOptionList ====================================");
        System.out.println(param.toString());

        QProductOption qProductOption = QProductOption.productOption;
        ProductOptionDto.ResponseSimple prdOption = new ProductOptionDto.ResponseSimple();

        // 특정 상품옵션순번 단건만 조회하는 경우
        // param :: fetchOne
        if(null != param.getFetchOne()){
            prdOption = this.selectProductOption(param);
            param.setPrdOptionSeq(prdOption.getPrdOptionSeq());
        }

        List<ProductOptionDto.ResponseAll> list = null;
        list = jpaQueryFactory.select(Projections.fields(ProductOptionDto.ResponseAll.class
                        , qProductOption.prdOptionSeq
                        , qProductOption.finCoNo
                        , qProductOption.finPrdtCd
                        , qProductOption.dclsMonth
                        , qProductOption.intrRateType
                        , qProductOption.intrRateTypeNm
                        , qProductOption.saveTrm
                        , qProductOption.intrRate
                        , qProductOption.intrRate2
                        , qProductOption.rsrvType
                        , qProductOption.rsrvTypeNm
                        // custom
                        , ProductOptionExpression.retMaturityAmt(qProductOption).as("maturityAmt")
                ))
                .from(qProductOption)
                .where(
                        ProductOptionExpression.eqPrdOptionSeq(qProductOption, prdOption.getPrdOptionSeq())
                        , ProductOptionExpression.eqFinCoNo(qProductOption, param.getFinCoNo())
                        , ProductOptionExpression.eqFinPrdtCd(qProductOption, param.getFinPrdtCd())
                        , ProductOptionExpression.eqDclsMonth(qProductOption, param.getDclsMonth())
                )
                .orderBy(qProductOption.prdOptionSeq.desc())
                .fetch();
        System.out.println("list ====================================");
        System.out.println(list.toString());
        return list;
    }

    // 소비유형코드에 해당하는 상품 옵션 순번 단건 조회
    @Override
    public ProductOptionDto.ResponseSimple selectProductOption(ProductOptionDto.Request param) {
        System.out.println("selectProductOption ====================================");
        System.out.println("param ====================================");
        System.out.println(param.toString());

        // 소비유형코드에 따라 save_trm max, min, avg 조회
        QProductOption qProductOption = QProductOption.productOption;
        ProductOptionDto.ResponseSimple ret = null;
        ret = jpaQueryFactory.select(Projections.fields(ProductOptionDto.ResponseSimple.class,
                        ProductOptionExpression.retPrdOptionSeq(qProductOption, param.getCnsmpInclnCd()).as("prdOptionSeq")
                        , qProductOption.finCoNo
                        , qProductOption.finPrdtCd
                        , qProductOption.dclsMonth))
                .from(qProductOption)
                .where(
                        ProductOptionExpression.eqFinCoNo(qProductOption, param.getFinCoNo())
                        , ProductOptionExpression.eqFinPrdtCd(qProductOption, param.getFinPrdtCd())
                        , ProductOptionExpression.eqDclsMonth(qProductOption, param.getDclsMonth())
                        , ProductOptionExpression.eqRsrvType(qProductOption, param.getRsrvType())
                )
                .fetchOne();

        System.out.println("ret ====================================");
        System.out.println(ret.toString());
        return ret;
    }

    // 상품별 소비유형코드에 해당하는 상품 옵션 순번 목록 조회
    @Override
    public List<ProductOptionDto.ResponseSimple> selectProductOptionListSub(ProductOptionDto.Request param) {
        System.out.println("selectProductOptionListSub ====================================");
        System.out.println("param ====================================");
        System.out.println(param.toString());

        // 소비유형코드에 따라 save_trm max, min, avg 조회
        QProductOption qProductOption = QProductOption.productOption;
        List<ProductOptionDto.ResponseSimple> list = null;
        list = jpaQueryFactory.select(Projections.fields(ProductOptionDto.ResponseSimple.class,
                        ProductOptionExpression.retPrdOptionSeq(qProductOption, param.getCnsmpInclnCd()).as("prdOptionSeq")
                        , qProductOption.finCoNo
                        , qProductOption.finPrdtCd
                        , qProductOption.dclsMonth))
                .from(qProductOption)
                .where(
                        ProductOptionExpression.eqFinCoNo(qProductOption, param.getFinCoNo())
                        , ProductOptionExpression.eqFinPrdtCd(qProductOption, param.getFinPrdtCd())
                        , ProductOptionExpression.eqDclsMonth(qProductOption, param.getDclsMonth())
                        , ProductOptionExpression.eqRsrvType(qProductOption, param.getRsrvType())
                )
                .groupBy(qProductOption.finCoNo
                        , qProductOption.finPrdtCd
                        , qProductOption.dclsMonth)
                .fetch();

        System.out.println("list ====================================");
        System.out.println(list.toString());
        return list;
    }


}
