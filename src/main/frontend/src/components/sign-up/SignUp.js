import axios from 'axios';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import RadioGroup from './component/RadioGroup';
import Radio from './component/Radio';
import SelectBox from './component/SelectBox';
import DaumPostcode from 'react-daum-postcode';

import styles from "./SignUp.module.css";

// 우편번호 API CSS
const postCodeStyle = {
    position: 'absolute',
    top: '50px',
    height: '590px',
    width: '100%',
};

const SignUp = () => {
    const navigate = useNavigate();

    // 이메일
    const [isEmailValid, setIsEmailValid] = useState(false);
    const [inputEmail, setInputEmail] = useState('');
    const [emailCheck, setEmailCheck] = useState(false);
    const [emailCheckText, setEmailCheckText] = useState('이메일 중복체크를 클릭해주세요.');
    const [emailCheckTextColor, setEmailCheckTextColor] = useState('');

    // 비밀번호
    const [isPwValid, setIsPwValid] = useState(false);
    const [inputPw, setInputPw] = useState('');
    const [pwCheck, setPwCheck] = useState(false);
    const [pwCheckText, setPwCheckText] = useState('비밀번호를 입력해주세요.');
    const [pwCheckTextColor, setPwCheckTextColor] = useState('');

    // 비밀번호 확인
    const [isPwCfValid, setIsPwCfValid] = useState(false);
    const [inputPwCf, setInputPwCf] = useState('');
    const [pwCfCheck, setPwCfCheck] = useState(false);
    const [pwCfCheckText, setPwCfCheckText] = useState('비밀번호 확인을 입력해주세요.');
    const [pwCfCheckTextColor, setPwCfCheckTextColor] = useState('');

    // 이름
    const [isNmValid, setIsNmValid] = useState(false);
    const [inputNm, setInputNm] = useState('');
    const [nmCheck, setNmCheck] = useState(false);
    const [nmCheckText, setNmCheckText] = useState('성명을 입력해주세요.');
    const [nmCheckTextColor, setNmCheckTextColor] = useState('');

    // 나이
    const [isAgeValid, setIsAgeValid] = useState(false);
    const [inputAge, setInputAge] = useState('');
    const [ageCheck, setAgeCheck] = useState(false);
    const [ageCheckText, setAgeCheckText] = useState('나이를 선택해주세요.');
    const [ageCheckTextColor, setAgeCheckTextColor] = useState('');

    // 핸드폰 번호
    const [isPhoneValid, setIsPhoneValid] = useState(false);
    const [inputPhoneNumber, setInputPhoneNumber] = useState('');
    const [phoneCheck, setPhoneCheck] = useState(false);
    const [phoneCheckText, setPhoneCheckText] = useState('핸드폰 번호를 입력해주세요.');
    const [phoneCheckTextColor, setPhoneCheckTextColor] = useState('');

    // 성별
    const [inputGender, setInputGender] = useState('M');
    const [inputProductRecommend, setInputProductRecommend] = useState('IC');
    const [inputJobCategory, setInputJobCategory] = useState('');
    const [openPostcode, setOpenPostcode] = useState(false);
    const [inputPostcode, setInputPostcode] = useState('');
    const [inputAddress, setInputAddress] = useState('');
    const [inputAddressDetail, setInputAddressDetail] = useState('');
    const [inputPolicyCheck, setInputPolicyCheck] = useState(false);

    // 직업군
    const JobCategoryOptions = [
        { value: "", name: "직업군을 선택해주세요." },
    	{ value: "2", name: "전문가 및 관련 종사자" },
    	{ value: "3", name: "사무 종사자" },
    	{ value: "4", name: "서비스 종사자" },
    	{ value: "5", name: "판매 종사자" },
    	{ value: "6", name: "농림/어업 숙련종사자" },
    	{ value: "7", name: "기능원 및 관련 기능 종사자" },
    	{ value: "8", name: "장치/기계 조작 및 조립 종사자" },
    	{ value: "9", name: "단순노무 종사자" },
    	{ value: "A", name: "군인" },
    ];

    // 이메일 체크 초기화
    const initEmailCheck = () => {
        setIsEmailValid(false);
        setEmailCheck(false);
        setEmailCheckText('이메일 중복체크를 클릭해주세요.');
        setEmailCheckTextColor('');
    };

    // 이메일 중복체크
    const checkDuplicateEmail = async (email) => {
        return await axios.get('/api/sign/emailCheck', {
            params: {
                email: email
            }
        }).then(function(res) {
            return res;
        }).catch(function(error){
            throw error;
        });
    };

    // 이메일 체크
    const checkEmail = async (email, change) => {
        setIsEmailValid(false);

        // 이메일 형식이 맞지 않을 경우
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(!emailRegex.test(email)){
            setEmailCheck(true);
            setEmailCheckText('이메일 형식이 맞지 않습니다. 다시 입력해 주세요.');
            setEmailCheckTextColor('var(--color-red-100)');
            return;
        }

        // 이미 가입된 이메일일 경우
        const checkDuplicateEmailRes = await checkDuplicateEmail(email);
        if(checkDuplicateEmailRes.data !== false){
            setEmailCheck(true);
            setEmailCheckText('이미 가입된 이메일 주소입니다. 다른 이메일 주소를 사용해주세요.');
            setEmailCheckTextColor('var(--color-red-100)');
        }
        // 사용 가능한 이메일일 경우
        else {
            setIsEmailValid(true);
            setEmailCheck(true);
            setEmailCheckText('사용 가능한 이메일 주소입니다.');
            setEmailCheckTextColor('#2a66ff');
        }

    };

    // 비밀번호 체크
    const checkPw = (pw, change) => {
        setIsPwValid(false);

        // 비밀번호 형식이 맞지 않을 경우
        const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{9,16}$/;
        if(!passwordRegex.test(pw)){
            setPwCheck(true);
            setPwCheckText('비밀번호를 다시 입력해 주세요.');
            setPwCheckTextColor('var(--color-red-100)');
        }
        // 사용 가능한 비밀번호일 경우
        else {
            setIsPwValid(true);
            setPwCheck(true);
            setPwCheckText('사용 가능한 비밀번호입니다.');
            setPwCheckTextColor('#2a66ff');
        }
    };

    // 비밀번호 확인 체크
    const checkPwCf = (pw, originPw, change) => {
        setIsPwCfValid(false);

        // 비밀번호가 일치하지 않을 경우
        if(pw !== originPw){
            setPwCfCheck(true);
            setPwCfCheckText('비밀번호가 일치하지 않습니다.');
            setPwCfCheckTextColor('var(--color-red-100)');
        }
        // 비밀번호가 일치할 경우
        else {
            setIsPwCfValid(true);
            setPwCfCheck(true);
            setPwCfCheckText('');
            setPwCfCheckTextColor('');
        }
    };

    // 이름 체크
    const checkNm = (nm, change) => {
        setIsNmValid(false);

        // 이름 형식이 맞지 않을 경우
        if(nm.length === 0){
            setNmCheck(true);
            setNmCheckText('성명을 다시 입력해주세요.');
            setNmCheckTextColor('var(--color-red-100)');
        }
        // 올바른 이름일 경우
        else {
            setIsNmValid(true);
            setNmCheck(true);
            setNmCheckText('');
            setNmCheckTextColor('');
        }
    };

    // 나이 체크
    const checkAge = (age, change) => {
        setIsAgeValid(false);

        // 나이가 올바르지 않을 경우
        if(age === '' || age === 0 || age >= 1000){
            setAgeCheck(true);
            setAgeCheckText('나이를 다시 입력해주세요.');
            setAgeCheckTextColor('var(--color-red-100)');
        }
        // 올바른 나이일 경우
        else {
            setIsAgeValid(true);
            setAgeCheck(true);
            setAgeCheckText('');
            setAgeCheckTextColor('');
        }
    };

    // 핸드폰 번호 체크
    const checkPhone = (phone, change) => {
        setIsPhoneValid(false);

        // 핸드폰 번호 형식이 맞지 않을 경우
        const phoneRegex =  /^(010)[0-9]{3,4}[0-9]{4}$/;
        if(!phoneRegex.test(phone)){
            setPhoneCheck(true);
            setPhoneCheckText('핸드폰 번호를 다시 입력해 주세요.');
            setPhoneCheckTextColor('var(--color-red-100)');
        }
        // 올바른 핸드폰 번호일 경우
        else {
            setIsPhoneValid(true);
            setPhoneCheck(true);
            setPhoneCheckText('');
            setPhoneCheckTextColor('');
        }
    };

    // 주소
    const postCodeHandle = {
        // 버튼 클릭 이벤트
        clickButton: () => {
            setOpenPostcode(true);
        },
        // 닫기 버튼 클릭 이벤트
        clickCloseButton: () => {
            setOpenPostcode(false);
        },
        // 주소 선택 이벤트
        selectAddress: (data: any) => {
            setInputPostcode(data.zonecode);
            setInputAddress(data.address);
            setOpenPostcode(false);
        },
    };

    // 회원가입
    const onClickSignUp = () => {

        if(!isEmailValid){
            alert(emailCheckText);
        }
        else if(!isPwValid){
            alert(pwCheckText);
        }
        else if(!isPwCfValid){
            alert(pwCfCheckText);
        }
        else if(!isNmValid){
            alert(nmCheckText);
        }
        else if(!isAgeValid){
            alert(ageCheckText);
        }
        else if(inputJobCategory === ''){
            alert('직업군을 선택해주세요.');
        }
        else if(!isPhoneValid){
            alert(phoneCheckText);
        }
        else if(inputPostcode === '' || inputAddress === ''){
            alert('주소를 입력해주세요.');
        }
        else if(inputAddressDetail === ''){
            alert('상세주소를 입력해주세요.');
        }
        else if(!inputPolicyCheck){
            alert('개인정보 제공 및 수집에 동의해주세요.');
        }
        // 회원가입
        else {
            axios.post('/api/sign/join', {
                email: inputEmail,
                loginId: inputEmail,
                password: inputPw,
                userNm: inputNm,
                age: inputAge,
                ageCd: Math.floor(inputAge / 10) * 10,
                mobileTelNum: inputPhoneNumber,
                sexCd: inputGender,
                prdt_rcmd_item_cd: inputProductRecommend,
                occupationCd: inputJobCategory,
                zipCd: inputPostcode,
                zipDetailAddr1: inputAddress,
                zipDetailAddr2: inputAddressDetail,
                indvdlinfoAgreeYn: 'Y',
                profileImgUrl: null,
            }).then(function (res) {
                alert('회원가입이 완료되었습니다.');
                navigate('/login');
            }).catch(function (error) {
               alert(error.response.data.message);
            });

        }

    };

    useEffect(() => {
    }, []);

	return (
		<div className={styles.member2}>
			<div className={styles.back}>
                <img
                    className={styles.bultIcon}
                    alt=""
                    src="/bult.svg"
                    onClick={()=>navigate("/main")}
                />
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
                    onChange={(e) => {
                        setInputEmail(e.target.value);
                        initEmailCheck();
                    }}
                />
                {/*
                <div className={styles.mfdbcom}>@mfdb.com</div>
                */}
                <div
                    className={styles.inBox1}
                    onClick={() => checkEmail(inputEmail, true)}
                >
                    <b className={styles.b}>중복체크</b>
                </div>
                {emailCheck && <div
                    style={{ color: emailCheckTextColor }}
                    className={styles.div1}
                >
                    {emailCheckText}
                </div>}
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
                    onBlur={() => checkPw(inputPw, true)}
                />
                {pwCheck && <div
                    style={{ color: pwCheckTextColor }}
                    className={styles.div4}
                >
                    {pwCheckText}
                </div>}
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
                    onBlur={() => checkPwCf(inputPwCf, inputPw, true)}
                />
                {pwCfCheck && <div
                    style={{ color: pwCfCheckTextColor }}
                    className={styles.div7}
                >
                    {pwCfCheckText}
                </div>}
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
                    onBlur={() => checkNm(inputNm, true)}
                />
                {nmCheck && <div
                    style={{ color: nmCheckTextColor }}
                    className={styles.div9}
                >
                    {nmCheckText}
                </div>}
                <div className={styles.div10}>
                    <span>{`성별 `}</span>
                    <span className={styles.span}>*</span>
                </div>
                <RadioGroup
                    className={styles.genderRadioGroup}
                    value={inputGender}
                    onChange={setInputGender}
                >
                    <Radio radioLabelStyle={styles.genderRadioLabel} value="M">남성</Radio>
                    <Radio value="F">여성</Radio>
                </RadioGroup>
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
                    onBlur={() => checkAge(inputAge, true)}
                />
                <div className={styles.div14}>(만)</div>
                {ageCheck && <div
                    style={{ color: ageCheckTextColor }}
                    className={styles.div15}
                >
                    {ageCheckText}
                </div>}
                <div className={styles.div16}>
                    <span>{`직업군 `}</span>
                    <span className={styles.span}>*</span>
                </div>
                <SelectBox
                    selectWrapperStyle={styles.jobCategorySelectWrapper}
                    options={JobCategoryOptions}
                    defaultValue={inputJobCategory}
                    onChange={(e) => setInputJobCategory(e.target.value)}
                />
                <div className={styles.div18}>
                    <span>{`핸드폰 번호 `}</span>
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
                    onBlur={() => checkPhone(inputPhoneNumber, true)}
                />
                {phoneCheck && <div
                    style={{ color: phoneCheckTextColor }}
                    className={styles.div19}
                >
                    {phoneCheckText}
                </div>}
                <div className={styles.div20}>
                    <span>{`주소 `}</span>
                    <span className={styles.span}>*</span>
                </div>
                <input
                    className={styles.inBox8}
                    type="text"
                    id="postcode"
                    name="postcode"
                    value={inputPostcode}
                    onChange={(e) => setInputPostcode(e.target.value)}
                    disabled
                />
                <div
                    className={styles.btnBox}
                    onClick={postCodeHandle.clickButton}
                >
                    <b className={styles.b1}>우편번호</b>
                </div>
                <input
                    className={styles.inBox9}
                    type="text"
                    id="address"
                    name="address"
                    value={inputAddress}
                    onChange={(e) => setInputAddress(e.target.value)}
                    disabled
                />
                <input
                    className={styles.inBox10}
                    type="text"
                    id="addressDetail"
                    name="addressDetail"
                    placeholder="상세주소를 입력해 주세요."
                    value={inputAddressDetail}
                    onChange={(e) => setInputAddressDetail(e.target.value)}
                />
                <div className={styles.div22}>
                    <span>{`상품 추천 선택 항목 `}</span>
                    <span className={styles.span}>{`* `}</span>
                </div>
                <RadioGroup
                    className={styles.productRecommendRadioGroup}
                    value={inputProductRecommend}
                    onChange={setInputProductRecommend}
                >
                    <Radio radioLabelStyle={styles.productRecommendRadioLabel} value="IC">소득별</Radio>
                    <Radio radioLabelStyle={styles.productRecommendRadioLabel} value="CS">소비유형별</Radio>
                    <Radio value="AG">연령별</Radio>
                </RadioGroup>
                <label>
                    <input
                        className={styles.chBox}
                        id="policyCheck"
                        type="checkbox"
                        checked={inputPolicyCheck}
                        onChange={(e) => {
                            setInputPolicyCheck(e.target.checked);
                        }}
                    />
                    <div className={styles.div26}>개인정보 제공 및 수집에 동의합니다.</div>
                </label>
                <button
                    className={styles.btnBox1}
                    onClick={(e) => {
                        onClickSignUp();
                    }}
                >
                    <b className={styles.b2}>회원가입</b>
                </button>
            </div>
            {openPostcode &&
                <div className={styles.outerPostCodeDiv}>
                    <div className={styles.closeButtonDiv}>
                        <img
                            className={styles.clsIcoIcon}
                            alt=""
                            src="/clsico.svg"
                            onClick={postCodeHandle.clickCloseButton}
                        />
                    </div>
                    <DaumPostcode
                        style={postCodeStyle}
                        onComplete={postCodeHandle.selectAddress}
                        autoClose={false}
                    />
                </div>
            }
		</div>
	);
};

export default SignUp;
