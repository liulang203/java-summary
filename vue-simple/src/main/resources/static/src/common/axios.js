define(
    function (require) {
        let router = require("router");
        let store = require("store/index");
        const axios = require("axios");
        const  config ={
            baseURL: '/',
            timeout: 10000,

        };
        axios.interceptors.response.use(undefined, err => {
            let res = err.response;
        if (res.status === 401 && res.config && !res.config.__isRetryRequest) {
            store.commit("logout");
            router.replace("/login");
        } else if (res.status === 403){
            router.replace("/noPermission");
        }
    });
        return axios;
    }
);