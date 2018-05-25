define(
    function (require) {
        let axios = require("./axios");
        let router = require("router");
        let store = require("store/index");

        return {
            authorizeInfo() {
                var data = {};
                axios.get("/authorize/userInfo").then(function (response) {
                    if(response.status === 200){
                        var data = response.data;
                        store.commit('userInfo',data);
                        store.commit('changLoading',false);
                    }
                });
                return data;
            }

        };
    });