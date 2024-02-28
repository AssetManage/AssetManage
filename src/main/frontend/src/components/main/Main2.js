import styles from "./Main2.module.css";

import Header from '../common/header/Header';

const Main2 = () => {
	return (
		<div className={styles.mian2}>
			<div className={styles.main}>
			    <Header />
                <div className={styles.mian2Child} />
                <img className={styles.ellipseIcon} alt="" src="/ellipse1.svg" />
                <img className={styles.icon} alt="" src="/015-1@2x.png" />
                <div className={styles.div1}>나의 소비유형은?</div>
                <div className={styles.div2}>:: 하루살이형</div>
                <div className={styles.div3}>
                    <p className={styles.p}>사용자의 소비유형 설명입니다.</p>
                    <p className={styles.p}>내용을 넣어주세요.</p>
                </div>
                <div className={styles.btn}>
                    <div className={styles.btnBox} />
                    <b className={styles.b}>나에게 맞는 예·적금 추천받기</b>
                </div>
                <div className={styles.top3}>
                    <p className={styles.p2}>나의</p>
                    <p className={styles.p}>
                        <span className={styles.span}>{`추천 예금 `}</span>
                        <b className={styles.top32}>TOP3</b>
                    </p>
                </div>
                <div className={styles.seltBox}>
                    <div className={styles.inBox} />
                    <div className={styles.div4}>소득별</div>
                    <img className={styles.seltBoxChild} alt="" src="/polygon-1.svg" />
                </div>
                <div className={styles.moreView}>
                    <div className={styles.div5}>추천 상품 전체 보기</div>
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
                <div className={styles.div8}>
                    <span className={styles.txt}>
                        <span className={styles.span1}>{` `}</span>
                        <span className={styles.span2}>
                            <span className={styles.span3}>
                                <b className={styles.b2}>3.7</b>
                                <span className={styles.span4}>%</span>
                            </span>
                            <span className={styles.span4}>
                                <span className={styles.span6}>{` `}</span>
                            </span>
                            <span>(세전)</span>
                        </span>
                    </span>
                </div>
                <div className={styles.div9}>(12개월 / 온라인가입 기준)</div>
                <div className={styles.bgBox1} />
                <img className={styles.kbIcoIcon} alt="" src="/kbico@2x.png" />
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
                <div className={styles.div11}>
                    <span className={styles.txt}>
                        <span className={styles.span1}>{` `}</span>
                        <span className={styles.span2}>
                            <span className={styles.span3}>
                                <b className={styles.b2}>3.7</b>
                                <span className={styles.span4}>%</span>
                            </span>
                            <span className={styles.span4}>
                                <span className={styles.span6}>{` `}</span>
                            </span>
                            <span>(세전)</span>
                        </span>
                    </span>
                </div>
                <div className={styles.div12}>( 12개월 / 온라인가입 기준)</div>
                <div className={styles.bgBox2} />
                <img className={styles.kakaoIcoIcon} alt="" src="/kakaoico@2x.png" />
                <div className={styles.sTit2}>
                    <div className={styles.ibk}>IBK기업은행</div>
                    <div className={styles.m}>IBK평생한가족통장</div>
                </div>
                <b className={styles.b5}>최고금리</b>
                <div className={styles.div13}>
                    <span className={styles.txt}>
                        <span className={styles.span13}>{` `}</span>
                        <span className={styles.span14}>{` `}</span>
                        <span className={styles.span2}>
                            <span className={styles.span3}>
                                <b className={styles.b2}>3.8</b>
                                <span className={styles.span17}>%</span>
                            </span>
                            <span className={styles.span17}>
                                <span className={styles.span6}>{` `}</span>
                            </span>
                            <span>(세전)</span>
                        </span>
                    </span>
                </div>
                <div className={styles.div14}>( 36개월 / 온라인가입 기준)</div>
                <div className={styles.top33}>
                    <span className={styles.txt}>
                        <p className={styles.p2}>나의</p>
                        <p className={styles.p}>
                            <span className={styles.span}>{`추천 적금 `}</span>
                            <b className={styles.top32}>TOP3</b>
                        </p>
                    </span>
                </div>
                <div className={styles.seltBox1}>
                    <div className={styles.inBox} />
                    <div className={styles.div4}>소득별</div>
                    <img className={styles.seltBoxChild} alt="" src="/polygon-1.svg" />
                </div>
                <div className={styles.moreView1}>
                    <div className={styles.div5}>추천 상품 전체 보기</div>
                    <img className={styles.sArrIcoIcon} alt="" src="/sarrico.svg" />
                    <div className={styles.wLine} />
                </div>
                <div className={styles.bgBox3} />
                <img className={styles.shinhanIcoIcon1} alt="" src="/shinhanico@2x.png" />
                <div className={styles.sTit3}>
                    <div className={styles.db}>DB저축은행</div>
                    <div className={styles.m}>M-정기예금</div>
                </div>
                <b className={styles.b7}>최고금리</b>
                <div className={styles.div17}>
                    <span className={styles.txt}>
                        <span className={styles.span1}>{` `}</span>
                        <span className={styles.span2}>
                            <span className={styles.span3}>
                                <b className={styles.b2}>4.3</b>
                                <span className={styles.span4}>%</span>
                            </span>
                            <span className={styles.span4}>
                                <span className={styles.span6}>{` `}</span>
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
                <b className={styles.b9}>l</b>
                <div className={styles.div21}>
                    <span className={styles.txt}>
                        <span className={styles.span1}>{` `}</span>
                        <span className={styles.span2}>
                            <span className={styles.span3}>
                                <b className={styles.b2}>3.5</b>
                                <span className={styles.span4}>%</span>
                            </span>
                            <span className={styles.span4}>
                                <span className={styles.span6}>{` `}</span>
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
                <b className={styles.b11}>최고금리</b>
                <div className={styles.div23}>
                    <span className={styles.txt}>
                        <span className={styles.span13}>{` `}</span>
                        <span className={styles.span14}>{` `}</span>
                        <span className={styles.span2}>
                            <span className={styles.span3}>
                                <b className={styles.b2}>3.8</b>
                                <span className={styles.span17}>%</span>
                            </span>
                            <span className={styles.span17}>
                                <span className={styles.span6}>{` `}</span>
                            </span>
                            <span>(세전)</span>
                        </span>
                    </span>
                </div>
                <div className={styles.div24}>( 36개월 / 온라인가입 기준)</div>
            </div>
		</div>
	);
};

export default Main2;
