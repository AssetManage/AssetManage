import axios from 'axios';
import Slider from 'react-slick';

import { useEffect, useState, useRef } from 'react'
import { useNavigate } from 'react-router-dom';

// import styles from "./Main2.module.css";
import styles from "./Main.module.css";
// slick css custom 을 위해 별도 파일로 작업
import "../main/slick/slick.css";
import "../main/slick/slick-theme.css";


import Header from '../common/header/Header';
// TO-DO :: 팝업 호출
// import ProductRecmdPopup from '../product-recmd-popup/ProductRecmdPopup';
// import ProductRecmdPopup2 from '../product-recmd-popup/ProductRecmdPopup2';

const Main = () => {
    const navigate = useNavigate();

    // 0. 로그인 여부 체크
    // const isin = localStorage.getItem('accessToken') == null ? false : true;
    // tmp
    const isin = false;

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
        // prevArrow: "", // .lArrowIcon
    };

    // variables
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
            axios.get('/st/user/selectUserSimple', {
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
                })
                .catch(err => {
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
    const getCodeList = (grpCodeId) => {
        return axios.get('/st/code/selectCodeList', {
            params : {
                'groupCode': grpCodeId,
                'useYn' : 'Y'}
        })
    }

    const getComboList = (grpCodeId, div) => {
        getCodeList(grpCodeId).then(res => {
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

        console.log('params :: ', params);

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

    // event
    const changeCombo = (e, div) => {
        let param;
        if(div == 1){
            param = {...param1};
            setParam1((prev) => {
                param[param.key] = e.target.value;
                return param;
            });
        }else{
            param = {...param2};
            setParam2((prev) => {
                param[param.key] = e.target.value;
                return param;
            });
        }
        getProductList(div, param);
    };

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
        <div className={styles.main1}>
            <div className={styles.main}>
                <Header/>
                <div className={styles.main1Child}/>
                <div className={styles.myFinancialPocketParent}>
                    <b className={styles.myFinancialPocket}>My Financial Pocket</b>
                    <div className={styles.div}>
                        <p className={styles.p}>나의 아름다운 인생을 위한</p>
                        <p className={styles.p1}>
                            <span className={styles.span}>{`맞춤 자산 보장! `}</span>
                            <span className={styles.span1}>
                                <span className={styles.span2}>무엇이 좋을까?</span>
                            </span>
                        </p>
                        <p className={styles.blankLine}>
                            <span className={styles.span1}>
                                <span>&nbsp;</span>
                            </span>
                        </p>
                    </div>
                    <div className={styles.btn}>
                        <div className={styles.btnBox}/>
                        <b className={styles.b}>나에게 맞는 예·적금 추천받기</b>
                    </div>
                    <img className={styles.icon} alt="" src="/group-57.png"/>
                </div>
                <ProductTitle1/>
                <select className={styles.seltBox} onChange={(e) => changeCombo(e, 1)} value={param1[param1.key]}>
                    {comboList1.map((e) => {
                        return (
                            <option key={e.groupCode + "_" + e.codeId} value={e.codeId}>{e.codeNm}</option>
                        );
                    })
                    }
                </select>

                <Slider {...settings} >
                    {
                        productList1.map((e, idx) => {
                            return (
                                <div className={styles.bgBox}>
                                    <img className={styles.shinhanIcoIcon} alt="" src={e.finCoNoImgUrl}/>
                                    <div className={styles.sTit}>
                                        <div className={styles.db}>{e.korCoNm}</div>
                                        <div className={styles.m}>{e.finPrdtNm}</div>
                                    </div>
                                    <b className={styles.b1}>최고금리</b>
                                    <div className={styles.div6}>
                                        <span className={styles.txt}>
                                            <span className={styles.span3}>{` `}</span>
                                            <span className={styles.span4}>
                                                <span className={styles.span5}>
                                                    <b className={styles.b2}>{e.intrRate2}</b>
                                                    <span className={styles.span6}>%</span>
                                                </span>
                                                <span className={styles.span6}>
                                                    <span className={styles.span8}>{` `}</span>
                                                </span>
                                                <span>(세전)</span>
                                            </span>
                                        </span>
                                    </div>
                                    <div className={styles.div7}>({e.saveTrm}개월 / 온라인가입 기준)</div>
                                </div>
                            );
                        })
                    }
                </Slider>

                <img className={styles.lArrowIcon} alt="" src="/larrow.svg"/>

                <select className={styles.seltBox1} onChange={(e) => changeCombo(e, 2)} value={param2[param2.key]}>
                    {comboList2.map((e) => {
                        return (
                            <option key={e.groupCode + "_" + e.codeId} value={e.codeId}>{e.codeNm}</option>
                        );
                    })
                    }
                </select>

                <ProductTitle2 />
                <Slider {...settings}>
                    {
                        productList2.map((e, idx) => {
                            return (
                                // <div className={styles.bgBox3}>
                                <div>
                                    <img className={styles.shinhanIcoIcon} alt="" src={e.finCoNoImgUrl}/>
                                    <div className={styles.sTit3}>
                                        <div className={styles.db}>{e.korCoNm}</div>
                                        <div className={styles.m}>{e.finPrdtNm}</div>
                                    </div>
                                    <b className={styles.b1}>최고금리</b>
                                    <div className={styles.div17}>
                                        <span className={styles.txt}>
                                            <span className={styles.span3}>{` `}</span>
                                            <span className={styles.span4}>
                                                <span className={styles.span5}>
                                                    <b className={styles.b2}>{e.intrRate2}</b>
                                                    <span className={styles.span6}>%</span>
                                                </span>
                                                <span className={styles.span6}>
                                                    <span className={styles.span8}>{` `}</span>
                                                </span>
                                                <span>(세전)</span>
                                            </span>
                                        </span>
                                    </div>
                                    <div className={styles.div7}>({e.saveTrm}개월 / 온라인가입 기준)</div>
                                </div>
                            );
                        })
                    }
                </Slider>
                <img className={styles.lArrowIcon1} alt="" src="/larrow.svg"/>
            </div>

            {/* 예적금 추천 상품 팝업(입력) */}
            {/*
            <ProductRecmdPopup />
            */}
            {/* 예적금 추천 상품 팝업(결과) */}
            {/*
            <ProductRecmdPopup2 />
            */}
        </div>


    );

    function ProductTitle1() {
        if(isin) {
            return <>
                <div className={styles.top3}>
                    <p className={styles.p2}>나의</p>
                    <p className={styles.p}>
                        <span className={styles.span}>{`추천 예금 `}</span>
                        <b className={styles.top32}>TOP3</b>
                    </p>
                </div>
                <div className={styles.moreView}>
                    <div className={styles.div5}>추천 상품 전체 보기</div>
                    <img className={styles.sArrIcoIcon} alt="" src="/sarrico.svg"/>
                    <div className={styles.wLine}/>
                </div>
            </>
        } else {
            return <>
                <div className={styles.div1}>
                            <span className={styles.txt}>
                                <p className={styles.p2}>{`전체 `}</p>
                                <p className={styles.p2}>예·적금 상품</p>
                            </span>
                </div>
                <div className={styles.moreView} onClick={(e) => {
                    navigate('/product-detail');
                }}>
                    <div className={styles.div3}>모든 상품 전체 보기</div>
                    <img className={styles.sArrIcoIcon} alt="" src="/sarrico.svg"/>
                    <div className={styles.wLine}/>
                </div>
            </>
        }
    }

    function ProductTitle2() {
        if (isin) {
            return <>
                <div className={styles.top33}>
                        <span className={styles.txt}>
                            <p className={styles.p2}>나의</p>
                            <p className={styles.p}>
                                <span className={styles.span}>{`추천 적금 `}</span>
                                <b className={styles.top32}>TOP3</b>
                            </p>
                        </span>
                    <div className={styles.moreView1}>
                        <div className={styles.div5}>추천 상품 전체 보기</div>
                        <img className={styles.sArrIcoIcon} alt="" src="/sarrico.svg"/>
                        <div className={styles.wLine}/>
                    </div>
                </div>
            </>
        } else {
            return <>
                <div className={styles.hit}>
                            <span className={styles.txt}>
                                <p className={styles.p4}>연령대별</p>
                                <p className={styles.p2}>
                                    <span className={styles.span}>{`예·적금 `}</span>
                                    <b className={styles.hit2}>
                                        <span className={styles.hit3}>HIT</span>
                                        <span className={styles.span21}>{` `}</span>
                                    </b>
                                    <span className={styles.span21}>
                                        <span className={styles.span23}>상품</span>
                                    </span>
                                </p>
                            </span>
                </div>
                <div className={styles.moreView1}>
                    <div className={styles.div3}>인기 상품 전체 보기</div>
                    <img className={styles.sArrIcoIcon} alt="" src="/sarrico.svg"/>
                    <div className={styles.wLine}/>
                </div>
            </>
        }
    }


};


export default Main;
