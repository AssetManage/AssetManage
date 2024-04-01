import {
    BrowserRouter,
    Routes,
    Route,
    Navigate
} from "react-router-dom";
import "./App.css";

import Login from "./components/login/Login";
import SignUp from "./components/sign-up/SignUp";
import ProductDetail from "./components/product-detail/ProductDetail";
import Main from "./components/main/Main";
import Main2 from "./components/main/Main2";
import ProductList from "./components/product-list/ProductList";
import Mypage from "./components/mypage/Mypage";

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<Navigate replace to="/main" />} />
            <Route path="main" element={<Main />} />
            <Route path="login" element={<Login />} />
            <Route path="sign-up" element={<SignUp />} />
            <Route path="product-list" element={<ProductList />} />
            <Route path="product-detail" element={<ProductDetail />} />
            <Route path="main2" element={<Main2 />} />
            <Route path="mypage" element={<Mypage />} />
        </Routes>
    </BrowserRouter>
  );
}

export default App;
