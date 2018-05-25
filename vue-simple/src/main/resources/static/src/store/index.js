define(function (require) {
    "use strict";
    const getters = require('./getters');
    const mutations = require('./mutations');
    const actions = require('./actions');
    const Vue = require('vue');
    const Vuex = require('vuex');
    Vue.use(Vuex)

    const store = new Vuex.Store({
        state: {
            userInfo: {
                displayName: '',
                email: '',
                uid: ''
            },
            showLoading:true,
            roles: [],
            permissions: []
        },
        getters,
        mutations,
        actions
    });
    return store

});