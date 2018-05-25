define(function (require) {
    authorize  = require("common/authorize");
    return {
        components:{
            'v-head': require("component/head"),
            'v-menu': require("component/menu"),
            'v-body': require("component/body"),
        },
        template: `<el-container v-if="show">
        <el-header><v-head></v-head></el-header>
        <el-container>
            <el-aside width="210px"><v-menu></v-menu></el-aside>
            <el-main><router-view></router-view></el-main>
        </el-container>
    </el-container>`,
        data: function () {
            return {
            };
        },
        computed: {
            show: function() {
                return ! this.$store.state.showLoading;
            }
        },

        created: function () {
            let vm = this;
            data = authorize.authorizeInfo();
            if(data){
                console.log("user has ready login");
            }else{
                console.log("user need login");
                 // vm.$router.replace("/login")
            }

        }
    };
});
