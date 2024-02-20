import axios from 'axios';
import { useState, useEffect } from 'react';

import styles from "./SignUp.module.css";

const SignUp = () => {
    const [inputEmail, setInputEmail] = useState('');
    const [inputPw, setInputPw] = useState('');
    const [inputPwCf, setInputPwCf] = useState('');
    const [inputNm, setInputNm] = useState('');
    const [inputAge, setInputAge] = useState('');
    const [inputPhoneNumber, setInputPhoneNumber] = useState('');

	return (
		<div className={styles.member2}>
			<div className={styles.back}>
                <img className={styles.bultIcon} alt="" src="/bult.svg" />
                <div className={styles.div}>회원가입</div>
                <label className={styles.email} htmlFor="email">
                    <span>{`Email `}</span>
                    <span className={styles.span}>*</span>
                </label>
                <input
                    className={styles.email1}
                    type="email"
                    id="email"
                    name="email"
                    placeholder="Email 주소를 입력해 주세요."
                    value={inputEmail}
                    maxlength="25"
                    onChange={(e) => setInputEmail(e.target.value)}
                />
                <div className={styles.mfdbcom}>@mfdb.com</div>
                <div className={styles.inBox1}>
                    <b className={styles.b}>중복체크</b>
                </div>
                <div className={styles.div1}>이메일을 다시 입력해 주세요.</div>
                <label className={styles.div2} htmlFor="password">
                    <span>{`비밀번호 `}</span>
                    <span className={styles.span}>*</span>
                </label>
                <input
                    className={styles.password}
                    type="password"
                    id="password"
                    name="password"
                    placeholder="영문, 숫자, 특수기호 9~16 자리를 입력해 주세요."
                    value={inputPw}
                    onChange={(e) => setInputPw(e.target.value)}
                />
                <div className={styles.div4}>사용 가능한 비밀번호 입니다.</div>
                <label className={styles.div5} htmlFor="passwordConfirm">
                    <span>{`비밀번호 확인 `}</span>
                    <span className={styles.span}>*</span>
                </label>
                <input
                    className={styles.inBox3}
                    type="password"
                    id="passwordConfirm"
                    name="passwordConfirm"
                    placeholder="비밀번호를 다시 입력해 주세요."
                    value={inputPwCf}
                    onChange={(e) => setInputPwCf(e.target.value)}
                />
                <div className={styles.div7}>비밀번호가 일치하지 않습니다.</div>
                <label className={styles.div8} htmlFor="name">
                    <span>{`성명 `}</span>
                    <span className={styles.span}>*</span>
                </label>
                <input
                    className={styles.inBox4}
                    type="text"
                    id="name"
                    name="name"
                    value={inputNm}
                    onChange={(e) => setInputNm(e.target.value)}
                />
                <div className={styles.div9}>성명을 다시 입력해 주세요.</div>
                <div className={styles.div10}>
                    <span>{`성별 `}</span>
                    <span className={styles.span}>*</span>
                </div>
                <div className={styles.radioIco} />
                <div className={styles.radioIco1} />
                <div className={styles.div11}>남성</div>
                <div className={styles.radioIco2} />
                <div className={styles.div12}>여성</div>
                <label className={styles.div13} htmlFor="age">
                    <span>나이</span>
                    <span className={styles.span}>*</span>
                </label>
                <input
                    className={styles.inBox5}
                    type="number"
                    id="age"
                    name="age"
                    value={inputAge}
                    onChange={(e) => setInputAge(e.target.value)}
                />
                <div className={styles.div14}>(만)</div>
                <div className={styles.div15}>나이를 다시 입력해 주세요.</div>
                <div className={styles.div16}>
                    <span>{`직업군 `}</span>
                    <span className={styles.span}>*</span>
                </div>
                <div className={styles.inBox6} />
                <div className={styles.div17}>선택하세요.</div>
                <img className={styles.member2Child} alt="" src="/polygon-2.svg" />
                <div className={styles.div18}>
                    <span>{`휴대번호 `}</span>
                    <span className={styles.span}>*</span>
                </div>
                <input
                    className={styles.inBox7}
                    type="number"
                    id="phoneNumber"
                    name="phoneNumber"
                    placeholder="‘-’ 없이 입력해주세요. (ex) 01012341234"
                    value={inputPhoneNumber}
                    onChange={(e) => setInputPhoneNumber(e.target.value)}
                />
                <div className={styles.div19}>나이를 다시 입력해 주세요.</div>
                <div className={styles.div20}>
                    <span>{`주소 `}</span>
                    <span className={styles.span}>*</span>
                </div>
                <div className={styles.inBox8} />
                <div className={styles.btnBox} />
                <b className={styles.b1}>우편번호</b>
                <div className={styles.inBox9} />
                <div className={styles.inBox10} />
                <div className={styles.div21}>상세주소를 입력해 주세요.</div>
                <div className={styles.div22}>
                    <span>{`상품 추천 선택 항목 `}</span>
                    <span className={styles.span}>{`* `}</span>
                </div>
                <div className={styles.radioIco3} />
                <div className={styles.radioIco4} />
                <div className={styles.div23}>소득별</div>
                <div className={styles.radioIco5} />
                <div className={styles.div24}>소비유형별</div>
                <div className={styles.radioIco6} />
                <div className={styles.div25}>연령별</div>
                <div className={styles.chBox} />
                <div className={styles.div26}>개인정보 제공 및 수집에 동의합니다.</div>
                <div className={styles.btnBox1} />
                <b className={styles.b2}>회원가입</b>
            </div>
		</div>
	);
};

export default SignUp;
