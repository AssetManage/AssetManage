import axios from 'axios';

import { useEffect, useState, useRef } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';
import Sheet from 'react-modal-sheet';

import styles from "./ProductList.module.css";
import Header from '../common/header/Header';

// B/S
import * as BS from "./bottomSheet/BS";

const ProductList = ({ className, ...props }) => {

    // 이전 화면에서 넘어온 파라미터
    const location = useLocation();
    const prev = { ...location.state };

    const navigate = useNavigate();

    // 0. 로그인 여부 체크
    const isin = localStorage.getItem('accessToken') == null ? false : true;
    // tmp
    // const isin = false;

    // variables
    const initParam = {'actKindCd':'DP', 'joinWayCd':'N', 'cnsmpInclnCd':'AT', 'finCoNoList':[]};
    const [param, setParam] = useState(initParam);
    const [conditionList, setCondtionList] = useState({});
    const [productList, setProductList] = useState([]);

    // TO-DO :: 하나로 사용하는 방법 찾기
    const [isOpen1, setOpen1] = useState(false);
    const [isOpen2, setOpen2] = useState(false);

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
        
        // TO-DO :: 이전 화면에서 넘어온 파라미터 세팅에 반영 (prev)
        // 넘어온 파라미터를 사용하면 user 정보 안불러와도 됨
        console.log('prev :: ', prev);

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
            // TO-DO :: list 파라미터 parsing
            // , paramsSerializer: params => {
            //     return qs.stringify(params, { arrayFormat : 'brackets' })
            // }
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

        // 재렌더링 될 때 set됨
        setParam((prev) => {
            def[e.key] = e.codeId;

            // 상품 분류가 예금인 경우, 적립유형 display 및 param 제어
            if(def.actKindCd == 'DP'){
                delete def.rsrvType;
            }else{
                if(null == def.rsrvType) def = {...def, 'rsrvType': 'S'};
            }
            getProductList(def);
            return def;
        });
    }

    const clickReset = (e) => {
        setParam((prev) => {
            getProductList(initParam);
            return initParam;
        });
    }

    const clickProduct = (idx) => {
        const product = productList[idx];
        // console.log('product :: ', product);
        navigate('/product-detail', {
            state: {
                'finCoNo' : product.finCoNo,
                'finPrdtCd' : product.finPrdtCd,
                'dclsMonth' : product.dclsMonth,
            }
        });
    }

    const callBack1 = (ret) => {
        setOpen1(false);
        console.log('ret :: ', ret);
    }

    const callBack2 = (ret) => {
        setOpen2(false);
        console.log('ret :: ', ret);
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
                        {/* title */}
                        <div className={`${styles.font28} ${styles.bold700} `}>예·적금 상품</div>
                        {/* condition */}
                        <div className={styles.conditionArea}>
                            <ConditionArea/>
                            <ConditionSelectedArea/>
                        </div>
                        {/* bottom sheet button */}
                        <ConditionBtnArea/>
                        {/*  productList */}
                        <ProductArea/>
                    </div>
                </div>
                {/*<BottomSheet />*/}
                {/* TO-DO :: 임시 */}
                {isOpen1 && (
                    <BS.BankBS open={isOpen1} param={param} callback={callBack1}></BS.BankBS>
                )}
                {isOpen2 && (
                    <BS.ConditionsBS open={isOpen2} param={param} callback={callBack2}></BS.ConditionsBS>
                )}
            </div>
        </div>
    )

    // component
    function ConditionArea() {
        const keys = Object.keys(conditionList);
        let cnt = keys.length;
        return <div className={styles.conditionList}>
            {keys.map((e, idx) => {
                if (keys[idx] == "rsrvType"){
                    if(param.actKindCd == 'SV') return CondtionList({"key":keys[idx], "list":conditionList[keys[idx]]});
                } else return CondtionList({"key":keys[idx], "list":conditionList[keys[idx]]});
            })}
        </div>;
    }

    // 복수 선택 조건 display 되도록 구현
    // TO-DO :: 소비성향코드를 옵션대로 임의로 추출해서 검색 조건으로 복수 설정되도록 사용할 것인지 협의
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
                            <div className={`${styles.cnt} ${param[data.key] == e.codeId ? styles.selected : ''}`}
                                 onClick={() => clickParam({...e, "key":data.key})}>
                                {e.codeNm}
                            </div>
                        </div>
                    </>
                );
            })
            } </div>
    }

    function ConditionSelectedArea() {
        return <div className={styles.selectedArea}>
            <div className={styles.selected}>
                {/* selected condition */}
                <span className={styles.cnt}>깨진장독대형</span>
                <img className={styles.x} src="vector2.svg"/>
            </ div>
            <div className={styles.reset} onClick={() => clickReset()}>
                <img className={styles.ico} src="group-350.svg"/>
            </div>
        </div>;
    }

    function ConditionBtnArea() {
        return <div className={styles.conditionBtnArea}>
            <button className={` ${styles.colorBlack} ${styles.backgroundWhite}`} onClick={() => setOpen2(true) } >소득 · 연령</button>
            <button className={` ${styles.colorWhite} ${styles.backgroundNavy}`} onClick={() => setOpen1(true) }>은행선택</button>
            {/*<img className={styles.polygon3} src="polygon-30.svg"/>*/}
        </div>;
    }

    // TO-DO :: top3인 경우 좌측에 rank 표시, 상품이 속하는 소비성향목록 아이콘 표시
    function ProductArea() {
        return <div className={styles.productListArea}>
            {productList.map((e, idx) => {
                return (<>
                        <div className={styles.product} onClick={(e) => clickProduct(idx)}>
                            {/* rank */}
                            {/*<span style={{'display': 'inline-block'}}>{idx + 1}</span>*/}
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