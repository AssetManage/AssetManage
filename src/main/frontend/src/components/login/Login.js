import axios from 'axios';
import { useState, useEffect } from 'react';

import { useNavigate } from 'react-router-dom';

import styles from './Login.module.css';

const Login = () => {
    const [inputEmail, setInputEmail] = useState('');
    const [inputPw, setInputPw] = useState('');
    const navigate = useNavigate();

    const onClickLogin = (email, pw) => {
        if(email === ''){
            alert('이메일을 입력해 주세요.');
            return;
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(!emailRegex.test(email)){
            alert('이메일 및 비밀번호가 일치하지 않습니다.');
            return;
        }

        if(pw === ''){
            alert('비밀번호를 입력해 주세요.');
            return;
        }

        const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{9,16}$/;
        if(!passwordRegex.test(pw)){
            alert('이메일 및 비밀번호가 일치하지 않습니다.');
            return;
        }

        axios.post('/api/sign/login', {
            email: email,
            password: pw
        }).then(function (res) {
            localStorage.setItem('accessToken', res.data.token);
            axios.defaults.headers.common['Authorization'] = res.data.token;

            navigate('/');
        }).catch(function (error) {
           alert(error.response.data.message);
        });
    };

    useEffect(() => {

    }, []);

    return (
        <div className={styles.login}>
            <div className={styles.login1}>
                <form>
                    <div className={styles.login2}>Login</div>
                    <div className={styles.myFinancialPocket}>My Financial Pocket</div>
                    <label className={styles.email} htmlFor="email">Email</label>
                    <input
                        className={styles.email1}
                        type="email"
                        id="email"
                        name="email"
                        placeholder="Email 주소를 입력해 주세요."
                        value={inputEmail}
                        onChange={(e) => setInputEmail(e.target.value)}
                    />
                    <label className={styles.password} htmlFor="password">Password</label>
                    <input
                        className={styles.password1}
                        type="password"
                        id="password"
                        name="password"
                        placeholder="영문, 숫자, 특수기호 9~16 자리를 입력해 주세요."
                        value={inputPw}
                        onChange={(e) => setInputPw(e.target.value)}
                    />
                    {false&&<div className={styles.chBox} />}
                    {false&&<div className={styles.div1}>로그인 상태 유지</div>}
                    <button
                        className={styles.btnBox}
                        onClick={(e) => {
                            e.preventDefault();
                            onClickLogin(inputEmail, inputPw)
                        }}
                    >
                        <b className={styles.b}>로그인</b>
                    </button>
                    {false&&<div className={styles.div2}>비밀번호 찾기</div>}
                    {false&&<div className={styles.div3}>계정찾기</div>}
                    <div
                        className={styles.div4}
                        onClick={(e) => {
                            navigate('/sign-up');
                        }}
                    >회원가입</div>
                    <div className={styles.loginChild} />
                    <div className={styles.ellipse} />
                    <img className={styles.icon} alt="" src="/003-1@2x.png" />
                    <img className={styles.icon1} alt="" src="/003-2@2x.png" />
                    <img className={styles.icon2} alt="" src="/003-3@2x.png" />
                    <img className={styles.icon3} alt="" src="/00-1@2x.png" />
                </form>
            </div>
        </div>
    );
};

export default Login;
