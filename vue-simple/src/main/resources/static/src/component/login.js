define(function (require) {
    let axios = require("axios");
    return {
        template: `<el-row :gutter="20" class="el-row-login">
  <el-col :span="8" :offset="8">
<el-form :model="loginForm" status-icon :rules="loginRules" ref="loginForm" label-width="100px" class="demo-ruleForm">
    <h2 align="center">用户登录</h2>
  <el-form-item label="邮箱" prop="username">
    <el-input v-model.email="loginForm.username"></el-input>
  </el-form-item>
  <el-form-item label="密码" prop="password">
    <el-input type="password" v-model="loginForm.password" auto-complete="off"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('loginForm')">提交</el-button>
    <el-button @click="resetForm('loginForm')">重置</el-button>
  </el-form-item>
</el-form>
</el-col>
</el-row>

`,
        created: function () {
            const vm = this;
            if(vm.$store.getters.isLogin){
                console.log("user has been login!");
                vm.$router.replace("/")
            }
            vm.$store.commit('changLoading',false);
            console.log("user login form!");
        },
        data:function() {
            return {
                loginForm: {
                    password: '',
                    username: ''
                },
                loginRules: {
                    username: [
                        { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                        { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] },
                        { min: 3, max: 50, message: '长度在 3 到 50 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码！', trigger: 'blur' },
                        { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
                    ]

                }
            };
        },
        methods: {
            submitForm:function(formName) {
                const vm = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        data = vm.loginForm;
                        var params = new URLSearchParams();
                        params.append('username', data.username);
                        params.append('password', data.password);
                        axios.post("/user/login", params).then(function (response) {

                            loginRes = (response.status === 200) && response.data.login;
                            if(loginRes){
                                vm.$router.replace("/")
                            }else{
                                vm.$notify.error({
                                    title: '登录失败',
                                    message: '用户名或密码错误'
                                });
                            }
                        });
                    } else {
                return false;
                }
            });
            },
            resetForm:function(formName) {
                this.$refs[formName].resetFields();
            }
        }
    };
});
