import axios from 'axios';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import styles from './Header.module.css';

import { checkLoginState } from '../../../common/common';

function Header() {
	const navigate = useNavigate();

    const [loginState, setLoginState] = useState(false);
    const [userName, setUserName] = useState('');
    const [profileImgUrl, setProfileImgUrl] = useState('');

    // 로그인
    const login = () => {
        sessionStorage.setItem('preLoginPath', window.location.pathname);
        navigate('/login');
    };

    // 로그아웃
    const logout = async () => {
        localStorage.removeItem('accessToken');
        window.location.reload();
    };

	useEffect(() => {
		const onStart = async () => {
			const response = await axios
				.get('/st/user/selectUserAll', {
					headers: {
						Authorization: localStorage.getItem('accessToken'),
					},
				})
				.then(function (res) {
				    console.log(res);
					return res;
				})
				.catch(function (error) {
					return error;
				});

            // 비로그인 상태
			if(response?.data?.stat !== 'SUCCESS'){
			    setLoginState(false);
			}
			// 로그인 상태
			else{
                setLoginState(true);
                setUserName(response?.data?.data?.userNm);

                if(response?.data?.data?.profileImgUrl === '' || response?.data?.data?.profileImgUrl === null){
                    setProfileImgUrl('/userIcon.svg');
                } else {
                    setProfileImgUrl(response?.data?.data?.profileImgUrl);
                }
			}
		};

		onStart();
	}, []);

	return (
		<div className={styles.header}>
			<div className={styles.logo} onClick={() => navigate('/main')}>LOGO</div>
			<div className={styles.menu}>
				<div className={styles.menu01} onClick={() => navigate('/main')}>예금</div>
				<div className={styles.menu02} onClick={() => navigate('/main')}>적금</div>
				<div className={styles.menu03} onClick={() => navigate('/main')}>추천</div>
			</div>
			{/* 비로그인 상태 */}
			{!loginState &&
			<div className={styles.vectorParent}>
				<div className={styles.vectorDiv} onClick={() => login()}>
					<img className={styles.vectorIcon} alt='' src='/vector.svg' />
					<b className={styles.logIn}>Log in</b>
				</div>
				<div className={styles.vectorDiv} onClick={() => navigate('/sign-up')}>
					<img className={styles.vectorIcon1} alt='' src='/userIcon.svg' />
					<b className={styles.signUp}>Sign up</b>
				</div>
			</div>
			}
			{/* 로그인 상태 */}
			{loginState &&
            <div className={styles.vectorParent}>
				<div className={styles.vectorDiv} onClick={() => logout()}>
                    <img className={styles.vectorIcon} alt='' src='/vector.svg' />
                    <b className={styles.logOut}>Log out</b>
                </div>
				<div className={styles.vectorDiv} onClick={() => {}}>
                    <img className={styles.groupIcon} alt='' src={profileImgUrl} />
                    <div className={styles.kimsw1245}>{userName}</div>
                </div>
            </div>
            }
		</div>
	);
}

export default Header;
