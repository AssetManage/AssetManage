import styles from "./ProductDetail.module.css";

const ProductDetail = () => {
	return (
		<div className={styles.sub2}>
			<div className={styles.main}>
                <div className={styles.logo}>LOGO</div>
                <div className={styles.menu}>
                    <div className={styles.menu01}>menu01</div>
                    <div className={styles.menu02}>menu02</div>
                    <div className={styles.menu03}>menu03</div>
                </div>
                <div className={styles.div}>
                    <img className={styles.vectorIcon} alt="" src="/vector.svg" />
                    <b className={styles.logOut}>Log out</b>
                    <img className={styles.groupIcon} alt="" src="/group.svg" />
                    <div className={styles.kimsw1245}>김선우(kimsw1245)</div>
                </div>
                <div className={styles.bgBox} />
                <div className={styles.sLogo}>
                    <img
                        className={styles.shinhanIcoIcon}
                        alt=""
                        src="/shinhanico@2x.png"
                    />
                    <div className={styles.sTit}>
                        <div className={styles.div1}>신한저축은행</div>
                    </div>
                </div>
                <div className={styles.banCont}>
                    <b className={styles.b}>더드림정기예금</b>
                    <div className={styles.div2}>
                        회전주기(매1년)마다 영업점 방문 없이 변동금리로 자동 회전되는 장기
                        목돈굴리기 상품
                    </div>
                    <div className={styles.groupParent}>
                        <div className={styles.groupContainer}>
                            <img className={styles.groupChild} alt="" src="/group-15.svg" />
                            <div className={styles.parent}>
                                <div className={styles.div3}>가입기간</div>
                                <div className={styles.div4}>
                                    <b>6~36</b>
                                    <span className={styles.span}>개월</span>
                                </div>
                            </div>
                        </div>
                        <div className={styles.groupDiv}>
                            <img className={styles.groupChild} alt="" src="/group-14.svg" />
                            <div className={styles.div5}>가입금액</div>
                            <div className={styles.div6}>
                                <span>
                                    <b>10</b>
                                </span>
                                <span className={styles.span1}>
                                    <span>만원</span>
                                    <span className={styles.span2}> 이상</span>
                                </span>
                            </div>
                        </div>
                        <div className={styles.groupParent1}>
                            <img className={styles.groupChild} alt="" src="/group-8.svg" />
                            <div className={styles.div7}>최고금리</div>
                            <div className={styles.group}>
                                <b className={styles.b1}>연</b>
                                <b className={styles.b2}>3.7%</b>
                                <div className={styles.div8}>(세전)</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div className={styles.tabMenu}>
                    <div className={styles.inBox} />
                    <b className={styles.b3}>상품안내</b>
                    <img className={styles.arrIcoIcon} alt="" src="/arrico.svg" />
                    <div className={styles.inBox1} />
                    <div className={styles.div9}>금리안내</div>
                    <img className={styles.arrIcoIcon1} alt="" src="/arrico.svg" />
                    <div className={styles.inBox2} />
                    <div className={styles.div10}>유의사항</div>
                    <img className={styles.arrIcoIcon2} alt="" src="/arrico.svg" />
                </div>
                <div className={styles.div11}>
                    <div className={styles.div12}>상품안내</div>
                    <div className={styles.bgBox1} />
                    <div className={styles.bgBox2} />
                    <div className={styles.bgBox3} />
                    <div className={styles.wLine} />
                    <div className={styles.wLine1} />
                    <div className={styles.wLine2} />
                    <div className={styles.wLine3} />
                    <div className={styles.wLine4} />
                    <div className={styles.hLine} />
                    <div className={styles.div13}>상품명</div>
                    <div className={styles.div14}>더드림정기예금</div>
                    <div className={styles.div15}>가입대상</div>
                    <div className={styles.div16}>
                        제한 없음(단, 법인은 영업점 방문가입)
                    </div>
                    <div className={styles.div17}>가입기간</div>
                    <div className={styles.div18}>
                        36개월, 48개월, 60개월(12개월 주기로 금리변동)
                    </div>
                    <div className={styles.div19}>가입금액</div>
                    <div className={styles.div20}>10만원 이상</div>
                    <div className={styles.div21}>이자 지급 시기</div>
                    <div className={styles.div22}>
                        <p className={styles.p}>
                            <span className={styles.span3}>{`- 단리 : `}</span>
                            <span>매월 이자 지급</span>
                        </p>
                        <p className={styles.p}>
                            <span className={styles.span3}>{`- 복리 : `}</span>
                            <span>
                                이자와 원금을 만기 시 일괄 지급 (월복리, 연단위 이자지급 가능)
                            </span>
                        </p>
                        <p className={styles.p}>
                            <span className={styles.span3}>{`- 회전 주기 별 이자 : `}</span>
                            <span>
                                회전주기 (매 1년) 도래 시 원금만 연장되고, 이자는 별도 지급되는
                                방식
                            </span>
                        </p>
                    </div>
                    <div className={styles.div23}>비과세 종합저축</div>
                    <div className={styles.div24}>
                        <p className={styles.p}>
                            <span className={styles.span3}>{`- 가입대상자 : `}</span>
                            <span>
                                만65세 이상 고령자, 장애인, 독립유공자와 그 유족 또는 가족,
                                국가유공상이자,
                            </span>
                        </p>
                        <p className={styles.otp}>
                            {" "}
                            기초생활수급자,고엽제후유증 환자, 5.18민주화운동 부상자에 한하여
                            가입 가능
                        </p>
                    </div>
                    <div className={styles.div25}>
                        <span className={styles.span7}>{`- 저축한도 : `}</span>
                        <span>
                            전 금융기관에서 1인당 (원금 기준) 5천만원 이내로 비과세종합저축 가입
                            가능
                        </span>
                    </div>
                    <div className={styles.div26}>- 계약기간만료일 이후 이자는 과세</div>
                    <div className={styles.div27}>
                        - 직전3개 과세기간 내 1회 이상 금융소득 종합과세 대상자는 가입 제한
                        (시행일:2020.01.01)
                    </div>
                </div>
                <div className={styles.div28}>
                    <div className={styles.div12}>금리안내</div>
                    <div className={styles.bgBox1} />
                    <div className={styles.bgBox5} />
                    <div className={styles.bgBox6} />
                    <div className={styles.wLine} />
                    <div className={styles.wLine1} />
                    <div className={styles.wLine2} />
                    <div className={styles.wLine3} />
                    <div className={styles.wLine9} />
                    <div className={styles.hLine1} />
                    <div className={styles.hLine2} />
                    <div className={styles.div30}>기간</div>
                    <div className={styles.div31}>금리(연.세전)</div>
                    <div className={styles.div32}>복리수익률(연.세전)</div>
                    <div className={styles.div33}>3개월</div>
                    <div className={styles.div34}>2.9%</div>
                    <div className={styles.div35}>2.907%</div>
                    <div className={styles.div36}>6개월</div>
                    <div className={styles.div37}>3%</div>
                    <div className={styles.div38}>3.018%</div>
                    <div className={styles.div39}>12개월</div>
                    <div className={styles.div40}>3.6%</div>
                    <div className={styles.div41}>3.659%</div>
                    <div className={styles.div42}>24개월</div>
                    <div className={styles.div43}>1.8%</div>
                    <div className={styles.div44}>1.831%</div>
                    <div className={styles.div45}>36개월</div>
                    <div className={styles.div46}>1.8%</div>
                    <div className={styles.div47}>1.848%</div>
                </div>
                <div className={styles.div48}>
                    <div className={styles.div49}>유의사항</div>
                    <div className={styles.bgBox1} />
                    <div className={styles.div50}>
                        ※ 계약 체결 전 상품설명서 및 약관을 확인하시기 바랍니다.
                    </div>
                    <div className={styles.eContainer}>
                        <p className={styles.p}>
                            - 해당 상품에 대해 충분한 사전 설명을 받을 권리가 있으며, 설명을
                            이해한 후 거래하시기 바랍니다.
                        </p>
                        <p className={styles.p}>
                            - 제한 사항 : 계좌에 압류, 가압류,질권설정 등이 등록될 경우 원금 및
                            이자지급 제한, 예금잔액증명서 발급당일은 잔액변동 불가 등
                        </p>
                        <p className={styles.p}>
                            - e회전정기예금은 인터넷뱅킹, 모바일뱅킹 (SB톡톡+) 을 통하여 가입
                            가능 - e회전정기예금 상품은 인터넷뱅킹, 모바일뱅킹으로만 해지
                            가능(인터넷뱅킹 가입안내: 신분증, 도장 지참하여 본인 내방(서명 거래
                            가능) 또는
                        </p>
                        <p className={styles.otp}>
                            {" "}
                            비대면 계좌개설 서비스를 통해 가입 가능(OTP필요), 미성년자 가입
                            불가)
                        </p>
                        <p className={styles.p}>
                            - 비대면 전용 상품은 무통장으로 가입이 됩니다.
                        </p>
                        <p className={styles.p}>
                            - 해지 및 제신고, 예적금담보대출은 본인 거래만 가능합니다.
                        </p>
                        <p className={styles.p}>
                            - 회전 정기예금을 담보로 한 예적금담보대출은 회전주기 도래에 따라
                            변동된 금리로 적용됩니다.
                        </p>
                        <p className={styles.p}>- 신청고객에 한하여 자동해지가 가능합니다.</p>
                    </div>
                </div>
                <div className={styles.div51}>
                    <div className={styles.bgBox8} />
                    <img
                        className={styles.banProtect1Icon}
                        alt=""
                        src="/ban-protect-1@2x.png"
                    />
                    <div className={styles.txt}>
                        <div className={styles.div52}>예금자 보호 안내</div>
                        <div className={styles.div53}>
                            <p
                                className={styles.p}
                            >{`이 예금은 예금자보호법에 따라 예금보험공사가 보호하되, 보호 한도는 본 저축은행에 있는 귀하의 모든 예금보호 대상 금융상품의 `}</p>
                            <p className={styles.p}>
                                원금과 소정의 이자를 합하여 1인당 “최고 5천만원”이며,
                                5천만원을초과하는 나머지 금액은 보호하지 않습니다.
                            </p>
                        </div>
                    </div>
                </div>
		    </div>
		</div>
	);
};

export default ProductDetail;
