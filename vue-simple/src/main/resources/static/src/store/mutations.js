define(function () {
    "use static";
    return {
        changLoading(state, nwhow) {
            state.showLoading = nwhow;
        },
        logout(state){
            state.userInfo.uid='';
            state.permissions.length=0;
            state.roles.length=0;
        },
        userInfo(state, userInfo) {
            var info = userInfo.userInfo;
            Object.keys(state.userInfo).forEach(function(e,i){
                state.userInfo[e] = info[e] || state.userInfo[e];
            });
            state.roles.push(userInfo.roles);
            state.permissions.push(userInfo.permissions);
        },
    };
})