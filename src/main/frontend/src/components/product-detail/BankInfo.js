import "./BankInfo.css";
import React, { useContext, useEffect } from "react";
import { useTable } from "react-table";
import ProductDetailContext, { useProductDetail } from "./Context/ProductDetailContext";

const BankInfo = () => {
    // ProductDetailContext에서 productData를 가져옵니다.
    const { productData } = useContext(ProductDetailContext);

    const { getProduct } = useProductDetail();

    useEffect(() => {
        getProduct('0014807', '10141114300011', '202402');
    }, []);


    // react-table을 위한 컬럼 정의
    const columns = React.useMemo(
        () => [
            {
                Header: '항목',
                accessor: 'Item',
            },
            {
                Header: '내용',
                accessor: 'Content',
            },
        ],
        []
    );

    // react-table 훅을 사용하여 테이블 데이터 생성
    const { getTableProps, getTableBodyProps, rows, prepareRow } = useTable({
        columns,
        data: productData ? [
            { Item: '상품명', Content: productData.list[0]?.finPrdtNm },
            { Item: '가입대상', Content: productData.list[0]?.joinMember },
            { Item: '가입기간', Content: `${productData.list[0]?.productOptionList[0]?.saveTrm}개월 ~ ${productData.list[0]?.productOptionList[productData.list[0]?.productOptionList.length - 1]?.saveTrm}개월` },
            { Item: '가입금액', Content: productData.list[0]?.etcNote },
            { Item: '이자 지급 시기', Content: productData.list[0]?.mtrtInt },
            { Item: '비과세 종합저축', Content: productData.list[0]?.actKindCd },
        ] : [],
    });

    return (
        <div className="div28">
            <div className="tab_title">
                <div className="div29">상품안내</div>
                <div className="bg-box6"/>
            </div>
            <div className="product-info" id="product-info">
                <table {...getTableProps()} className="react-table">
                    <tbody {...getTableBodyProps()}>
                    {rows.map(row => {
                        prepareRow(row);
                        return (
                            <tr {...row.getRowProps()}>
                                {row.cells.map(cell => (
                                    <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                                ))}
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </div>
            <div className="tab_title">
                <div className="div29">금리안내</div>
                <div className="bg-box6"/>
            </div>
            <div className="product-info" id="react-table2">
                <table {...getTableProps()} className="react-table2" >
                    <thead>
                    <tr>
                        <th>기간</th>
                        <th>금리(연, 세전)</th>
                        <th>복리수익률(연, 세전)</th>
                    </tr>
                    </thead>
                    <tbody>
                    {productData && productData.list[0]?.productOptionList.slice().reverse().map(option => (
                        <tr key={option.optionId}>
                            <td>{option.saveTrm}개월</td>
                            <td>{option.intrRate}%</td>
                            <td>{option.intrRate2}%</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>

            <div className="tab_title">
                <div className="div29">유의사항</div>
                <div className="bg-box6"/>
            </div>
            <div className="product-info" id="notice-detail">
                <b className= "notice-title">※ 계약 체결 전 상품설명서 및 약관을 확인하시기 바랍니다.</b>
                <div className="notice-detail"></div>
            </div>
            <div className="product-info-footer">
                <div className="logo-img">
                    <img className="logo" alt="" src="/depositor-protection.png"/>
                </div>
                <div className="depositor-protection">
                    <b className="semi-title">예금자 보호 안내</b>
                    <br/>
                    <span className="protect-detail">이 예금은 예금자보호법에 따라 예금보험공사가 보호하되, 보호 한도는 본 저축은행에 있는 귀하의 모든 예금보호 대상 금융상품의
원금과 소정의 이자를 합하여 1인당 “최고 5천만원”이며, 5천만원을초과하는 나머지 금액은 보호하지 않습니다.</span>
                </div>
            </div>
        </div>
    );
};

export default BankInfo;
