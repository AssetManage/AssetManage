import axios from 'axios';
import Slider from 'react-slick';

import { useEffect, useState, useRef } from 'react'
import { useNavigate, useLocation } from 'react-router-dom';

// import styles from "./Main2.module.css";
import styles from "./Main.module.css";
// slick css custom 을 위해 별도 파일로 작업
import "../main/slick/slick.css";
import "../main/slick/slick-theme.css";

import Header from '../common/header/Header';

// TO-DO :: 팝업 호출
// import ProductRecmdPopup from '../product-recmd-popup/ProductRecmdPopup';
// import ProductRecmdPopup2 from '../product-recmd-popup/ProductRecmdPopup2';

const Main = ({ className, ...props }) => {
    const navigate = useNavigate();

    // 0. 로그인 여부 체크
    const isin = localStorage.getItem('accessToken') == null ? false : true;
    // tmp
    // const isin = false;

    // slick 슬라이드
    const settings = {
        dots: !isin,
        infinite: !isin,
        speed: 500,
        slidesToShow: 3,
        slidesToScroll: 3,
        arrows: !isin,
        autoplay: !isin,
        autoplaySpeed: 5000,
        prevArrow: "", // .lArrowIcon
    };

    // variables
    const [cnsmpInclnCdInfo, setCnsmpInclnCdInfo] = useState({});

    const [param1, setParam1] = useState({});
    const [param2, setParam2] = useState({});

    const [comboList1, setComboList1] = useState([]);
    const [comboList2, setComboList2] = useState([]);

    const [productList1, setProductList1] = useState([]);
    const [productList2, setProductList2] = useState([]);

    // function
    // TO-DO :: useState 기본값 설정 후 바로 사용할 수 있는 방법 찾기
    const init = () => {
        if(isin){
            axios.get('/user/selectUserSimple', {
                headers:{
                    Authorization : localStorage.getItem('accessToken') // 로그인 사용자 token
                }
            })
                .then(res => {
                    const info = res.data.data;
                    const def = {'key':'prdtRcmdItemCd', 'prdtRcmdItemCd':info.prdtRcmdItemCd, 'ageCd':info.ageCd, 'incomeScopeCd': info.incomeScopeCd, 'limit':3, 'cnsmpInclnCd':info.cnsmpInclnCd};
                    const def1 = {...def, 'actKindCd':'DP'};
                    const def2 = {...def, 'actKindCd':'SV'};

                    setParam1(def1);
                    setParam2(def2);
                    getComboList('prdt_rcmd_item_cd', 1);
                    getComboList('prdt_rcmd_item_cd', 2);
                    getProductList(1, def1);
                    getProductList(2, def2);
                    getCnsmpInclnCd("cnsmp_incln_cd", info.cnsmpInclnCd, info.cnsmpInclnNm);

                })
                .catch(err => {
                    // TO-DO :: 토큰은 존재하나 백단에서 user 정보가 null로 조회되는 경우, 에러 처리 추가
                    console.log(err);
                });
        }else{
            const def1 = {'key':'actKindCd', 'actKindCd':'DP'};
            const def2 = {'key':'ageCd', 'prdtRcmdItemCd':'AG', 'ageCd':'20'};

            setParam1(def1);
            setParam2(def2);
            getComboList('act_kind_cd', 1);
            getComboList('age_cd', 2);
            getProductList(1, def1);
            getProductList(2, def2);
        }
    }

    // axios
    const getCodeList = (grpCodeId, codeId) => {
        return axios.get('/st/code/selectCodeList', {
            params : {
                'groupCode' : grpCodeId,
                'codeId' : codeId,
                'useYn' : 'Y'}
        })
    }

    const getComboList = (grpCodeId, div) => {
        getCodeList(grpCodeId)
            .then(res => {
                const list = res.data.list;
                if(div == 1){
                    setComboList1(list);
                }else{
                    setComboList2(list);
                }
            }).catch(err => {
            console.log(err);
        });
    }
    const getProductList = (area, params) => {

        // console.log('params :: ', params);

        axios.get('/st/product/selectProductListWithOpt', {
            params : params
        })
            .then(res => {
                const list = res.data.list;
                if(area == 1){
                    setProductList1(list);
                }else{
                    setProductList2(list);
                }
            })
            .catch(err => {
                console.log(err);
            });
    }

    const getCnsmpInclnCd = (grpCodeId, codeId, codeNm) => {
        getCodeList(grpCodeId, codeId)
            .then(res => {
                const info = res.data.list[0].info;
                setCnsmpInclnCdInfo({...info, cnsmpInclnNm:codeNm});
            })
            .catch(err => {
                console.log(err);
            });
    }

    function getColor(){
        return "bgrColor"+(Math.floor(Math.random() * 5)+1);
    }

    // event
    const changeCombo = (e, div) => {
        let param;
        if(div == 1){
            param = {...param1};
            param[param.key] = e.target.value;
            setParam1((prev) => {
                return param;
            });
        }else{
            param = {...param2};
            param[param.key] = e.target.value;
            setParam2((prev) => {
                return param;
            });
        }
        getProductList(div, param);
    };

    const clickProduct = (idx, div) => {
        let product;
        if(div == 1){
            product = productList1[idx];
        }else{
            product = productList2[idx];
        }
        // console.log('product :: ', product);

        navigate('/product-detail', {
            state: {
                'finCoNo' : product.finCoNo,
                'finPrdtCd' : product.finPrdtCd,
                'dclsMonth' : product.dclsMonth,
            }
        });
    }

    const clickMoreView = (div) => {
        let param;
        if(div == 1){
            param = param1;
        }else{
            param = param2;
        }
        navigate('/product-list', {
            state: param
        });
    }

    // init
    useEffect(() => {
        init();
    }, []);

    /*
    * 메인(MFDB001-M001) 4번 :: MFDB001-M001-4
    *
    * 0. 로그인 여부 체크
    *
    * 1. 비회원
    * 1-1. 대표 캐릭터 display 및 비회원의 회원가입 유도를 위한 나에게 맞는 예적금 추천받기! 팝업 호출 배너
    * 1-2. 구현 컴포넌트 :: button(나에게 맞는 예적금 추천받기! 팝업 호출 event)
    *
    * 2. 회원
    * 2-1. 로그인 회원의 소비성향코드에 해당하는 소비유형정보 display
    * 2-2. 구현 컴포넌트 ::
    * */

    /*
    * TO-DO :: MFDB001-M001-5는 actKindCd(상품분류코드) 파라미터만 상이하고 5-1, 5-2 컴포넌트 생성 프로세스가 동일함
    * a. 공통코드 중 groupCode가 act_kind_cd에 해당하는 목록 조회
    * b. act_kind_cd에 해당하는 목록 size 만큼 컴포넌트 생성 프로세스 사용자 함수 호출
    *
    * 사전 작업 :: 5-1 5-2 영역 div css 생성 후 시스템 스크롤로 제어 가능하게 사용
    * */
    /*
    * 메인(MFDB001-M001) 5번 :: MFDB001-M001-5-1
    *
    * 0. 로그인 여부 체크
    *
    * 1. 비회원
    * 1-1. 전체 예적금 상품 조회
    * 1-2. 소비성향코드 기준으로 조회하여 기간이 가장 길고 금리가 가장 높은 예적금 상품 정보 조회
    * 1-3. ProductOptionDto.Request param :: cnsmpInclnCd(소비성향코드), actKindCd(상품분류코드)
    * 1-4. default param :: cnsmpInclnCd(AT(개미형)), actKindCd(DP(예금))
    * 1-5. 구현 컴포넌트 :: 슬라이드(slick 라이브러리), combo(상품분류코드)
    *
    * 2. 회원
    * 2-1. 로그인 회원의 소비성향코드에 해당하는 예금 상품 top3 조회
    * 2-2. ProductOptionDto.Request param :: cnsmpInclnCd(소비성향코드), prdtRcmdItemCd(상품추천항목코드), limit(조회 갯수 제한)
    * 2-3. default param :: cnsmpInclnCd(로그인 회원의 소비성향코드), actKindCd(AP(예금)), prdtRcmdItemCd(로그인 회원이 회원가입시 입력한 항목), limit(3)
    * 2-4. 구현 컴포넌트 :: combo(상품추천항목코드)
    * */

    // 로그인 사용자 정보 조회
    // TO-DO :: 현재 토큰값으로는 로그인 사용자가 누구인지 판단 x, 로그인 후 후처리 사용자 식별 가능한 필드 및 토큰값 별도 처리 필요

    /*
    * 메인(MFDB001-M001) 5번 :: MFDB001-M001-5-2
    *
    * 0. 로그인 여부 체크
    *
    * 1. 비회원
    * 1-1. 연령대별 예/적금 HIT 상품 목록 조회
    * 1-2. 내부 회원 기준 연령대별 보유한 상품이 많은 순으로 상품 목록 조회
    * 1-3. ProductOptionDto.Request param :: ageCd(연령대)
    * 1-4. default param :: ageCd(20) 임시, 이유는 없음 걍 젊고 싶음
    * 1-5. 구현 컴포넌트 :: 슬라이드(slick 라이브러리), combo(연령대)
    *
    * 2. 회원
    * 2-1. 로그인 회원의 소비성향코드에 해당하는 적금 상품 top3 조회
    * 2-2. ProductOptionDto.Request param :: cnsmpInclnCd(소비성향코드), prdtRcmdItemCd(상품추천항목코드), limit(조회 갯수 제한)
    * 2-3. default param :: cnsmpInclnCd(로그인 회원의 소비성향코드), actKindCd(SV(적금)), prdtRcmdItemCd(로그인 회원이 회원가입시 입력한 항목), limit(3)
    * 2-4. 구현 컴포넌트 :: combo(상품추천항목코드)
    * */

    return (
        <div className={styles.main11920 + " " + className}>

            <div className={styles.frame49}>
                {/* header*/}
                <div className="header-wrapper">
                    <Header/>
                </div>
                <div className={styles.frame48}>

                    {/* banner */}
                    <Banner/>

                    {/*  TO-DO :: dataset 제외하고 area1, area2 호출 방법 동일하므로 합칠 수 있는 방법 찾기 */}
                    {/* productList */}
                    <div className={styles.productArea}>
                        <ProductTitle1/>
                        <ProductList1/>
                        {/*<img className={styles.lArrow} src="/larrow.svg"/>*/}
                    </div>

                    <div className={styles.productArea}>
                        <ProductTitle2/>
                        <ProductList2/>
                        {/*<img className={styles.lArrow} src="/larrow.svg"/>*/}
                    </div>
                </div>
            </div>
        </div>

        //     {/* 예적금 추천 상품 팝업(입력) */}
        //     {/*
        //         <ProductRecmdPopup />
        //         */}
        //     {/* 예적금 추천 상품 팝업(결과) */}
        //     {/*
        //         <ProductRecmdPopup2 />
        //         */}
    );

    // component
    function Banner() {
        if (isin) {
            return <>
                <div className={styles.bannerArea}>
                    <img className={styles.img} src={cnsmpInclnCdInfo.cnsmpInclnImgUrl}/>
                    <div className={`${styles.bannerContents}`}>
                        <div
                            className={`${styles.bannerTitle} ${styles.font48} ${styles.colorBlack} ${styles.bold700}`}>
                            나의 소비 유형은?
                        </div>
                        <span className={`${styles.font48} ${styles.colorBlue} ${styles.bold600}`}>{cnsmpInclnCdInfo.cnsmpInclnNm}</span>
                        <span className={`${styles.font18} ${styles.bold600}`} >{cnsmpInclnCdInfo.cnsmpInclnTitle}</span>
                        <span className={`${styles.font18} ${styles.bold400}`} >{cnsmpInclnCdInfo.cnsmpInclnContents}</span>
                    </div>
                </div>
            </>
        } else {
            return <>
                <div className={styles.bannerArea}>
                    <img className={styles.img} src="/group-570.svg"/>
                    <div className={`${styles.bannerContents}`}>
                        <div
                            className={`${styles.bannerTitle} ${styles.font48} ${styles.colorBlack} ${styles.bold700}`}>My
                            Financial Pocket
                        </div>
                        <span className={`${styles.font28} ${styles.colorBlack} ${styles.bold500}`}>
                              나의 아름다운 인생을 위한
                            </span>
                        <br/>
                        <span className={`${styles.font48} ${styles.colorBlue} ${styles.bold600}`}>맞춤 자산 보장! </span>
                        <span className={`${styles.font28} ${styles.colorBlack} ${styles.bold500}`}>
                                  무엇이 좋을까?
                                  <br/>
                        </span>
                    </div>
                    <div className={`${styles.bannerBtnArea}`}>
                        <button className={`${styles.btnNavy} ${styles.bold700}`}>
                            나에게 맞는 예·적금 추천받기
                        </button>
                    </div>
                </div>
            </>
        }
    }

    function ProductTitle1() {
        if (isin) {
            return <>
                <div className={styles.title}>
                            <span className={`${styles.font28} ${styles.colorBlack} ${styles.bold600}`}>
                                나의
                                <div className={styles.marginM5}></div>
                                추천 예금 <p style={{'display':'inline-block', 'margin':'3px'}} className={`${styles.colorBlue} ${styles.bold700}`}>TOP3</p>
                            </span>
                    <br/>
                    <select className={styles.seltBox} onChange={(e) => changeCombo(e, 1)} value={param1[param1.key]}>
                        {comboList1.map((e) => {
                            return (
                                <option key={e.groupCode + "_" + e.codeId} value={e.codeId}>{e.codeNm}</option>
                            );
                        })
                        }
                    </select>
                    <div className={styles.moreView} onClick={(e) => clickMoreView(1)}>
                        <span className={`${styles.font16} ${styles.colorBlue}`} >추천 상품 전체 보기 -></span>
                    </div>
                </div>
            </>
        } else {
            return <>
                <div className={styles.title}>
                            <span className={`${styles.font28} ${styles.colorBlack} ${styles.bold600}`}>
                                전체 <br/>
                                예·적금 상품
                            </span>
                    <br/>
                    <select className={styles.seltBox} onChange={(e) => changeCombo(e, 1)} value={param1[param1.key]}>
                        {comboList1.map((e) => {
                            return (
                                <option key={e.groupCode + "_" + e.codeId} value={e.codeId}>{e.codeNm}</option>
                            );
                        })
                        }
                    </select>
                    <div className={styles.moreView} onClick={(e) => clickMoreView(1)}>
                        <span className={`${styles.font16} ${styles.colorBlue}`}>모든 상품 전체 보기 -></span>
                    </div>
                </div>
            </>
        }
    }

    function ProductList1() {
        return <div className={styles.productList}><Slider {...settings} >
            {
                productList1.map((e, idx) => {
                    return (
                        <div className={`${styles.product} ${styles[getColor()]}`} onClick={(e) => clickProduct(idx, 1)}>
                            <div className={styles.tit}>
                                <img className={styles.ico} src={e.finCoNoImgUrl}/>
                                <div className={styles.nm}>
                                    <span className={`${styles.font14} ${styles.bold500}`}>{e.korCoNm}</span>
                                    <span className={`${styles.font18} ${styles.bold600}`}>{e.finPrdtNm}</span>
                                </div>
                            </div>
                            <div className={styles.cnts}>
                                <div className={styles.intrRate}>
                                    <div
                                        className={`${styles.tit} ${styles.font16} ${styles.colorOrange} ${styles.bold700}`}>최고금리
                                    </div>
                                    <div className={styles.cnts}>
                                            <span
                                                className={`${styles.font36} ${styles.colorNavy} ${styles.bold700}`}>{e.intrRate2}</span>
                                        <span
                                            className={`${styles.font20} ${styles.colorNavy} ${styles.bold700}`}>%</span>
                                        <span className={`${styles.font16}`}>(세전)</span>
                                    </div>
                                </div>
                                <div className={styles.trms}>
                                    <span className={`${styles.font14}`}>({e.saveTrm}개월 / 온라인가입 기준)</span>
                                </div>
                            </div>
                        </div>
                    );
                })
            }
        </Slider></div>
    }

    function ProductTitle2() {
        if (isin) {
            return <>
                <div className={styles.title}>
                            <span className={`${styles.font28} ${styles.colorBlack} ${styles.bold600}`}>
                                나의
                                <div className={styles.marginM5}></div>
                                추천 적금 <p style={{'display': 'inline-block', 'margin': '3px'}}
                                         className={`${styles.colorBlue} ${styles.bold700}`}>TOP3</p>
                            </span>
                    <br/>
                    <select className={styles.seltBox} onChange={(e) => changeCombo(e, 2)} value={param2[param2.key]}>
                            {comboList2.map((e) => {
                                return (
                                    <option key={e.groupCode + "_" + e.codeId} value={e.codeId}>{e.codeNm}</option>
                                );
                            })
                        }
                    </select>
                    <div className={styles.moreView} onClick={(e) => clickMoreView(2)}>
                        <span className={`${styles.font16} ${styles.colorBlue}`}>추천 상품 전체 보기 -></span>
                    </div>
                </div>
            </>
        } else {
            return <>
                <div className={styles.title}>
                            <span className={`${styles.font28} ${styles.colorBlack} ${styles.bold600}`}>
                                연령대별 <br/>
                                예·적금
                                <p style={{'display': 'inline-block', 'margin':'3px'}} className={`${styles.colorBlue}`}>HIT</p>
                                상품
                            </span>
                    <br/>
                    <select className={styles.seltBox} onChange={(e) => changeCombo(e, 2)} value={param2[param2.key]}>
                        {comboList2.map((e) => {
                                return (
                                    <option key={e.groupCode + "_" + e.codeId} value={e.codeId}>{e.codeNm}</option>
                                );
                            })
                        }
                    </select>
                    <div className={styles.moreView} onClick={(e) => clickMoreView(2)}>
                        <span className={`${styles.font16} ${styles.colorBlue}`}>인기 상품 전체 보기 -></span>
                    </div>
                </div>
            </>
        }
    }

    function ProductList2() {
        return <div className={styles.productList}><Slider {...settings} >
            {
                productList2.map((e, idx) => {
                    return (
                        <div className={`${styles.product} ${styles[getColor()]}`} onClick={(e) => clickProduct(idx, 2)}>
                            <div className={styles.tit}>
                                <img className={styles.ico} src={e.finCoNoImgUrl}/>
                                <div className={styles.nm}>
                                    <span className={`${styles.font14} ${styles.bold500}`}>{e.korCoNm}</span>
                                    <span className={`${styles.font18} ${styles.bold600}`}>{e.finPrdtNm}</span>
                                </div>
                            </div>
                            <div className={styles.cnts}>
                                <div className={styles.intrRate}>
                                    <div
                                        className={`${styles.tit} ${styles.font16} ${styles.colorOrange} ${styles.bold700}`}>최고금리
                                    </div>
                                    <div className={styles.cnts}>
                                            <span
                                                className={`${styles.font36} ${styles.colorNavy} ${styles.bold700}`}>{e.intrRate2}</span>
                                        <span
                                            className={`${styles.font20} ${styles.colorNavy} ${styles.bold700}`}>%</span>
                                        <span className={`${styles.font16}`}>(세전)</span>
                                    </div>
                                </div>
                                <div className={styles.trms}>
                                    <span className={`${styles.font14}`}>({e.saveTrm} 개월 / 온라인가입 기준)</span>
                                </div>
                            </div>
                        </div>
                    );
                })
            }
        </Slider></div>
    }
};


export default Main;
