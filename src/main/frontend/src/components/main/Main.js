import axios from 'axios';
import Slider from 'react-slick';

import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';


import "../main/slick/slick.css";
import "../main/slick/slick-theme.css";
import styles from "./Main.module.css";


import Header from '../common/header/Header';
// import ProductRecmdPopup from '../product-recmd-popup/ProductRecmdPopup';
// import ProductRecmdPopup2 from '../product-recmd-popup/ProductRecmdPopup2';
// import SelectBox from "../sign-up/component/SelectBox";

const Main = () => {
    const navigate = useNavigate();
    // 0. 로그인 여부 체크
    const token = localStorage.getItem('accessToken');
    console.log('token :: ', token);
    /*
    * Storage {accessToken: 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MkBlbWFpbC5jb…TUzfQ.Qqq3R9sY7n4VA4vj92idk5c33PHHKRK7wlmBENuuuKU', length: 1}
accessToken
:
"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MkBlbWFpbC5jb20iLCJyb2xlcyI6WyJVU0VSIl0sImlhdCI6MTcwOTYxODM1MywiZXhwIjoxNzA5NjIwMTUzfQ.Qqq3R9sY7n4VA4vj92idk5c33PHHKRK7wlmBENuuuKU"
length
:
1
    * */
    console.log('localStorage :: ', localStorage);
    // 비회원
    const [actKindCdList, setActKindCdList] = useState([]); // 상품분류코드(act_kind_cd)
    const [ageCdList, setAgeCdList] = useState([]); // 연령대(age_cd)
    // 회원
    const [prdtRcmdItemCdList, setPrdtRcmdItemCdList] = useState([]); // 상품추천항목코드(prdt_rcmd_item_cd)

    const [productList, setProductList] = useState([]);

    // 객체 stat
    const [cnsmpInclnCd, setCnsmpInclnCd] = useState("AT");
    let [actKindCd, setActKindCd] = useState("DP");
    const [ageCd, setAgeCd] = useState(20);
    const [prdtRcmdItemCd, setPrdtRcmdItemCd] = useState("");

    // slick 슬라이드
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 3,
        slidesToScroll: 3,
        arrows: true,
        autoplay: true,
        autoplaySpeed: 5000,
    };

    const handleSelect = (e) => {
        actKindCd = e.target.value;
        // TO-DO :: 왜 해당 함수 호출하면 이전값으로 세팅되는지 원인 파악
        // setActKindCd(e.target.value);
        // console.log("actKindCd :: ", actKindCd);
        getProductList();
    };

    function getCodeList(grpCodeId){
        return axios.get('/st/code/selectCodeList', {
            params : {
                'groupCode': grpCodeId,
                'useYn' : 'Y'}
        })
    }

    function getProductList(){
        axios.get('/st/product/selectProductListWithOpt', {
            params : {
                'cnsmpInclnCd': cnsmpInclnCd,
                'actKindCd' : actKindCd,
                // 'limit': 3
            }
        })
            .then(res => {
                // console.log("selectProductListWithOpt :: ", res.data.list);
                setProductList(res.data.list);
            })
            .catch(err => {
                console.log(err);
            });
    }

    useEffect(() => {
        async function getActKindCdList(grpCodeId) {

            const response = getCodeList(grpCodeId).then(res => {
                const list = res.data.list;
                // 받아온 데이터를 map 해주어 rowData 별로 optionList 선언
                // list.map( (data, index) => {
                //      let ret = [];
                //     ret.push(
                //         <option key={grpCodeId+"_"+data.codeId} value={data.codeId}>{data.codeNm}</option>
                //     )
                // })
                // list.map( (data, index) => {
                //     ret.push(
                //         {
                //             name : data.codeNm
                //             , value : data.codeId
                //         }
                //     )
                // })
                setActKindCdList(list);
            })
            .catch(err => {
                    console.log(err);
            });
        }
        getActKindCdList("act_kind_cd");
    }, []);
    useEffect(() => {
        async function getAgeCdList(grpCodeId) {

            const response = getCodeList(grpCodeId).then(res => {
                const list = res.data.list;
                setAgeCdList(list);
            })
            .catch(err => {
                console.log(err);
            });
        }
        getAgeCdList("age_cd");
    }, []);

    useEffect(() => {
        async function getPrdtRcmdItemCdList(grpCodeId) {

            const response = getCodeList(grpCodeId).then(res => {
                const list = res.data.list;
                setPrdtRcmdItemCdList(list);
            })
                .catch(err => {
                    console.log(err);
                });
        }
        getPrdtRcmdItemCdList("prdt_rcmd_item_cd");
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
    useEffect(() => {
        getProductList();
    }, []);

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

    function ChangeValue(e){
        console.log(e.target.value)
        /*
        for(let i = 0; i < Array_Data[e.target.value].length; ++i){
            props.setArray(lists => [...lists, {
                id: Array_Data[e.target.value][i].id,
                name: Array_Data[e.target.value][i].name,
                count: Array_Data[e.target.value][i].count,
                type: Array_Data[e.target.value][i].type
            }])
        }
         */
    }

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
                {/* combo(상품분류코드) */}
                <div className={styles.div1}>
                    <span className={styles.txt}>
                        <p className={styles.p2}>{`전체 `}</p>
                        <p className={styles.p2}>예·적금 상품</p>
                    </span>
                </div>
                {/*<SelectBox*/}
                {/*    selectWrapperStyle={styles.seltBox}*/}
                {/*    options={actKindCdList}*/}
                {/*    // defaultValue={inputJobCategory}*/}
                {/*    // onChange={(e) => setInputJobCategory(e.target.value) }*/}
                {/*/>*/}
                <select className={styles.seltBox} onChange={handleSelect}>
                    {actKindCdList.map((e) => {
                            return (
                                <option key={e.groupCode + "_" + e.codeId} value={e.codeId}>{e.codeNm}</option>
                            );
                        })
                    }
                </select>
                {/*<div className={styles.seltBox}>*/}
                {/*    <div className={styles.inBox}/>*/}
                {/*    <div className={styles.div2}>예금</div>*/}
                {/*    <img className={styles.seltBoxChild} alt="" src="/polygon-1.svg"/>*/}
                {/*</div>*/}
                <div className={styles.moreView} onClick={(e) => {
                    navigate('/product-detail');
                }}>
                    <div className={styles.div3}>모든 상품 전체 보기</div>
                    <img className={styles.sArrIcoIcon} alt="" src="/sarrico.svg"/>
                    <div className={styles.wLine}/>
                </div>
                <Slider {...settings}>
                {
                    productList.map((e, idx) => {
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
                <select className={styles.seltBox1}>
                    {ageCdList.map((e) => {
                        return (
                            <option key={e.groupCode + "_" + e.codeId} value={e.codeId}>{e.codeNm}</option>
                        );
                    })
                    }
                </select>
                {/*<div className={styles.seltBox1}>*/}
                {/*    <div className={styles.inBox}/>*/}
                {/*    <div className={styles.div2}>30대</div>*/}
                {/*    <img className={styles.seltBoxChild} alt="" src="/polygon-1.svg"/>*/}
                {/*</div>*/}
                <div className={styles.moreView1}>
                    <div className={styles.div3}>인기 상품 전체 보기</div>
                    <img className={styles.sArrIcoIcon} alt="" src="/sarrico.svg"/>
                    <div className={styles.wLine}/>
                </div>
                <div className={styles.bgBox3}/>
                <img className={styles.shinhanIcoIcon1} alt="" src="/shinhanico@2x.png"/>
                <div className={styles.sTit3}>
                    <div className={styles.db}>DB저축은행</div>
                    <div className={styles.m}>M-정기예금</div>
                </div>
                <b className={styles.b6}>최고금리</b>
                <div className={styles.div17}>
                    <span className={styles.txt}>
                        <span className={styles.span3}>{` `}</span>
                        <span className={styles.span4}>
                            <span className={styles.span5}>
                                <b className={styles.b2}>4.3</b>
                                <span className={styles.span6}>%</span>
                            </span>
                            <span className={styles.span6}>
                                <span className={styles.span8}>{` `}</span>
                            </span>
                            <span>(세전)</span>
                        </span>
                    </span>
                </div>
                <div className={styles.div18}>(12개월 / 온라인가입 기준)</div>
                <div className={styles.bgBox4}/>
                <img className={styles.kbIcoIcon1} alt="" src="/kbico@2x.png"/>
                <div className={styles.sTit4}>
                    <div className={styles.db}>토스뱅크</div>
                    <div className={styles.m}>먼저이자받는정기예금</div>
                </div>
                <b className={styles.b8}>최고금리</b>
                <div className={styles.div21}>
                    <span className={styles.txt}>
                        <span className={styles.span3}>{` `}</span>
                        <span className={styles.span4}>
                            <span className={styles.span5}>
                                <b className={styles.b2}>3.5</b>
                                <span className={styles.span6}>%</span>
                            </span>
                            <span className={styles.span6}>
                                <span className={styles.span8}>{` `}</span>
                            </span>
                            <span>(세전)</span>
                        </span>
                    </span>
                </div>
                <div className={styles.div22}>( 6개월 / 온라인가입 기준)</div>
                <div className={styles.bgBox5}/>
                <img className={styles.kakaoIcoIcon1} alt="" src="/kakaoico@2x.png"/>
                <div className={styles.sTit5}>
                    <div className={styles.ibk}>IBK기업은행</div>
                    <div className={styles.m}>IBK평생한가족통장</div>
                </div>
                <b className={styles.b10}>최고금리</b>
                <div className={styles.div23}>
                    <span className={styles.txt}>
                        <span className={styles.span36}>{` `}</span>
                        <span className={styles.span37}>{` `}</span>
                        <span className={styles.span4}>
                            <span className={styles.span5}>
                                <b className={styles.b2}>3.8</b>
                                <span className={styles.span40}>%</span>
                            </span>
                            <span className={styles.span40}>
                                <span className={styles.span8}>{` `}</span>
                            </span>
                            <span>(세전)</span>
                        </span>
                    </span>
                </div>
                <div className={styles.div24}>( 36개월 / 온라인가입 기준)</div>
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
};

export default Main;
