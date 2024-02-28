import styles from "./ProductRecmdPopup.module.css";

const ProductRecmdPopup = () => {
	return (
		<div className={styles.mainPop1}>
			<div className={styles.dimBox} />
			<div className={styles.frameParent}>
				<div className={styles.dimBoxWrapper}>
					<div className={styles.dimBox1} />
				</div>
				<div className={styles.frameWrapper}>
					<div className={styles.poptitWrapper}>
						<div className={styles.poptitWrapper}>
							<img className={styles.bultIcon} alt="" src="/bult.svg" />
							<div className={styles.div17}>나에게 맞는 예·적금 추천받기</div>
							<img className={styles.clsIcoIcon} alt="" src="/clsico.svg" />
							<div className={styles.wLine1} />
						</div>
					</div>
				</div>
				<div className={styles.div18}>
					고객님의 입력 정보로 연령대별 나에게 맞는 예·적금 상품을 추천받습니다.
				</div>
				<div className={styles.div19}>
					<div className={styles.div20}>{`성명 `}</div>
					<div className={styles.inBox1} />
				</div>
				<div className={styles.div21}>
					<div className={styles.div22}>연소득</div>
					<div className={styles.inBox2} />
					<div className={styles.div23}>연소득을 입력해 주세요.</div>
					<div className={styles.div24}>만원</div>
					<div className={styles.div25}>
						<span>※</span>
						<span className={styles.span27}>{` 정확한 결과를 위해 `}</span>
						<span>세전 연소득</span>
						<span className={styles.span27}>으로 입력해주세요.</span>
					</div>
				</div>
				<div className={styles.div26}>
					<div className={styles.div22}>저축비율</div>
					<div className={styles.inBox2} />
					<div className={styles.div23}>저축비율을 입력해 주세요.</div>
					<div className={styles.div24}>%</div>
				</div>
				<div className={styles.btn1}>
					<div className={styles.btnBox1} />
					<b className={styles.b8}>결과 확인</b>
				</div>
				<div className={styles.btn2}>
					<div className={styles.btnBox2} />
					<div className={styles.div30}>취소</div>
				</div>
			</div>
		</div>
	);
};

export default ProductRecmdPopup;
