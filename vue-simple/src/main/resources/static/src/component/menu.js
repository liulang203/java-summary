define(function (require) {
    authorize  = require("common/authorize");
    axios  = require("common/axios");
    return {
        template: `
<el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse">
  <el-submenu v-for="menu in menus" index="{{menu.id}}" >
    <template slot="title">
      <i class="el-icon-location"></i>
      <span slot="title">{{menu.name}}</span>
    </template>
    <el-submenu v-for="submenu in menu.subMenus" index="submenu.id">
      <span slot="title">选项4</span>
      <el-menu-item index="1-4-1">选项1</el-menu-item>
    </el-submenu>
    <el-submenu index="1-5">
      <span slot="title">选项5</span>
          <el-submenu index="1-5-1">
          <span slot="title">选项5-1</span>
          <el-menu-item index="1-5-1-1">选项5-1-1</el-menu-item>
        </el-submenu>
    </el-submenu>
  </el-submenu>
  
  <el-menu-item index="2">
    <i class="el-icon-menu"></i>
    <span slot="title">导航二</span>
  </el-menu-item>
  <el-menu-item index="3" disabled>
    <i class="el-icon-document"></i>
    <span slot="title">导航三</span>
  </el-menu-item>
  <el-menu-item index="4">
    <i class="el-icon-setting"></i>
    <span slot="title">导航四</span>
  </el-menu-item>
</el-menu>`,
        data: function () {
            return {
                isCollapse: false,
                menus:{}
            };
        },
        computed: {
            show: function() {
                return ''
            },
            menus:function () {

            }
        },
        mounted: function() {
            axios.get('/menu')
                .then(function(response){
                    this.menus = response.data;
                }.bind(this));
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    };
});
