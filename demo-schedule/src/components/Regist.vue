<script setup>
import { ref, reactive } from 'vue';
import request from '../utils/request';
import { useRouter } from 'vue-router';
import { ElForm, ElFormItem, ElInput, ElButton, ElMessage } from 'element-plus';

const router = useRouter();

let registUser = reactive({
  username: '',
  userPwd: ''
});
let usernameMsg = ref('');
let userPwdMsg = ref('');
let reUserPwdMsg = ref('');
let reUserPwd = ref('');

async function checkUsername() {
  let usernameReg = /^[a-zA-Z0-9]{5,10}$/;
  if (!usernameReg.test(registUser.username)) {
    usernameMsg.value = '格式有误';
    return false;
  }
  // 发送异步请求，继续校验用户名是否被占用
  let { data } = await request.post(`user/checkUsernameUsed?username=${registUser.username}`);
  if (data.code != 200) {
    usernameMsg.value = '用户名占用';
    return false;
  }
  usernameMsg.value = '可用';
  return true;
}

function checkUserPwd() {
  let userPwdReg = /^[0-9]{6}$/;
  if (!userPwdReg.test(registUser.userPwd)) {
    userPwdMsg.value = '格式有误';
    return false;
  }
  userPwdMsg.value = 'OK';
  return true;
}

function checkReUserPwd() {
  let userPwdReg = /^[0-9]{6}$/;
  if (!userPwdReg.test(reUserPwd.value)) {
    reUserPwdMsg.value = '格式有误';
    return false;
  }
  if (registUser.userPwd != reUserPwd.value) {
    reUserPwdMsg.value = '两次密码不一致';
    return false;
  }
  reUserPwdMsg.value = 'OK';
  return true;
}

// 注册的方法
async function regist() {
  // 校验所有的输入框是否合法
  let flag1 = await checkUsername();
  let flag2 = checkUserPwd();
  let flag3 = checkReUserPwd();
  if (flag1 && flag2 && flag3) {
    let { data } = await request.post('user/regist', registUser);
    if (data.code == 200) {
      ElMessage.success('注册成功');
      router.push('/login');
    } else {
      ElMessage.error('用户名被抢注了');
    }
  } else {
    ElMessage.error('校验不通过');
  }
}

function clearForm() {
  registUser.username = '';
  registUser.userPwd = '';
  usernameMsg.value = '';
  userPwdMsg.value = '';
  reUserPwd.value = '';
  reUserPwdMsg.value = '';
}
</script>

<template>
    <div>
      <h3 class="ht">请注册</h3>
      <div class="form-container">
        <el-form :model="registUser" label-width="120px">
          <el-form-item label="请输入账号">
            <el-input class="short-input" v-model="registUser.username" @blur="checkUsername"></el-input>
            <span id="usernameMsg" class="msg">{{ usernameMsg }}</span>
          </el-form-item>
          <el-form-item label="请输入密码">
            <el-input class="short-input" type="password" v-model="registUser.userPwd" @blur="checkUserPwd"></el-input>
            <span id="userPwdMsg" class="msg">{{ userPwdMsg }}</span>
          </el-form-item>
          <el-form-item label="确认密码">
            <el-input class="short-input" type="password" v-model="reUserPwd" @blur="checkReUserPwd"></el-input>
            <span id="reUserPwdMsg" class="msg">{{ reUserPwdMsg }}</span>
          </el-form-item>
          <el-form-item class="button-container">
            <el-button type="primary" @click="regist">注册</el-button>
            <el-button @click="clearForm">重置</el-button>
            <router-link to="/login">
              <el-button type="success">去登录</el-button>
            </router-link>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </template>
  
  <style scoped>
  .ht {
    text-align: center;
    color: cadetblue;
    font-family: 幼圆;
  }
  
  .form-container {
    width: 500px;
    margin: 0 auto;
    padding: 20px;
    border: 5px solid cadetblue;
    border-radius: 5px;
    font-family: 幼圆;
    background-color: #f9f9f9;
  }
  
  .short-input {
    width: 200px;
  }
  
  .msg {
    color: gold;
    display: inline-block;
    margin-left: 10px;
    vertical-align: middle;
  }
  
  .el-form-item {
    margin-bottom: 20px;
  }
  
  .button-container {
    display: flex;
    justify-content: space-between;
  }
  </style>
  
