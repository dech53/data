<script setup>
import { defineSchedule } from '../store/scheduleStore';
import { defineUser } from '../store/userStore';
let sysUser = defineUser()
let schedule = defineSchedule()
import { onMounted,reactive,ref,onUpdated } from 'vue';
import request from '../utils/request'
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from '@element-plus/icons-vue'
const input = ref('')
 onMounted(async()=>{
    showSchedule()
})
async function showSchedule(){
    let {data}= await request.get("/schedule/findAllSchedule",{params:{"uid":sysUser.uid}})
    schedule.itemList = data.data.itemList
}
async function addItem(){
    let {data} = await request.get("/schedule/addDefaultSchedule",{params:{"uid":sysUser.uid}})
    console.log(data);
    if(data.code == 200){
        showSchedule()
    }else{
        alert("增加失败")
    }
}
async function updateItem(index){
    let {data} = await request.post("/schedule/updateSchedule",schedule.itemList[index])
    if(data.code == 200){
        showSchedule()
    }else{
        alert("更新异常")
    }
}
async function removeItem(index){
        if(confirm("确定要删除该条数据")){
            let sid = schedule.itemList[index].sid
            let{data} = await request.get("/schedule/removeSchedule",{params:{"sid":sid}})
            if(data.code == 200){
                showSchedule()
            }else{
                alert("删除失败")
            }
        }
    }
</script>

<template>
    <div>
      <h3 class="ht">您的日程如下</h3>
      <el-table :data="schedule.itemList" style="width: 80%; margin: 0 auto;">
        <el-table-column prop="index" label="编号" width="70">
          <template #default="{ $index }">
            <span>{{ $index + 1 }}</span>
          </template>
        </el-table-column>
        <el-table-column label="内容">
          <template #default="{ row }">
            <el-input v-model="row.title" placeholder="Please input" clearable></el-input>
          </template>
        </el-table-column>
        <el-table-column label="进度">
          <template #default="{ row }">
            <el-radio-group v-model="row.completed">
              <el-radio :label="1">已完成</el-radio>
              <el-radio :label="0">未完成</el-radio>
            </el-radio-group>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="{ $index }">
            <el-button type="primary" @click="updateItem($index)">保存修改</el-button>
            <el-button type="danger" @click="removeItem($index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="text-align: center; margin-top: 10px;">
        <el-button type="primary" @click="addItem">新增日程</el-button>
      </div>
    </div>
  </template>

<style scoped>


      .ht{
            text-align: center;
            color: cadetblue;
            font-family: 幼圆;
        }
        .tab{
            width: 80%;
            border: 5px solid cadetblue;
            margin: 0px auto;
            border-radius: 5px;
            font-family: 幼圆;
        }
        .ltr td{
            border: 1px solid  powderblue;

        }
        .ipt{
            border: 0px;
            width: 50%;

        }
        .btn1{
            border: 2px solid powderblue;
            border-radius: 4px;
            width:100px;
            background-color: antiquewhite;

        }
        #usernameMsg , #userPwdMsg {
            color: gold;
        }

        .buttonContainer{
            text-align: center;
        }

</style>
