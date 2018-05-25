define(function ( require) {
    let VueRouter = require("vueRouter");
    return new VueRouter({
        routes: [{
            path: "/",
            component: resolve => require(['component/main'],resolve),
            children: [
                {
                    path: "",
                    component: resolve => require(['component/body'],resolve)
                }
            ]
        }, {
            path: "/login",
            component:  resolve => require(['component/login'],resolve)
        }
        ]
    })
});