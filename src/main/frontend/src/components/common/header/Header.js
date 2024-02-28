import axios from 'axios';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import styles from "./Header.module.css";

import { checkLoginState } from "../../../common/common";

function Header() {
    const navigate = useNavigate();

    const goToPage = (page) => {
        navigate(page);
    };

    useEffect(() => {
    }, []);

	return (
	    <div>
            <div className={styles.logo}>LOGO</div>
            <div className={styles.menu}>
                <div className={styles.menu01}>menu01</div>
                <div className={styles.menu02}>menu02</div>
                <div className={styles.menu03}>menu03</div>
            </div>
            {/*
            <div className={styles.div}>
                <img className={styles.vectorIcon} alt="" src="/vector.svg" />
                <b className={styles.logOut}>Log out</b>
                <img className={styles.groupIcon} alt="" src="/group.svg" />
                <div className={styles.kimsw1245}>김선우(kimsw1245)</div>
            </div>
            */}
			<div className={styles.vectorParent}>
			    <div className={styles.vectorDiv} onClick={() => goToPage('/login')}>
                    <img className={styles.vectorIcon2} alt="" src="/vector.svg" />
                    <b className={styles.logIn}>Log in</b>
				</div>
				<div className={styles.vectorDiv} onClick={() => goToPage('/sign-up')}>
                    <img className={styles.vectorIcon1} alt="" src="/userIcon.svg" />
                    <b className={styles.signUp}>Sign up</b>
				</div>
			</div>
        </div>
	);
}

export default Header;
