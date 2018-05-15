define(function () {
    "use static";
    return  {
        hasRole: (state) => (roleName) =>{
            return state.roles.filter(r=> r===roleName ).length>0;
        },
        isLogin:(state)=> { return state.userInfo.uid !== '';}

    };
})