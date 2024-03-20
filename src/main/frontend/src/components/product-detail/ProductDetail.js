import "./ProductDetail.css";
import { ProductDetailProvider } from './Context/ProductDetailContext';
import BankDetail from "./BankDetail";
import Tab from "./Tab";
import BankInfo from "./BankInfo";
import Header from '../common/header/Header';

const ProductDetail = () => {

	return (
        <div className="detail11920">
            <div className="detail11920-2">
                <div className="header-wrapper">
                    <Header/>
                </div>
                <div className="detail11920-3">
                    <ProductDetailProvider>
                        <div className="group-wrapper">
                            <BankDetail/>
                        </div>
                        <div className="info-wrapper">
                            <Tab/>
                            <BankInfo/>
                        </div>
                    </ProductDetailProvider>
                </div>
            </div>

        </div>


    );
};

export default ProductDetail;
