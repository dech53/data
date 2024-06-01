<script setup>
import { defineUser } from '../store/userStore';
import { defineSchedule } from '../store/scheduleStore';
import { useRouter } from 'vue-router';
import { ElButton, ElRow, ElCol } from 'element-plus';

import { Check, Delete, Edit, Message, Search, Star } from '@element-plus/icons-vue';

let sysUser = defineUser();
let schedule = defineSchedule();
let router = useRouter();

function logout() {
  sysUser.$reset();
  schedule.$reset();
  router.push('/login');
}
</script>

<template>
  <div>
    <h1 class="ht">欢迎使用日程管理系统</h1>
    <el-row justify="end">
      <el-col :span="6" v-if="sysUser.username === ''">
        <router-link to="/login">
          <el-button type="primary" round>登录</el-button>
        </router-link>
        <router-link to="/regist">
          <el-button type="success" round>注册</el-button>
        </router-link>
      </el-col>
      <el-col :span="6" v-else>
        <div>欢迎 {{ sysUser.username }}</div>
        <el-button type="danger" round @click="logout">退出登录</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.ht {
  text-align: center;
  color: cadetblue;
  font-family: 幼圆;
}

.optionDiv {
  width: 400px;
  float: right;
}
</style>
