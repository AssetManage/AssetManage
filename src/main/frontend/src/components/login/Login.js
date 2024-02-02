import styles from "./Login.module.css";

const Login = () => {
  return (
    <div className={styles.login}>
      <div className={styles.login1}>
      <div className={styles.login2}>Login</div>
      <div className={styles.myFinancialPocket}>My Financial Pocket</div>
      <div className={styles.email}>Email</div>
      <div className={styles.inBox} />
      <div className={styles.email1}>Email 주소를 입력해 주세요.</div>
      <div className={styles.password}>Password</div>
      <div className={styles.inBox1} />
      <div
        className={styles.div}
      >{`영문, 숫자, 특수기호 9~16 자리를 입력해 주세요. `}</div>
      <div className={styles.chBox} />
      <div className={styles.div1}>로그인 상태 유지</div>
      <div className={styles.btnBox} />
      <b className={styles.b}>로그인</b>
      <div className={styles.div2}>비밀번호 찾기</div>
      <div className={styles.div3}>계정찾기</div>
      <div className={styles.div4}>회원가입</div>
      <div className={styles.loginChild} />
      <div className={styles.ellipse} />
      <img className={styles.icon} alt="" src="/003-1@2x.png" />
      <img className={styles.icon1} alt="" src="/003-2@2x.png" />
      <img className={styles.icon2} alt="" src="/003-3@2x.png" />
      <img className={styles.icon3} alt="" src="/00-1@2x.png" />
      </div>
    </div>
  );
};

export default Login;
