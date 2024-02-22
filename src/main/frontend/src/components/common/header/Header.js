import styles from "./Header.module.css";

function Header() {
	return (
	    <div>
            <div className={styles.logo}>LOGO</div>
            <div className={styles.menu}>
                <div className={styles.menu01}>menu01</div>
                <div className={styles.menu02}>menu02</div>
                <div className={styles.menu03}>menu03</div>
            </div>
            <div className={styles.div}>
                <img className={styles.vectorIcon} alt="" src="/vector.svg" />
                <b className={styles.logOut}>Log out</b>
                <img className={styles.groupIcon} alt="" src="/group.svg" />
                <div className={styles.kimsw1245}>김선우(kimsw1245)</div>
            </div>
        </div>
	);
}

export default Header;
