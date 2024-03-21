import {useEffect, useState} from "react";
import Sheet from "react-modal-sheet";
import axios from 'axios';

import bankStyles from "./BankBS.module.css";
import conditionsStyles from "./ConditionsBS.module.css";

// TO-DO :: 공통 함수 하나의 js 파일로 관리
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

export const BankBS = () => {
    const [isOpen, setOpen] = useState(false);

    // variables
    const [bankList, setBankList] = useState([]);
    const [selectedList, setSelectedList] = useState([]);
    const init = async() => {
        setBankList(await getCodeList('fin_co_no'));
    }

    const clickBank = (e) => {
        setSelectedList((prev) => {
            if(prev.includes(e.codeId)){
                return [...prev].filter((el) => el !== e.codeId);
            }else return [...prev, e.codeId];
        });
    }

    const clickBtnConfirm = (e) => {
        console.log('selectedList :: ', selectedList);
        setOpen(false);
    }

    useEffect(() => {
        init();
    }, []);

    return (
        <Sheet
            isOpen={true}
            onClose={() => setOpen(false)}
            snapPoints={[750, 750, 100, 0]}
            initialSnap={1}
        >
            <Sheet.Container>
                <Sheet.Header/>
                <Sheet.Content>
                    <div className={bankStyles.frame78}>
                        <div className={bankStyles.frame80}>
                            <div className={bankStyles.bankList}>
                                <div className={bankStyles.tit}>금융사</div>
                                <div className={bankStyles.cnts}>
                                    <BankList />
                                </div>
                                <div className={bankStyles.btnArea}>
                                    <button className={bankStyles.btnConfirm} onClick={(e) => clickBtnConfirm(e)}>
                                        금융사 상품 찾아보기
                                    </button>
                                    {/*<img className={bankStyles.clsIco} src="cls-ico0.svg"/>*/}
                                </div>
                            </div>

                        </div>
                    </div>
                </Sheet.Content>
            </Sheet.Container>
            <Sheet.Backdrop/>
        </Sheet>
    );

    function BankList(){
        return bankList.map((e, idx) => {
                return (
                    // <div className={` ${bankStyles.bank} ${bankStyles.selected}`}>
                    <div className={` ${bankStyles.bank} ${selectedList.includes(e.codeId) ? bankStyles.selected : ''}`} onClick={() => clickBank({...e, 'idx':idx})}>
                        <img className={bankStyles.ico} src={e.etc1}/>
                        <div className={bankStyles.nm}>{e.codeNm}</div>
                    </div>
                );
        })
    }
}

export const ConditionsBS = ({className, ...props}) => {
    const [isOpen, setOpen] = useState(false);

    // variables
    const [ageCdList, setAgeCdList] = useState([]);
    const [incomeScopeCdList, setIncomeScopeCdList] = useState([]);
    const [selected, setSelected] = useState({});

    const init = async() => {
        setAgeCdList(await getCodeList('age_cd'));
        setIncomeScopeCdList(await getCodeList('income_scope_cd'));
    }

    const clickCondition = (e) => {
        let def = {...selected};
        setSelected((prev) => {
            if(def[e.key] === e.codeId){
                delete def[e.key];
            }else{
                def[e.key] = e.codeId;
            }
            return def;
        });
    }

    const clickBtnConfirm = (e) => {
        console.log('selected :: ', selected);
        setOpen(false);
    }

    useEffect(() => {
        init();
    }, []);

    return (
        <Sheet
            isOpen={true}
            onClose={() => setOpen(false)}
            snapPoints={[550, 550, 100, 0]}
            initialSnap={1}
        >
            <Sheet.Container>
                <Sheet.Header/>
                <Sheet.Content>
                    <div className={conditionsStyles.frame74 + " " + className}>
                        <div className={conditionsStyles.frame77}>
                            <div className={conditionsStyles.conditionList}>
                                <div className={conditionsStyles.condition}>
                                    <div className={conditionsStyles.tit}>소득</div>
                                    <div className={conditionsStyles.cnts}>
                                        <AgeList />
                                    </div>
                                </div>
                                <div className={conditionsStyles.condition}>
                                    <div className={conditionsStyles.tit}>연령</div>
                                    <div className={conditionsStyles.cnts}>
                                        <IncomeScopeList />
                                    </div>
                                </div>
                                <div className={conditionsStyles.btnArea}>
                                    <button
                                        className={conditionsStyles.btnConfirm} onClick={(e) => clickBtnConfirm(e)}>
                                        적용
                                    </button>
                                    {/*<img className={bankStyles.clsIco} src="cls-ico0.svg"/>*/}
                                </div>
                            </div>
                        </div>
                    </div>
                </Sheet.Content>
            </Sheet.Container>
            <Sheet.Backdrop/>
        </Sheet>
    );

    function AgeList(){
        return ageCdList.map((e, idx) => {
            return (
                    <div className={` ${conditionsStyles.cnt} ${selected['ageCd'] === e.codeId ? conditionsStyles.selected : ''}`}
                         onClick={() => clickCondition({...e, 'key':'ageCd'})}>
                        {e.codeNm}
                    </div>
                );
        })
    }

    function IncomeScopeList(){
        return incomeScopeCdList.map((e, idx) => {
            return (
                <div className={` ${conditionsStyles.cnt} ${selected['incomeScopeCd'] === e.codeId ? conditionsStyles.selected : ''}`}
                     onClick={() => clickCondition({...e, 'key':'incomeScopeCd'})}>
                    {e.codeNm}
                </div>
            );
        })
    }
};