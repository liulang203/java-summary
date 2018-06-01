define(function (require) {
    authorize  = require("common/authorize");
    axios  = require("common/axios");
    return {

        template: `
<el-menu default-active="1-4-1" class="el-menu-vertical-demo" 
    router
    @open="handleOpen" 
    @close="handleClose" 
    :collapse="isCollapse"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">
<template v-for="menu in menus">
  <el-submenu v-if="menu.subMenus"  v-bind:index="menu.id" >
    <template slot="title">
      <i v-if="menu.icon" v-bind:class="menu.icon"></i>
      <span slot="title" v-text="menu.name"></span>
    </template>
    
        <template v-for="menu2 in menu.subMenus">
      <el-submenu v-if="menu2.subMenus"  v-bind:index="menu2.id" >
        <template slot="title">
          <i v-if="menu2.icon" v-bind:class="menu2.icon"></i>
          <span slot="title" v-text="menu2.name"></span>
        </template>
        
            <template v-for="menu3 in menu2.subMenus">
          <el-submenu v-if="menu3.subMenus"  v-bind:index="menu3.id" >
            <template slot="title">
              <i v-if="menu3.icon" v-bind:class="menu3.icon"></i>
              <span slot="title" v-text="menu3.name"></span>
            </template>
            
            <template v-for="menu4 in menu3.subMenus">
          <el-submenu v-if="menu4.subMenus"  v-bind:index="menu4.id" >
            <template slot="title">
              <i v-if="menu4.icon" v-bind:class="menu4.icon"></i>
              <span slot="title" v-text="menu4.name"></span>
            </template>
          </el-submenu>
           <el-menu-item :route="menu4.href" v-else :index="menu4.id">
            <i v-if="menu4.icon" :class="menu4.icon"></i>
            <span slot="title" v-text="menu4.name"></span>
          </el-menu-item>
          </template>
            
          </el-submenu>
           <el-menu-item :route="menu3.href" v-else :index="menu3.id">
            <i v-if="menu3.icon" :class="menu3.icon"></i>
            <span slot="title" v-text="menu3.name"></span>
          </el-menu-item>
          </template>
        
      </el-submenu>
       <el-menu-item :route="menu2.href" v-else :index="menu2.id">
        <i v-if="menu2.icon" :class="menu2.icon"></i>
        <span slot="title" v-text="menu2.name"></span>
      </el-menu-item>
      </template>
  </el-submenu>
   <el-menu-item :route="menu.href" v-else :index="menu.id">
    <i v-if="menu.icon" :class="menu.icon"></i>
    <span slot="title" v-text="menu.name"></span>
  </el-menu-item>
  </template>
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
