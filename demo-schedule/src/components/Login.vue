<script setup>
import { ref, reactive } from 'vue';
import request from '../utils/request.js';
import { defineUser } from '../store/userStore';
import { useRouter } from 'vue-router';
import { ElButton, ElInput, ElMessage, ElForm, ElFormItem } from 'element-plus';

let sysUser = defineUser();
const router = useRouter();

let loginUser = reactive({
  username: '',
  userPwd: '',
});

let usernameMsg = ref('');
let userPwdMsg = ref('');

function checkUsername() {
  var usernameReg = /^[a-zA-Z0-9]{5,10}$/;
  if (!usernameReg.test(loginUser.username)) {
    usernameMsg.value = '格式有误';
    return false;
  }
  usernameMsg.value = 'ok';
  return true;
}

function checkUserPwd() {
  var passwordReg = /^[0-9]{6}$/;
  if (!passwordReg.test(loginUser.userPwd)) {
    userPwdMsg.value = '格式有误';
    return false;
  }
  userPwdMsg.value = 'ok';
  return true;
}

async function login() {
  let flag1 = checkUsername();
  let flag2 = checkUserPwd();
  if (!(flag1 && flag2)) {
    return;
  }
  let { data } = await request.post('/user/login', loginUser);
  if (data.code == 200) {
    ElMessage.success('登录成功');
    sysUser.uid = data.data.loginUser.uid;
    sysUser.username = data.data.loginUser.username;
    router.push('/showSchedule');
  } else if (data.code == 501) {
    ElMessage.error('用户名有误');
  } else if (data.code == 503) {
    ElMessage.error('密码有误');
  } else {
    ElMessage.error('未知错误');
  }
}
</script>

<template>
  <div>
    <h3 class="ht">请登录</h3>
    <div class="form-container">
      <el-form :model="loginUser" label-width="120px">
        <el-form-item label="请输入账号">
          <el-input class="short-input" v-model="loginUser.username" @blur="checkUsername"></el-input>
          <span id="usernameMsg" class="msg">{{ usernameMsg }}</span>
        </el-form-item>
        <el-form-item label="请输入密码">
          <el-input class="short-input" type="password" v-model="loginUser.userPwd" @blur="checkUserPwd"></el-input>
          <span id="userPwdMsg" class="msg">{{ userPwdMsg }}</span>
        </el-form-item>
        <el-form-item class="button-container">
          <el-button type="primary" @click="login" class="button">登录</el-button>
          <el-button @click="() => { loginUser.username = ''; loginUser.userPwd = ''; usernameMsg.value = ''; userPwdMsg.value = ''; }" class="button">重置</el-button>
          <router-link to="/regist">
            <el-button type="success" class="button">去注册</el-button>
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
  vertical-align: middle;
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

.button {
  margin: 0 5px;
}
</style>
