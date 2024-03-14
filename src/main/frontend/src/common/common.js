import axios from 'axios';

// 로그인 상태 확인
const checkLoginState = async () => {
    const token = localStorage.getItem('accessToken');

    return await axios.post('/api/sign/logout', {
        token: token
    }).then(function (res) {
        if(res.data !== true){
            return false;
        } else{
            return true;
        }
    }).catch(function (error) {
        return false;
    });
}

export { checkLoginState };