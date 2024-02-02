import styles from "./SignUp.module.css";

const SignUp = () => {
  return (
    <div className={styles.member2}>
      <div className={styles.back}>
          <div className={styles.chBox} />
          <div className={styles.div}>회원가입</div>
          <div className={styles.email}>
            <span>{`Email `}</span>
            <span className={styles.span}>*</span>
          </div>
          <div className={styles.div1}>
            <span>{`비밀번호 `}</span>
            <span className={styles.span}>*</span>
          </div>
          <div className={styles.div2}>
            <span>{`비밀번호 확인 `}</span>
            <span className={styles.span}>*</span>
          </div>
          <div className={styles.div3}>
            <span>{`성명 `}</span>
            <span className={styles.span}>*</span>
          </div>
          <div className={styles.div4}>{`상품 추천 선택 항목 `}</div>
          <div className={styles.div5}>개인정보 제공 및 수집에 동의합니다.</div>
          <div className={styles.div6}>
            ※ 미선택 시 소득별을 기본으로 상품이 추천됩니다.
          </div>
          <div className={styles.div7}>이메일을 다시 입력해 주세요.</div>
          <div className={styles.div8}>사용 가능한 비밀번호 입니다.</div>
          <div className={styles.div9}>비밀번호가 일치하지 않습니다.</div>
          <div className={styles.div10}>성명을 다시 입력해 주세요.</div>
          <div className={styles.div11}>소득별</div>
          <div className={styles.div12}>소비별</div>
          <div className={styles.div13}>연령별</div>
          <div className={styles.inBox} />
          <div className={styles.inBox1} />
          <div className={styles.inBox2} />
          <div className={styles.inBox3} />
          <div className={styles.inBox4} />
          <div className={styles.mfdbcom}>@mfdb.com</div>
          <b className={styles.b}>중복체크</b>
          <div className={styles.email1}>Email 주소를 입력해 주세요.</div>
          <div className={styles.div14}>
            영문, 숫자, 특수기호 9~16 자리를 입력해 주세요.
          </div>
          <div className={styles.div15}>비밀번호를 다시 입력해 주세요.</div>
          <div className={styles.btnBox} />
          <b className={styles.b1}>회원가입</b>
          <div className={styles.radioIco} />
          <div className={styles.radioIco1} />
          <div className={styles.radioIco2} />
          <div className={styles.radioIco3} />
          <img className={styles.bultIcon} alt="" src="/bult.svg" />
      </div>
    </div>
  );
};

export default SignUp;
