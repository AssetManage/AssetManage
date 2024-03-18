import React, { useState, useEffect } from 'react';
import "./Tab.css";

const Tab = () => {
    const [scrolled, setScrolled] = useState(false);

    useEffect(() => {
        function handleScroll() {
            const scrollPosition = window.scrollY;
            if (scrollPosition > 100) {
                setScrolled(true);
            } else {
                setScrolled(false);
            }
        }

        window.addEventListener('scroll', handleScroll);
        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []); // 컴포넌트가 처음 렌더링될 때만 실행
    return (
        <div className={`tab-menu ${scrolled ? 'scrolled' : ''}`}>
            <div className="in-box-parent">
                <div className="in-box2">
                    <div className="div25"><b className="b13">상품안내</b></div>
                    <img className="arr-ico-icon" alt="" src="/arrico_white.svg"/>
                </div>

            </div>
            <div className="in-box-group">
                <div className="in-box3">
                    <div className="div26">금리안내</div>
                    <img className="arr-ico-icon1" alt="" src="/arrico.svg"/>
                </div>

            </div>
            <div className="in-box-container">
                <div className="in-box4">
                    <div className="div27">유의사항</div>
                    <img className="arr-ico-icon2" alt="" src="/arrico.svg"/>
                </div>

            </div>
        </div>
    )
}

export default Tab;