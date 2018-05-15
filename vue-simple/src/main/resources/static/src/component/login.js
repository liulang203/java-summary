define(function (require) {
    return {
        data: function () {
            return {
                show: false
            };
        },
        template: `<div>login page</div>`,
        created: function () {
            const vm = this;
            if(vm.$store.getters.isLogin){
                console.log("user has been login!");
                vm.$router.replace("/")
            }
            console.log("user login form!");
        }
    };
});
