import axios from 'axios';

var TestAxios = axios.create({
    baseURL: 'http://localhost:8080/api',
});

TestAxios.interceptors.request.use(
    function add_jwt(config){
        if(window.localStorage["jwt"]){
            config.headers["Authorization"]="Bearer " + window.localStorage["jwt"]
        }
        return config
    }
)

export default TestAxios;