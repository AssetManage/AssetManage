import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";
import "./App.css";

import Login from "./components/login/Login";
import SignUp from "./components/sign-up/SignUp";
import ProductDetail from "./components/product-detail/ProductDetail";
import Main from "./components/main/Main";
import Main2 from "./components/main/Main2";

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path="" element={<Main />} />
            <Route path="login" element={<Login />} />
            <Route path="sign-up" element={<SignUp />} />
            <Route path="product-detail" element={<ProductDetail />} />
            <Route path="main2" element={<Main2 />} />
        </Routes>
    </BrowserRouter>
  );
}

export default App;
