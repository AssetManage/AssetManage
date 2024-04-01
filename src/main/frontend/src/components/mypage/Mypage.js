import axios from 'axios';
import { useState, useEffect, useRef } from 'react';
import { useNavigate } from 'react-router-dom';

import Header from '../common/header/Header';
import RadioGroup from './component/RadioGroup';
import Radio from './component/Radio';
import SelectBox from './component/SelectBox';
import DaumPostcode from 'react-daum-postcode';

import styles from "./Mypage.module.css";

// 우편번호 API CSS
const postCodeStyle = {
    position: 'absolute',
    top: '50px',
    height: '590px',
    width: '100%',
};

const Mypage = () => {
    const navigate = useNavigate(0);

    // 회원 순번
    const [inputUserSeq, setInputUserSeq] = useState('');

    // 사진
    const uploadImgForm = useRef();
    const [uploadImgFile, setUploadImgFile] = useState('');
    const [uploadImgUrl, setUploadImgUrl] = useState('');
    const [isImageExist, setIsImageExist] = useState(false);

    // 이메일
    const [inputEmail, setInputEmail] = useState('');

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

    // 직업군
    const [jobCategoryOptions, setJobCategoryOptions] = useState([]);

    // 성별
    const [genderOptions, setGenderOptions] = useState([]);

    // 상품 추천 선택 항목
    const [productRecommendOptions, setProductRecommendOptions] = useState([]);

    // 사진 업로드
    const onchangeImageUpload = (e) => {
         const {files} = e.target;
         const uploadFile = files[0];
         setUploadImgFile(uploadFile);
         e.target.value = '';

         const reader = new FileReader();
         reader.readAsDataURL(uploadFile);
         reader.onloadend = ()=> {
            setUploadImgUrl(reader.result);
         }
    };

    // 비밀번호 체크
    const checkPw = (pw) => {
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
    const checkPwCf = (pw, originPw) => {
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
    const checkNm = (nm) => {
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
    const checkAge = (age) => {
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
    const checkPhone = (phone) => {
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

    // 재진단 버튼
    const reDiagnosisBtn = () => {

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

    // 회원정보 수정
    const onClickUpdate = () => {
        checkPw(inputPw);
        checkPwCf(inputPw, inputPw);
        checkNm(inputNm);
        checkAge(inputAge);
        checkPhone(inputPhoneNumber);

        // 유효성 체크
        if(inputUserSeq === ''){
            alert('로그인을 해주세요.');
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
        // 회원정보 수정
        else {
            axios.post('/user/update', {
                userSeq: inputUserSeq,
                userNm: inputNm,
                loginId: inputEmail,
                loginPw: inputPw,
                ageCd: Math.floor(inputAge / 10) * 10,
                profileImgUrl: null,
                sexCd: inputGender,
                age: inputAge,
                occupationCd: inputJobCategory,
                mobileTelNum: inputPhoneNumber,
                zipCd: inputPostcode,
                zipDetailAddr1: inputAddress,
                zipDetailAddr2: inputAddressDetail,
                prdtRcmdItemCd: inputProductRecommend
            }, {
                headers: {
                    Authorization: localStorage.getItem('accessToken'),
                },
            }).then(function (res) {
                alert('회원 수정이 완료되었습니다.');
                window.location.reload(true);
            }).catch(function (error) {
                console.log(error);
                alert(error?.response?.data);
            });

        }

    };

    // 페이지가 로드될 때
	useEffect(() => {
		const onStart = async () => {
		    getUserInfo();
		    getOccupationOptions();
		    getGenderOptions();
		    getProductRecommendOptions();
		};

		onStart();
	}, []);

	// 회원 정보 가져오기
	const getUserInfo = async () => {
        const response = await axios
            .get('/user/selectUserAll', {
                headers: {
                    Authorization: localStorage.getItem('accessToken'),
                },
            })
            .then(function (res) {
                return res;
            })
            .catch(function (error) {
                return error;
            });

        // 비로그인 상태
        if(response?.data?.stat !== 'SUCCESS'){
            alert('로그인을 해주세요.');
            navigate('/main');
        }
        // 로그인 상태
        else{
            setInputEmail(response?.data?.data?.email);
            setInputUserSeq(response?.data?.data?.userSeq);
            setInputPw(response?.data?.data?.loginPw);
            setInputPwCf(response?.data?.data?.loginPw);
            setInputNm(response?.data?.data?.userNm);
            setInputGender(response?.data?.data?.sexCd);
            setInputAge(response?.data?.data?.age);
            setInputJobCategory(response?.data?.data?.occupationCd);
            setInputPhoneNumber(response?.data?.data?.mobileTelNum);
            setInputPostcode(response?.data?.data?.zipCd);
            setInputAddress(response?.data?.data?.zipDetailAddr1);
            setInputAddressDetail(response?.data?.data?.zipDetailAddr2);
            setInputProductRecommend(response?.data?.data?.prdtRcmdItemCd);

            if(response?.data?.data?.profileImgUrl !== '' && response?.data?.data?.profileImgUrl !== null){
                setUploadImgUrl(response?.data?.data?.profileImgUrl);
            }
        }
	};

    // 직업군 옵션 세팅
    const getOccupationOptions = async () => {
			const response = await axios
				.get('/st/code/selectCodeList', {
				    params: {
				        groupCode : 'occupation_cd'
				    },
				})
				.then(function (res) {
					return res;
				})
				.catch(function (error) {
					return error;
				});

            setJobCategoryOptions([{ value: "", name: "직업군을 선택해주세요." }]);

			if(response?.data?.stat === 'SUCCESS'){

                response?.data?.list?.forEach((obj) => {
                    setJobCategoryOptions(jobCategoryOptions => [...jobCategoryOptions, { value: obj.codeId, name: obj.codeNm }]);
                });
			}
    };

    // 성별 옵션 세팅
    const getGenderOptions = async () => {
			const response = await axios
				.get('/st/code/selectCodeList', {
				    params: {
				        groupCode : 'sex_cd'
				    },
				})
				.then(function (res) {
					return res;
				})
				.catch(function (error) {
					return error;
				});

            setGenderOptions([]);

			if(response?.data?.stat === 'SUCCESS'){

                response?.data?.list?.forEach((obj) => {
                    setGenderOptions(genderOptions => [...genderOptions, { value: obj.codeId, name: obj.codeNm }]);
                });

			}
    };

    // 상품 추천 선택 항목
    const getProductRecommendOptions = async () => {
            const response = await axios
                .get('/st/code/selectCodeList', {
                    params: {
                        groupCode : 'prdt_rcmd_item_cd'
                    },
                })
                .then(function (res) {
                    return res;
                })
                .catch(function (error) {
                    return error;
                });

            setProductRecommendOptions([]);

            if(response?.data?.stat === 'SUCCESS'){

                response?.data?.list?.forEach((obj) => {
                    setProductRecommendOptions(productRecommendOptions => [...productRecommendOptions, { value: obj.codeId, name: obj.codeNm }]);
                });

            }
    };

    // 사진이 있으면 디폴트 사진 안 보이게
    useEffect(() => {
        if(uploadImgUrl !== ''){
            setIsImageExist(true);
        } else{
            setIsImageExist(false);
        }
    }, [uploadImgUrl]);

	return (
		<div className={styles.mypage}>
			<div className={styles.mypageInner}>
				<div className={styles.frameParent}>
					<div className={styles.headerWrapper}>
                        <Header/>
					</div>
					<div className={styles.titParent}>
						<div className={styles.tit}>
							<div className={styles.div1}>마이페이지</div>
						</div>
                        {isImageExist && <img
                            className={styles.proImageIcon}
                            src={uploadImgUrl}
                            img="img"
                        />}
						{isImageExist && <img
						    className={styles.xIcon}
                            alt=""
                            src="/xIcon.png"
                            onClick={() => { setUploadImgUrl(''); }}
                        />}
						{!isImageExist && <img
						    className={styles.noImageIcon}
						    alt=""
						    src="/noimage.png"
						    onClick={() => { uploadImgForm.current.click() }}
                        />}
                        <input
                            style={{ display: 'none'}}
                            ref={uploadImgForm}
                            type="file"
                            accept="image/*"
                            onChange={onchangeImageUpload}
                        />
						<div className={styles.div2}>
							<span>소비유형</span>
							<span className={styles.span}> : 하루살이형</span>
						</div>
						<div
						    className={styles.btn1}
						    onClick={reDiagnosisBtn}
                        >
							<div className={styles.inBox} />
							<b className={styles.b}>{`재진단 `}</b>
						</div>
                        <label className={styles.email} htmlFor="email">
                            <span>{`Email `}</span>
                        </label>
						<div className={styles.inBoxParent}>
                            <input
                                className={styles.inBox1}
                                type="email"
                                id="email"
                                name="email"
                                placeholder="Email 주소를 입력해 주세요."
                                value={inputEmail}
                                onChange={(e) => {}}
                                disabled
                            />
                        </div>
                        <label className={styles.div3} htmlFor="password">
                            <span>{`비밀번호 `}</span>
                        </label>
						<div className={styles.inBoxGroup}>
                            <input
                                className={styles.inBox2}
                                type="password"
                                id="password"
                                name="password"
                                placeholder="영문, 숫자, 특수기호 9~16 자리를 입력해 주세요."
                                value={inputPw}
                                onChange={(e) => setInputPw(e.target.value)}
                                onBlur={() => checkPw(inputPw)}
                            />
						</div>
                        {pwCheck && <div
                            style={{ color: pwCheckTextColor }}
                            className={styles.div5}
                        >
                            {pwCheckText}
                        </div>}
                        <label className={styles.div6} htmlFor="passwordConfirm">
                            <span>{`비밀번호 확인 `}</span>
                        </label>
						<div className={styles.inBoxContainer}>
                            <input
                                className={styles.inBox2}
                                type="password"
                                id="passwordConfirm"
                                name="passwordConfirm"
                                placeholder="비밀번호를 다시 입력해 주세요."
                                value={inputPwCf}
                                onChange={(e) => setInputPwCf(e.target.value)}
                                onBlur={() => checkPwCf(inputPwCf, inputPw)}
                            />
						</div>
                        {pwCfCheck && <div
                            style={{ color: pwCfCheckTextColor }}
                            className={styles.text}
                        >
                            {pwCfCheckText}
                        </div>}
                        <label className={styles.div9} htmlFor="name">
                            <span>{`성명 `}</span>
                        </label>
                        <input
                            className={styles.inBox4}
                            type="text"
                            id="name"
                            name="name"
                            value={inputNm}
                            onChange={(e) => setInputNm(e.target.value)}
                            onBlur={() => checkNm(inputNm)}
                        />
                        {nmCheck && <div
                            style={{ color: nmCheckTextColor }}
                            className={styles.text2}
                        >
                            {nmCheckText}
                        </div>}
                        <div className={styles.div11}>
                            <span>{`성별 `}</span>
                        </div>
                        <RadioGroup
                            className={styles.genderRadioGroup}
                            value={inputGender}
                            onChange={setInputGender}
                        >
                            {genderOptions.map((option) => (
                                <Radio
                                    radioLabelStyle={styles.genderRadioLabel}
                                    value={option.value}
                                >
                                    {option.name}
                                </Radio>
                            ))}
                        </RadioGroup>
                        <label className={styles.div14} htmlFor="age">
                            <span>나이</span>
                        </label>
						<div className={styles.groupDiv}>
                            <input
                                className={styles.inBox5}
                                type="number"
                                id="age"
                                name="age"
                                value={inputAge}
                                onChange={(e) => setInputAge(e.target.value)}
                                onBlur={() => checkAge(inputAge)}
                            />
							<div className={styles.div7}>(만)</div>
						</div>
                        {ageCheck && <div
                            style={{ color: ageCheckTextColor }}
                            className={styles.div16}
                        >
                            {ageCheckText}
                        </div>}
                        <div className={styles.div17}>
                            <span>{`직업군 `}</span>
                        </div>
                        <SelectBox
                            selectWrapperStyle={styles.jobCategorySelectWrapper}
                            options={jobCategoryOptions}
                            value={inputJobCategory}
                            onChange={(e) => setInputJobCategory(e.target.value)}
                        />
                        <div className={styles.div19}>
                            <span>{`핸드폰 번호 `}</span>
                        </div>
						<div className={styles.inBoxParent2}>
                            <input
                                className={styles.inBox2}
                                type="number"
                                id="phoneNumber"
                                name="phoneNumber"
                                placeholder="‘-’ 없이 입력해주세요. (ex) 01012341234"
                                value={inputPhoneNumber}
                                onChange={(e) => setInputPhoneNumber(e.target.value)}
                                onBlur={() => checkPhone(inputPhoneNumber)}
                            />
						</div>
                        {phoneCheck && <div
                            style={{ color: phoneCheckTextColor }}
                            className={styles.div20}
                        >
                            {phoneCheckText}
                        </div>}
                        <div className={styles.div21}>
                            <span>{`주소 `}</span>
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
                            className={styles.btnBoxParent}
                            onClick={postCodeHandle.clickButton}
                        >
                            <div className={styles.btnBox} />
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
						<div className={styles.inBoxParent3}>
                            <input
                                className={styles.inBox2}
                                type="text"
                                id="addressDetail"
                                name="addressDetail"
                                placeholder="상세주소를 입력해 주세요."
                                value={inputAddressDetail}
                                onChange={(e) => setInputAddressDetail(e.target.value)}
                            />
						</div>
                        <div className={styles.div23}>
                            <span>{`상품 추천 선택 항목 `}</span>
                        </div>
                        <RadioGroup
                            className={styles.productRecommendRadioGroup}
                            value={inputProductRecommend}
                            onChange={setInputProductRecommend}
                        >
                            {productRecommendOptions.map((option) => (
                                <Radio
                                    radioLabelStyle={styles.productRecommendRadioLabel}
                                    value={option.value}
                                >
                                    {option.name}
                                </Radio>
                            ))}
                        </RadioGroup>
						<div
						    className={styles.btn2}
                            onClick={(e) => {
                                onClickUpdate();
                            }}
                        >
							<div className={styles.btnBox1} />
							<b className={styles.b2}>수정</b>
						</div>
					</div>
				</div>
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

export default Mypage;
