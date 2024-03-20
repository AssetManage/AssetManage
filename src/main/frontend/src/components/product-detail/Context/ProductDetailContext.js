// ProductDetailContext.js
import React, { createContext, useState, useContext } from 'react';
import axios from "axios";

const ProductDetailContext = createContext();
export default ProductDetailContext;

export const useProductDetail = () => useContext(ProductDetailContext);

export const ProductDetailProvider = ({ children }) => {
    const [productData, setProductData] = useState(null);

    const getProduct = async (finCoNo, finPrdtCd, dclsMonth) => {
        axios.get('/st/product/selectProductList', {
            params: {
                'finCoNo': finCoNo,
                'finPrdtCd': finPrdtCd,
                'dclsMonth': dclsMonth
            }
        }).then(response => {
            console.log('res :: ', response);
            setProductData(response.data);
        }).catch(error => {
            console.error('Error fetching product data:', error);
        });

    };

    return (
        <ProductDetailContext.Provider value={{ productData, getProduct }}>
            {children}
        </ProductDetailContext.Provider>
    );
};
