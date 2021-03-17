<template>
  <div class="app-container">
    老师列表{{ res }}
    <el-table
      :data="res"
      border="true"
      style="width: 100%">
      <el-table-column
        prop="shool"
        label="学校"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
    import teacher  from '@/api/teacher/teacher'

    export default {
        name: "list",

      data() {
        return {
          res: null,
        }
      },
      created() {
        this.fetchData()
      },
      methods: {
        fetchData() {
          teacher.getTeacherList()
            .then(response => {
            // this.res = response.data
              this.res = response.data.rows;
          })
          .catch(error =>{
            console.log(error)
          })
        }
      }
    }
</script>

<style scoped>

</style>
