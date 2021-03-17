<template>
  <div class="app-container">
    这是一个注册页面
    <div>
      <el-button title="注册" @click="reg">register</el-button>
    </div>
    <div id="ToLogin" hidden="true">
      <router-link :to="{path:'/login'}">注册完成，去登陆</router-link>
    </div>

  </div>
</template>

<script>
  export default {
    name: "Register",
    methods :{
      reg(){
        document.getElementById("ToLogin").hidden = false;
      }
    }
  }
</script>

<style scoped>
#ToLogin {
  background-color: blue;
}
</style>
