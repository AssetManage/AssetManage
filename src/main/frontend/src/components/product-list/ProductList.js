import axios from 'axios';

import { useEffect, useState, useRef } from 'react'
import { useNavigate } from 'react-router-dom';

import styles from "./ProductList.module.css";

import Header from '../common/header/Header';

const ProductList = ({ className, ...props }) => {

    const navigate = useNavigate();

    // 0. 로그인 여부 체크
    const isin = localStorage.getItem('accessToken') == null ? false : true;
    // tmp
    // const isin = false;


    const [param, setParam] = useState({'actKindCd':'DP', 'joinWayCd':'N', 'cnsmpInclnCd':'AT'});
    const [conditionList, setCondtionList] = useState({});

    const [productList, setProductList] = useState([]);


    const init = async() => {

        // setConditionList
        const cnsmpInclnCdList = await getCodeList('cnsmp_incln_cd');
        const actKindCdList = await getCodeList('act_kind_cd');
        const joinWayCdList = await getCodeList('join_way_cd');
        const rsrvTypeList = await getCodeList('rsrv_type');
        setCondtionList(
            {
                'cnsmpInclnCd' : cnsmpInclnCdList,
                'joinWayCd' : joinWayCdList,
                'actKindCd' : actKindCdList,
                'rsrvType' : rsrvTypeList,
            }
        );

        // setParam
        // 소비유형(개미형(AT)), 상품분류(예금(DP)), 가입방식(비대면(N))
        // 로그인한 경우, 로그인 사용자의 소비유형코드, 소득, 연령대를 기본값으로 설정한다
        if(isin){
            axios.get('/st/user/selectUserSimple', {
                headers:{
                    Authorization : localStorage.getItem('accessToken') // 로그인 사용자 token
                }
            })
                .then(res => {
                    const info = res.data.data;
                    const def = {...param, 'cnsmpInclnCd':info.cnsmpInclnCd, 'ageCd':info.ageCd, 'incomeScopeCd':info.incomeScopeCd};
                    setParam(def);
                    getProductList(def);
                })
                .catch(err => {
                // TO-DO :: 토큰은 존재하나 백단에서 user 정보가 null로 조회되는 경우, 에러 처리 추가
                console.log(err);
            });
        }else getProductList(param);
        // console.log('conditionList :: ', conditionList);
        // console.log('param :: ', param);

    }

    // axios
    const getCodeList = async (grpCodeId, codeId) => {
        return await axios.get('/st/code/selectCodeList', {
            params : {
                'groupCode' : grpCodeId,
                'codeId' : codeId,
                'useYn' : 'Y'}
        }).then(res => {
            return res.data.list;
        }).catch(err => {
                console.log(err);
        });
    }

    const getProductList = (params) => {
        console.log('params :: ', params);

        axios.get('/st/product/selectProductListWithOpt', {
            params : params
        })
            .then(res => {
                setProductList(res.data.list);
            })
            .catch(err => {
                console.log(err);
            });
    }

    // event
    const clickParam = (e) => {

        let def = {...param};
        
        // TO-DO :: 변경한 param이 아니라 이전값으로 넘어가는 것 수정
        setParam((prev) => {
            def[e.key] = e.codeId;

            // 상품 분류가 예금인 경우, 적립유형 display 및 param 제어
            if(def.actKindCd == 'DP'){
                delete def.rsrvType;
            }else{
                if(null == def.rsrvType) def['rsrvType'] = 'S';
            }
            return def;
        });

        // console.log('def :: ', def);

        getProductList(def);
    }

    // init
    useEffect(() => {
        init();
    }, []);

    return (
        <div className={styles.productDetail + " " + className}>
            <div className={styles.product1}>
                {/* header */}
                <Header />
                <div className={styles.frame71}>
                    <div className={styles.frame72}>
                        <div className={`${styles.font28} ${styles.bold700} `} >예·적금 상품</div>

                        {/* condition */}
                        <div className={styles.conditionArea}>
                            <ConditionArea />

                            {/* selected condition */}
                            <div className={styles.selectedArea}>
                                <div className={styles.selected}>
                                    <span className={styles.cnt}>깨진장독대형</span>
                                    <img className={styles.x} src="vector2.svg"/>
                                </ div>
                            </div>
                            {/* 초기화 */}
                            {/*<div className={styles.group95}>*/}
                            {/*    <div className={styles.btnBox5}></div>*/}
                            {/*    <img className={styles.group35} src="group-350.svg"/>*/}
                            {/*</div>*/}
                        </div>
                        {/* bottom sheet button */}
                        <div className={styles.conditionBtnArea}>
                            <button className={` ${styles.colorBlack} ${styles.backgroundWhite} `}>소득 · 연령</button>
                            <button className={` ${styles.colorWhite} ${styles.backgroundNavy}`}>은행선택</button>
                            {/*<img className={styles.polygon3} src="polygon-30.svg"/>*/}
                        </div>

                        {/*  productList */}
                        <ProductArea />



                    </div>
                </div>
            </div>
        </div>
    )

    function ConditionArea(){
        const keys = Object.keys(conditionList);
        let cnt = keys.length;
        return <div className={styles.conditionList}>
                        { keys.map((e, idx) => {
                            if(keys[idx] == "rsrvType"){
                                if(param.actKindCd == 'SV') return CondtionList({"key":keys[idx], "list":conditionList[keys[idx]]});
                            } else return CondtionList({"key":keys[idx], "list":conditionList[keys[idx]]});
                        })}
        </div>;
    }

    // TO-DO :: 선택된 파라미터 파란 박스 display 기능, 검색 조건 초기화 기능
    function CondtionList(data){
        return <div className={styles.condition}>
                    {/* 조건 타이틀 */}
                    <div className={styles.conditionTit}>
                        <div className={styles.ellipse}></div>
                        {/* TO-DO :: 코드 테이블에 표시명을 따로 설정하기 전까지는 해당 처리 불가피 */}
                        <div className={styles.nm}> {data.list[0].groupCodeNm.replace('코드', '')} </div>
                    </div>
                    {   data.list.map((e, idx) => {
                        return (
                            <>
                                <div className={styles.conditionCnts}>
                                    {/* 파라미터로 선택된 경우, selected css 적용 */}
                                    <div className={`${styles.cnt} ${param[data.key] == e.codeId ? styles.selected : ''}`} onClick={() => clickParam({...e, "key":data.key})}>
                                        {e.codeNm}
                                    </div>
                                </div>
                            </>
                        );
                    })
            } </div>
    }

    // TO-DO :: top3인 경우 좌측에 rank 표시, 상품이 속하는 소비성향목록 아이콘 표시
    function ProductArea() {

        return <div className={styles.productListArea}>
            { productList.map((e, idx) => {
                return (<>
                        <div className={styles.product}>
                            {/* productTit*/}
                            <div className={styles.productTit}>
                                <img className={styles.ico} src={e.finCoNoImgUrl}/>
                                <div className={styles.tit}>
                                    <span className={` ${styles.font16} ${styles.bold500} `}> {e.korCoNm} </span>
                                    <span className={` ${styles.font18} ${styles.bold600} `}> {e.finPrdtNm} </span>
                                </div>
                            </div>
                            {/* productCnts*/}
                            <div className={styles.productCnts}>
                                <div className={styles.rate}>
                                                <span
                                                    className={` ${styles.font18} ${styles.colorOrange} ${styles.bold800} `}
                                                    style={{"margin-right": "10px"}}>최고</span>
                                    <span
                                        className={` ${styles.font32} ${styles.colorNavy} ${styles.bold700} `}>{e.intrRate2}</span>
                                    <span
                                        className={` ${styles.font20} ${styles.colorNavy} ${styles.bold800}`}>%</span>
                                </div>
                                <div className={styles.trms}>
                                    <span className={` ${styles.font14} ${styles.colorBlack} ${styles.bold400}`}>({e.saveTrm}개월 / 세금공제전 / {e.joinWayNm})</span>
                                </div>
                            </div>
                        </div>
                        <div className={styles.wLine}></div>
                    </>
                )
            })} </ div>
    }

};


export default ProductList;