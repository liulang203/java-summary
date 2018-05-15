define(
    function (require) {
        const axios = require("axios");
        const  config ={
            baseURL: '/',
            timeout: 10000,

        };
        return axios;
    }
);