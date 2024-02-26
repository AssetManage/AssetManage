import axios from 'axios';

// 로그인 상태 확인
const checkLoginState = () => {
    const token = localStorage.getItem('accessToken');

    return axios.post('/api/sign/logout', {
        token: token
    }).then(function (res) {
        if(res.data == true){
            return true;
        }

        return false;
    }).catch(function (error) {
        console.log(error);
        return false;
    });
}

export { checkLoginState };