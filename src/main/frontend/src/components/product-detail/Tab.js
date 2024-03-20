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

    const scrollToSection = (id) => {
        const element = document.getElementById(id);
        if (element) {
            window.scrollTo({
                top: element.offsetTop,
                behavior: 'smooth'
            });
        }
    };


    return (
        <div className={`tab-menu ${scrolled ? 'scrolled' : ''}`}>
            <div className="in-box-parent">
                <div className="in-box" onClick={() => scrollToSection('product-info')}>
                    <div className="div25">상품안내</div>
                    <img className="arr-ico-icon" alt="" src="/arrico.svg"/>
                </div>

            </div>
            <div className="in-box-parent">
                <div className="in-box" onClick={() => scrollToSection('react-table2')}>
                    <div className="div25">금리안내</div>
                    <img className="arr-ico-icon" alt="" src="/arrico.svg"/>
                </div>

            </div>
            <div className="in-box-parent">
                <div className="in-box" onClick={() => scrollToSection('notice-detail')}>
                    <div className="div25">유의사항</div>
                    <img className="arr-ico-icon" alt="" src="/arrico.svg"/>
                </div>

            </div>
        </div>
    )
}

export default Tab;