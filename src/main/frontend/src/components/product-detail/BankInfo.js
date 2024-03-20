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
            <div className="div29">상품안내</div>
            <div className="bg-box6"/>
            <div className="product-detail">
                <div className="product-info">
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
            </div>
        </div>
    );
};

export default BankInfo;
