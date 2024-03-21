import React, { useContext, useEffect } from 'react';
import "./BankDetail.css";
import ProductDetailContext, { useProductDetail } from './Context/ProductDetailContext'; // ProductDetailProvider에서 생성한 Context를 가져옵니다.
import { useLocation } from 'react-router-dom';

const BankDetail = () => {
    const { productData } = useContext(ProductDetailContext);
    const { getProduct } = useProductDetail();
    const location = useLocation();

    useEffect(() => {
        const { finCoNo, finPrdtCd, dclsMonth } = location.state;
        getProduct(finCoNo, finPrdtCd, dclsMonth);
    }, []);


    let bankLogo = '';
        const finCoNo = productData?.list[0]?.finCoNo;
        switch (finCoNo) {
            case '0010001':
                bankLogo = '/bank-icons/woori.png'
                break;
            case '0010002':
                bankLogo = '/bank-icons/sc.png'
                break;
            case '0010016':
                bankLogo = '/bank-icons/dgb.png'
                break;
            case '0010017':
                bankLogo = '/bank-icons/bnk.png'
                break;
            case '0010019':
                bankLogo = '/bank-icons/gwangju.png'
                break;
            case '0010020':
                bankLogo = '/bank-icons/jeju.png'
                break;
            case '0010022':
                bankLogo = '/bank-icons/jeonbuk.png'
                break;
            case '0010024':
                bankLogo = '/bank-icons/bnk-kyongnam.png'
                break;
            case '0010026':
                bankLogo = '/bank-icons/ibk.png'
                break;
            case '0010030':
                bankLogo = '/bank-icons/kdb.png'
                break;
            case '0010927':
                bankLogo = '/bank-icons/kb.png'
                break;
            case '0011625':
                bankLogo = '/bank-icons/shinhanico@2x.png'
                break;
            case '0013175':
                bankLogo = '/bank-icons/nh.png'
                break;
            case '0013909':
                bankLogo = '/bank-icons/keb.png'
                break;
            case '0014674':
                bankLogo = '/bank-icons/kbank.png'
                break;
            case '0014807':
                bankLogo = '/bank-icons/suhyup.png'
                break;
            case '0015130':
                bankLogo = '/bank-icons/kakaobank.png'
                break;
            case '0017801':
                bankLogo = '/bank-icons/toss.png'
                break;
            default:
                bankLogo = ''
        }

        //은행명
        const korCoNm = productData?.list[0]?.korCoNm;

        //상품이름
        const finPrdtNm = productData?.list[0]?.finPrdtNm;

        //가입기간
        const  maxRegiPeriod= productData?.list[0]?.productOptionList[0]?.saveTrm;
        const lastIndex = productData?.list[0]?.productOptionList.length - 1;
        const  minRegiPeriod= productData?.list[0]?.productOptionList[lastIndex]?.saveTrm;

        //가입금액 추출
        let etcNote = productData?.list[0]?.etc_note;

        function extractInfoFromEtcNote(etcNote) {

            let extractedInfo = "해당없음"; // 추출된 정보를 저장할 변수, 기본값은 "해당없음"

            if (etcNote) {
                // 정규식을 사용하여 "가입금액" 또는 "가입한도"와 관련된 부분을 추출
                const regex = /(가입금액|가입한도)\s?:\s?(.*)/gi;
                const match = regex.exec(etcNote);

                if (match && match.length > 2) {
                    extractedInfo = match[2].trim(); // 추출된 부분에서 공백을 제거한 후 반환
                }
            }
            return extractedInfo;
        }

        //가입금액
        let regiAmount = extractInfoFromEtcNote(etcNote);
        let regiAmount_num = "";

        console.log("추출된 정보:", regiAmount);

        if(regiAmount !== "해당없음"){
            // 숫자만 파싱해오기
            regiAmount_num = "";
        }

        //최고금리
        const highestInterestRate = productData?.list[0]?.productOptionList.reduce((maxInterestRate, option) => {
            return option.intrRate2 > maxInterestRate ? option.intrRate2 : maxInterestRate;
        }, 0);

    return (
        <div className="bg-box-parent">
            <div className="bg-box11">
                <div className="s-logo-wrapper">
                    <div className="s-logo">
                        <img className="shinhan-ico-icon3" alt="" src={bankLogo}/>
                    </div>
                    <div className="s-tit6">
                        <div className="div47">{korCoNm}</div>
                    </div>
                </div>
                <div className="parent2">
                    <b className="b23">{finPrdtNm}</b>
                    <div className="product_detail_section">
                        회전주기(매1년)마다 영업점 방문 없이 변동금리로 자동 회전되는 장기
                        목돈굴리기 상품
                    </div>
                </div>
                <div className="group-parent">
                    <div className="group-contents">
                        <div className="title_section">가입기간</div>
                        <div className="data_section">
                            <img className="group-img" alt="" src="/group-15.svg"/>
                            <div className="group_detail_section">
                                <b className="detail_number">{minRegiPeriod}~{maxRegiPeriod}</b>
                                <span className="end_section">개월</span>
                            </div>
                        </div>
                    </div>
                    <div className="group-contents">
                        <div className="title_section_second">가입금액</div>
                        <div className="data_section">
                            <img className="group-img" alt="" src="/group-14.svg"/>
                            <div className="group_detail_section">
                                {regiAmount !== '해당없음' ? (
                                    <b className="amount_section1">{regiAmount_num}</b>
                                ) : (
                                    <div className="amount_section2">{regiAmount}</div>
                                )}
                            </div>
                        </div>
                    </div>
                    <div className="group-contents">
                        <div className="title_section_last">최고금리</div>
                        <div className="data_section">
                            <img className="group-img" alt="" src="/group-8.svg"/>
                            <div className="group_detail_section">
                                <b className="detail_number">연 {highestInterestRate}%</b>
                                <div className="end_section">(세전)</div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    );
};

export default BankDetail;
