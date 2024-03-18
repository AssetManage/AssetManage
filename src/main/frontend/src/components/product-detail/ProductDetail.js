import "./ProductDetail.module.css";
import BankDetail from "./BankDetail";
import Tab from "./Tab";
import BankInfo from "./BankInfo";
import Header from '../common/header/Header';

const ProductDetail = () => {
	return (
        <div className="detail-1920">
            <div className="component-parent">
                <div className="header-wrapper">
                    <Header />
                </div>
                <div className="tab-menu-wrapper">
                    <Tab />
                </div>
                <div className="group-wrapper">
                    <BankDetail />
                </div>
                <div className="info-wrapper">
                    <BankInfo/>
                </div>
            </div>
        </div>
    );
};

export default ProductDetail;
