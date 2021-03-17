<template>
  <div class="dashboard-container">


<!--    <div class="dashboard-text">name: {{ name }}</div>-->
    <div class="dashboard-text">欢迎使用小毛驴教育平台</div>

    <div>
      <img class="dashboard-im" src="@/assets/main.jpg" ></img>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  },

}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
  &-im {
    position: absolute;
    top: 50%;
    left: 50%;
    display: block;
    min-width: 60%;
    min-height: 60%;
    transform:translate(-50%,-50%);
  }
}
.dashboard-im {
  cursor: pointer;
  width:  80%;
  height: 80%;
  border-radius: 10px;
}
</style>
