<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-form-item label="上传" prop="field101" required>
        <!-- <el-upload
          ref="field101"
          :file-list="field101fileList"
          :action="field101Action"
          :before-upload="field101BeforeUpload"
        > -->
        <!-- accept="image/jpeg,image/gif,image/png" 加在下面的-->
        <el-upload
          action=""
          :on-change="onUploadChange"
          :auto-upload="false"
          :show-file-list="true"
        >
          <el-button slot="trigger" size="small" type="primary">选取</el-button>
          <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传</el-button>
        </el-upload>
        <!-- <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button> -->
        <!-- <el-button size="small" type="primary" icon="el-icon-upload" @click="submitVideo">点击上传</el-button> -->
        <!-- <input type="file" name="file" class="fileBtn">
          <input type="submit" name="submit" value="上传视频" class="submitBtn" @click="submitVideo"> -->
        <!-- </el-upload> -->
      </el-form-item>
      <el-form-item size="large">
        <!-- <el-button type="primary" @click="submitForm">提交</el-button> -->
        <!-- <el-button type="primary" @click="submitVideo">提交</el-button> -->
        <!-- <el-button @click="resetForm">重置</el-button> -->
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        field101: null
      },
      rules: {},
      field101Action: 'https://jsonplaceholder.typicode.com/posts/',
      field101fileList: []
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
    field101BeforeUpload(file) {
      const isRightSize = file.size / 1024 / 1024 < 200
      if (!isRightSize) {
        this.$message.error('文件大小超过 200MB')
      }
      return isRightSize
    },
    submitVideo() {
      var formData1 = new FormData()
      formData1.append('files', document.querySelector('input[type=file]').files[0])
      var options = {
        // url: 'api/video/upVideo',
        url: 'http://httpbin.org/',
        data: formData1,
        method: 'post',
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      axios(options).then(this.upVideoSucc).catch(this.upVideoFail)
    },
    upVideoSucc(res) {
      this.getVideo()
    },
    upVideoFail() {
      alert('上传失败！')
    },
    submitUpload() {
      console.log('submit')
    },
    onUploadChange(file) {
      const isIMAGE = (file.raw.type === 'image/jpeg' || file.raw.type === 'image/png' || file.raw.type === 'image/gif')
      const isLt1M = file.size / 1024 / 1024 < 200

      if (!isIMAGE) {
        this.$message.error('上传文件只能是图片格式!')
        return false
      }
      if (!isLt1M) {
        this.$message.error('上传文件大小不能超过 200MB!')
        return false
      }
      var reader = new FileReader()
      reader.readAsDataURL(file.raw)
      reader.onload = function(e) {
        console.log(this.result)// 图片的base64数据
      }
    }
  }
}

</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}

</style>
