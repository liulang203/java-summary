define(function ( require) {
    let VueRouter = require("vueRouter");
    return new VueRouter({
        routes: [
            {
                path: "/login",
                component:  resolve => require(['component/login'],resolve)
            },{
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
                    path: "noPermission",
                        component: resolve => require(['component/noPermission'],resolve)
                },
                {
                    path: "*",
                        component: resolve => require(['component/4xxPage'],resolve)
                }
            ]
        }
        ]
    })
});