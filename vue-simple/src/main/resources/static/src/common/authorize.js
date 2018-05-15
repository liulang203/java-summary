define(
    function (require) {
        let axios = require("axios");
        let router = require("router");

        axios.interceptors.response.use(undefined, err => {
            let res = err.response;
        if (res.status === 401 && res.config && !res.config.__isRetryRequest) {
            router.replace("/login");
        }
    })

        return {
            authorizeInfo: function () {
                var data = null;
                axios.get("/authorize/userInfo").then(function (response) {
                    data = response.status === 200 ? response.data : null;
                });
                return data;
            }
        };
    }
);