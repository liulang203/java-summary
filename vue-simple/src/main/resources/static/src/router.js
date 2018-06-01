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
                },
                {
                    path: "user",
                        component: resolve => require(['component/user'],resolve)
                },
    {
        path: "*",
            component: resolve => require(['component/4xxPage'],resolve)
    }
            ]
        }, {
            path: "/login",
            component:  resolve => require(['component/login'],resolve)
        }
        ]
    })
});