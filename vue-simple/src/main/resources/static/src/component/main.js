define(function (require) {
    authorize  = require("common/authorize");
    return {
        data: function () {
            return {
                show: false
            };
        },
        template: `<el-container>
        <el-header>header</el-header>
        <el-container>
            <el-aside>menu</el-aside>
            <el-main>main</el-main>
        </el-container>
    </el-container>`,
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
