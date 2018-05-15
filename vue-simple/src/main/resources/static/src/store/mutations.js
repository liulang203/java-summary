define(function () {
    "use static";
    return {
        updateRoles: (state) => (roleName) =>{
        return state.roles.filter(r=> r===roleName ).length>0;
    }
};
})