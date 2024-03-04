import axios from 'axios';
import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
import styles from "./Main.module.css";

import Header from '../common/header/Header';
import ProductRecmdPopup from '../product-recmd-popup/ProductRecmdPopup';
import ProductRecmdPopup2 from '../product-recmd-popup/ProductRecmdPopup2';

const Main = () => {
    const [productList, setProductList] = useState([]);

    useEffect(() => {
        axios.get('/st/product/selectProductListWithOpt', {
            params : {
                'cnsmpInclnCd': 'AT',
                'limit': 3
            }
        })
            .then(res => {
                console.log(res.data.list);
                setProductList(res.data.list);
            })
            .catch(err => {
                console.log(err);
            });
    }, []);
	return (
		<div className={styles.main1}>
			<div className={styles.main}>
			    <Header />
                <div className={styles.main1Child} />
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
                        <div className={styles.btnBox} />
                        <b className={styles.b}>나에게 맞는 예·적금 추천받기</b>
                    </div>
                    <img className={styles.icon} alt="" src="/group-57.png" />
                </div>
                <div className={styles.div1}>
                    <span className={styles.txt}>
                        <p className={styles.p2}>{`전체 `}</p>
                        <p className={styles.p2}>예·적금 상품</p>
                    </span>
                </div>
                <div className={styles.seltBox}>
                    <div className={styles.inBox} />
                    <div className={styles.div2}>예금</div>
                    <img className={styles.seltBoxChild} alt="" src="/polygon-1.svg" />
                </div>
                <div className={styles.moreView}>
                    <div className={styles.div3}>모든 상품 전체 보기</div>
                    <img className={styles.sArrIcoIcon} alt="" src="/sarrico.svg" />
                    <div className={styles.wLine} />
                </div>
                <div className={styles.bgBox} />
                <img className={styles.shinhanIcoIcon} alt="" src="/shinhanico@2x.png" />
                <div className={styles.sTit}>
                    <div className={styles.db}>신한은행</div>
                    <div className={styles.m}>더드림정기예금</div>
                </div>
                <b className={styles.b1}>최고금리</b>
                <div className={styles.div6}>
                    <span className={styles.txt}>
                        <span className={styles.span3}>{` `}</span>
                        <span className={styles.span4}>
                            <span className={styles.span5}>
                                <b className={styles.b2}>3.7</b>
                                <span className={styles.span6}>%</span>
                            </span>
                            <span className={styles.span6}>
                                <span className={styles.span8}>{` `}</span>
                            </span>
                            <span>(세전)</span>
                        </span>
                    </span>
                </div>
                <div className={styles.div7}>(12개월 / 온라인가입 기준)</div>
                <>{
                    productList.map((e) => {
                        return (
                            <div>
                                <div className={styles.bgBox}/>
                                <img className={styles.shinhanIcoIcon} alt="" src="/shinhanico@2x.png"/>
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
                }</>
                <div className={styles.bgBox1}/>
                <img className={styles.kbIcoIcon} alt="" src="/kbico@2x.png"/>
                <div className={styles.sTit1}>
                    <div className={styles.db}>국민은행</div>
                    <a
                        className={styles.kbStar}
                        href={`https://obank.kbstar.com/quics?page=C016613&cc=b061496:b061496#none`}
                        target="_blank"
                    >
                        KB Star 정기예금
                    </a>
                </div>
                <b className={styles.b3}>최고금리</b>
                <div className={styles.div9}>
                    <span className={styles.txt}>
                        <span className={styles.span3}>{` `}</span>
                        <span className={styles.span4}>
                            <span className={styles.span5}>
                                <b className={styles.b2}>3.7</b>
                                <span className={styles.span6}>%</span>
                            </span>
                            <span className={styles.span6}>
                                <span className={styles.span8}>{` `}</span>
                            </span>
                            <span>(세전)</span>
                        </span>
                    </span>
                </div>
                <div className={styles.div10}>( 12개월 / 온라인가입 기준)</div>
                <div className={styles.bgBox2} />
                <img className={styles.kakaoIcoIcon} alt="" src="/kakaoico1@2x.png" />
                <div className={styles.sTit2}>
                    <div className={styles.db}>카카오뱅크</div>
                    <div className={styles.m}>카카오뱅크 정기예금</div>
                </div>
                <b className={styles.b5}>최고금리</b>
                <div className={styles.div13}>
                    <span className={styles.txt}>
                        <span className={styles.span15}>{` `}</span>
                        <span className={styles.span16}>
                            <b>3.8</b>
                            <span className={styles.span15}>%</span>
                        </span>
                        <span className={styles.span15}>
                            <span>{` `}</span>
                        </span>
                        <span className={styles.span19}>(세전)</span>
                    </span>
                </div>
                <div className={styles.div14}>( 12개월 / 온라인가입 기준)</div>
                <img className={styles.lArrowIcon} alt="" src="/larrow.svg" />
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
                <div className={styles.seltBox1}>
                    <div className={styles.inBox} />
                    <div className={styles.div2}>30대</div>
                    <img className={styles.seltBoxChild} alt="" src="/polygon-1.svg" />
                </div>
                <div className={styles.moreView1}>
                    <div className={styles.div3}>인기 상품 전체 보기</div>
                    <img className={styles.sArrIcoIcon} alt="" src="/sarrico.svg" />
                    <div className={styles.wLine} />
                </div>
                <div className={styles.bgBox3} />
                <img className={styles.shinhanIcoIcon1} alt="" src="/shinhanico@2x.png" />
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
                <div className={styles.bgBox4} />
                <img className={styles.kbIcoIcon1} alt="" src="/kbico@2x.png" />
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
                <div className={styles.bgBox5} />
                <img className={styles.kakaoIcoIcon1} alt="" src="/kakaoico@2x.png" />
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
                <img className={styles.lArrowIcon1} alt="" src="/larrow.svg" />
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
