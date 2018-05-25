(function (require, define) {
    require.config({
            baseUrl: 'src',
            paths: {
                promise: "../static/q",
                static: "../static",
                vue: "../static/vue",
                vuex: "../static/vuex",
                vueRouter: "../static/vue-router",
                ELEMENT: "../static/element-ui/index",
                axios: "../static/axios/axios"
            },
            shim: {
                promise: {
                    exports: "Q"
                },
                'vue': {
                    exports: "Vue"
                },
                'vuex': {
                    deps: ['vue'],
                    exports: "Vuex"
                },
                'vueRouter': {
                    deps: ['vue'],
                    exports: "VueRouter"
                }
            }
        }
    );
    require(['router', 'vue', 'vueRouter', 'ELEMENT', 'store/index'], function (router, Vue, VueRouter, ELEMENT, store) {
        Vue.use(VueRouter);
        Vue.use(ELEMENT);
        let app = new Vue({
            el: '#app',
            store: store,
            router: router,
            components: {
                "loading": {
                    template: `<div v-if="show" class="globalMask">loading......</div>`,
                    computed: {
                        show: function () {
                            return this.$store.state.showLoading;
                        }
                    }
                }
            }
        });
    });
})(require, define);
