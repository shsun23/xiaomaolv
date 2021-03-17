<template>
  <div>
    <div>
      <label v-if="type==='picture'" class="pointer" for="picture">
        上传图片
        <i class="fa fa-picture-o" aria-hidden="true" />
      </label>
      <input
        v-show="false"
        id="picture"
        ref="picture"
        accept=".jpg, .png, .gif, .jpeg"
        type="file"
        @change="showPicture()"
      >
      <label v-if="type==='video'" class="pointer" for="video">
        上传视频
        <i class="fa fa-film" aria-hidden="true" />
      </label>
      <input
        v-show="false"
        id="video"
        ref="video"
        accept=".mp4"
        type="file"
        @change="showVideo()"
      >
    </div>
    <div v-if="type==='picture'" class="attachment-preview">
      <div v-for="(img, index) in attachmentP" :key="index" class="content-img">
        <img :src="img.url" alt>
        <div class="click-div" @click="deleteImg(index)" />
      </div>
    </div>
    <div v-if="type==='video'" class="attachment-preview">
      <video
        v-for="(video, index) in attachmentV"
        :key="index"
        class="video"
        :src="video.url"
        controls
        autoplay
      />
    </div>
  </div>
</template>

<script>
export default {
  name: '',
  components: {},
  props: [],
  data() {
    return {
      formData: {
        field101: null
      },
      rules: {},
      field101Action: 'https://jsonplaceholder.typicode.com/posts/',
      field101fileList: [],
      // 保存图片的信息
      attachmentP: [],
      // 保存视频的信息
      attachmentV: []
    }
  },
  methods: {
    showPicture() {
      console.log('showPicture')
      const picture = this.$refs.picture.files[0]
      console.log(picture)
      // eslint-disable-next-line no-undef
      if (!validPicture(picture.path)) {
        this.$refs.picture.value = ''
        alert('图片格式只能是jpg|jpeg|png|gif')
      } else {
        if (picture) {
          const formdata = new FormData()
          formdata.append('myfile', picture)
          this.$store.dispatch('UploadFile', formdata).then(res => {
            console.log(res)
            this.$refs.picture.value = ''
            if (this.attachmentP.length >= 9) {
              alert('最多只能添加9张图片')
            } else {
              this.attachmentP.push(res)
            }
          }).catch(
            err => {
              console.log(err)
            })
        }
      }
      console.log(this.attachmentP)
    },
    deleteImg(index) {
      console.log(index)
      this.attachmentP.splice(index, 1)
    }
  }
}
</script>

<style lang="scss">
#app{
font-family: Arial, Helvetica, sans-serif;
-webkit-font-smoothing:antialiased;
-moz-osx-font-smoothing:grayscale;
text-align:#2c3e50;
margin-top: 60px;
.attachment-preview {
      width: 90%;
      height: 60%;
      position: relative;
      border: #4bbcfb 1px dashed;
      display: flex;
      align-items: center;
      justify-content: space-around;
      flex-wrap: wrap;
      .content-img {
        width: 30%;
        height: 30%;
        position: relative;
        display: flex;
        align-content: center;
        justify-content: space-around;
        &:hover {
          cursor: pointer;
          // & {
          //   filter: grayscale(0.8);
          // }
          &::before {
            content: "❌";
            position: absolute;
            background: rgba(0, 0, 0, 0.5);
            width: 100%;
            height: 100%;
            left: 0;
            top: 0;
            text-align: end;
          }
        }
        .click-div {
          width: 15px;
          height: 15px;
          position: absolute;
          right: 0;
          top: 0;
        }
        img {
          width: auto;
          height: auto;
          max-width: 100%;
          max-height: 100%;
        }
      }
    }
}
</style>

