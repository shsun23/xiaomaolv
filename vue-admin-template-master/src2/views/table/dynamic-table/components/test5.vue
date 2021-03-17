<template>
  <div>
    <input type="file" class="file">
    <button @click="submitVideo">提交</button>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data: function() {
    return {
      videoUrl: ''
    }
  },
  methods: {
    submitVideo() {
      var formData1 = new FormData()
      formData1.append('files', document.querySelector('input[type=file]').files[0])
      var options = {
        // url: 'api/video/upVideo',
        url: '', // 服务器地址
        data: formData1,
        method: 'post',
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      //   axios(options).then(this.upVideoSucc).catch(this.upVideoFail)
      axios(options).then(function(resp) {
        this.videoUrl = resp
        this.$store.commit('saveVideoUrl', this.videoUrl)
      }).catch(this.upVideoFail)
    },
    // upVideoSucc(res) {
    //   this.getVideo()
    // },
    upVideoFail() {
      alert('上传失败！')
    }
  }
}
</script>

<style>

</style>
