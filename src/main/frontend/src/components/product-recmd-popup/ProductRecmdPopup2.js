import styles from "./ProductRecmdPopup2.module.css";

const ProductRecmdPopup2 = () => {
  return (
    <div className={styles.mainPop2}>
      <div className={styles.dimBox} />
      <div className={styles.dimBoxParent}>
        <div className={styles.dimBox1} />
        <div className={styles.poptit}>
          <img className={styles.bultIcon} alt="" src="/bult.svg" />
          <div className={styles.div}>나에게 맞는 예적금 추천받기</div>
          <div className={styles.wLine} />
          <img className={styles.clsIcoIcon} alt="" src="/clsico.svg" />
        </div>
        <img className={styles.vsImgIcon} alt="" src="/vsimg.svg" />
        <div className={styles.stit}>
          <div className={styles.div1}>나의 소비유형은</div>
          <b className={styles.b}>자린고비형</b>
          <div className={styles.youOnlyLive}>“You Only Live Once”</div>
          <div className={styles.div2}>
            한번뿐인 인생! 모든 내가 원하는대로!!
          </div>
        </div>
        <div className={styles.div3}>나에게 맞는 예·적금 상품</div>
        <div className={styles.inBox} />
        <div className={styles.shinhanIcoParent}>
          <img
            className={styles.shinhanIcoIcon}
            alt=""
            src="/bank-icons/shinhanico@2x.png"
          />
          <div className={styles.sTit}>
            <div className={styles.div4}>신한은행</div>
            <div className={styles.div5}>더드림 정기예금</div>
          </div>
        </div>
        <div className={styles.groupParent}>
          <div className={styles.parent}>
            <div className={styles.div6}>최고</div>
            <div className={styles.div7}>
              <b>3.7</b>
              <span className={styles.span}>%</span>
            </div>
          </div>
          <div className={styles.div8}>(12개월 / 세금공제전/ 비대면)</div>
        </div>
        <div className={styles.btn1}>
          <div className={styles.btnBox} />
          <b className={styles.b1}>더 많 예·적금 상품 추천받기</b>
        </div>
        <div className={styles.btn2}>
          <div className={styles.btnBox1} />
          <div className={styles.div9}>취소</div>
        </div>
      </div>
    </div>
  );
};

export default ProductRecmdPopup2;
