import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";
import "./App.css";

import Login from "./components/login/Login";
import SignUp from "./components/sign-up/SignUp";
import ProductDetail from "./components/product-detail/ProductDetail";

function App() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path="login" element={<Login />} />
            <Route path="sign-up" element={<SignUp />} />
            <Route path="product-detail" element={<ProductDetail />} />
        </Routes>
    </BrowserRouter>
  );
}

export default App;
