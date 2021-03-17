<template>
  <div class="app-container">
    <el-table
      :data="list"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="序号" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="用户ID">
        <template slot-scope="scope">
          {{ scope.row.uno }}
        </template>
      </el-table-column>
      <el-table-column label="邮箱" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column label="身份" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.flag }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="Pageviews" width="110" align="center">-->
<!--        <template slot-scope="scope">-->
<!--          {{ scope.row.pageviews }}-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column class-name="status-col" label="Status" width="110" align="center">-->
<!--        <template slot-scope="scope">-->
<!--          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column align="center" prop="created_at" label="Display_time" width="200">-->
<!--        <template slot-scope="scope">-->
<!--          <i class="el-icon-time" />-->
<!--          <span>{{ scope.row.display_time }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>
  </div>
</template>

<script>
import { getList } from '@/api/table'

export default {

  data() {
    return {
      list: null,
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      var vm = this
      this.axios({
        method: 'GET',
        url: 'http://127.0.0.1:8080/bishe_shsun/query_sun'
      }).then(function(resp) {
        vm.list = resp.data
        // vm.list = eval('(' + resp.data + ')')
      })
    }
  }
}
</script>
