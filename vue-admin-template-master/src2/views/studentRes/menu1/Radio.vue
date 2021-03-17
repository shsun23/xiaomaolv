<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.title" placeholder="搜索标题" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
      <el-select v-model="listQuery.importance" placeholder="重要星级" clearable style="width: 110px" class="filter-item">
        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item" />
      </el-select>
      <el-select v-model="listQuery.type" placeholder="知识点" clearable class="filter-item" style="width: 130px">
        <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name" :value="item.key" />
      </el-select>
      <el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />
      </el-select>
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
        搜索
      </el-button>
      <!-- <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button> -->
      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">
        导出
      </el-button>
    </div>

    <!-- 数据表格 -->
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange"
    >
      <el-table-column label="序号" prop="id" sortable="custom" align="center" width="80" :class-name="getSortClass('id')">
        <template slot-scope="{row}">
          <span>{{ row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="日期" width="150px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.timestamp | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="标题" min-width="150px">
        <template slot-scope="{row}">
          <span class="link-type" @click="AnswerQuestion(row)">{{ row.title }}</span>
          <el-tag>{{ row.type | typeFilter }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column v-if="false" label="题干" width="110px" align="center">
        <template slot-scope="{row}">
          <span>{{ row.tigan }}</span>
        </template>
      </el-table-column>
      <el-table-column v-if="showReviewer" label="Reviewer" width="110px" align="center">
        <template slot-scope="{row}">
          <span style="color:red;">{{ row.reviewer }}</span>
        </template>
      </el-table-column>
      <el-table-column label="重要星级" width="80px">
        <template slot-scope="{row}">
          <svg-icon v-for="n in + row.importance" :key="n" icon-class="star" class="meta-item__icon" />
        </template>
      </el-table-column>
      <el-table-column label="阅读量" align="center" width="95">
        <template slot-scope="{row}">
          <span v-if="row.pageviews" class="link-type" @click="handleFetchPv(row.pageviews)">{{ row.pageviews }}</span>
          <span v-else>0</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" class-name="status-col" width="100">
        <template slot-scope="{row}">
          <el-tag :type="row.status | statusFilter">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <!-- <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="{row,$index}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">
            编辑
          </el-button>
          <el-button v-if="row.status!='published'" size="mini" type="success" @click="handleModifyStatus(row,'published')">
            发布
          </el-button>
          <el-button v-if="row.status!='draft'" size="mini" @click="handleModifyStatus(row,'draft')">
            草稿
          </el-button>
          <el-button v-if="row.status!='deleted'" size="mini" type="danger" @click="handleDelete(row,$index)">
            删除
          </el-button>
        </template>
      </el-table-column> -->
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 答题窗口 -->
    <el-dialog title="单选题" :visible.sync="QuestionVisible">
      <div
        :rules="rules"
        :model="temp"
        label-position="left"
        label-width="70px"
        style="width: 650px; margin-left:0px;"
        v-html="temp.tigan"
      >{{ temp.tigan }}</div>
      <el-form :model="temp">
        <el-form-item label="选项" prop="usrAns">
          <el-select v-model="temp.usrAns" placeholder="请选择选项" clearable :style="{width: '100%'}">
            <el-option
              v-for="(item, index) in ansOptions"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            />
          </el-select>
        </el-form-item>
        <el-form-item size="large">
          <el-button type="primary" @click="submitAns">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 编辑窗口 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 650px; margin-left:0px;">
        <el-form-item label="知识点" prop="type">
          <el-select v-model="temp.type" class="filter-item" placeholder="请选择">
            <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name" :value="item.key" />
          </el-select>
        </el-form-item>
        <el-form-item label="日期" prop="timestamp">
          <el-date-picker v-model="temp.timestamp" type="datetime" placeholder="请选择日期" />
        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="temp.title" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="temp.status" class="filter-item" placeholder="请选择">
            <el-option v-for="item in statusOptions" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item label="重要星级">
          <el-rate v-model="temp.importance" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" :max="3" style="margin-top:8px;" />
        </el-form-item>
        <el-form-item label="评论">
          <el-input v-model="temp.remark" :autosize="{ minRows: 2, maxRows: 4}" type="textarea" placeholder="请输入" />
        </el-form-item>
        <el-form-item label="题目" prop="tigan">
          <Tinymce v-model="temp.tigan" placeholder="请输入题目" :height="300" />
        </el-form-item>
        <el-form-item label="选项" prop="rightAns">
          <el-select v-model="temp.rightAns" placeholder="请选择答案选项" clearable :style="{width: '100%'}">
            <el-option
              v-for="(item, index) in ansOptions"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          确定
        </el-button>
      </div>
    </el-dialog>

    <!-- 数据读取窗口（未使用） -->
    <el-dialog :visible.sync="dialogPvVisible" title="Reading statistics">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel" />
        <el-table-column prop="pv" label="Pv" />
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">Confirm</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios' // 与后端交互
import { fetchList, fetchPv, createArticle, updateArticle } from '@/api/article'
import waves from '@/directive/waves' // waves directive
import { parseTime } from '@/utils'
import Pagination from '@/components/Pagination' // secondary package based on el-pagination
import Tinymce from '@/components/Tinymce' // 引入富文本框

const calendarTypeOptions = [ // 知识点选择数组
  { key: 'math_1', display_name: '全等三角形' },
  { key: 'math_2', display_name: '二元方程组' },
  { key: 'math_3', display_name: '化简' },
  { key: 'math_4', display_name: '计算' }
]

// arr to obj, such as { CN : "China", US : "USA" }
const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
  acc[cur.key] = cur.display_name
  return acc
}, {})

export default {
  name: 'ComplexTable',
  components: { Pagination, Tinymce },
  directives: { waves },
  filters: {
    statusFilter(status) { // 状态过滤器
      const statusMap = {
        published: 'success',
        draft: 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    typeFilter(type) { // 知识点类型过滤器,通过知识点类型获得知识点名称
      return calendarTypeKeyValue[type]
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      AnsRes: '', // 后端返回答案是否正确，true/false
      ansOptions: [{ // 答案选项
        'label': 'A',
        'value': 1
      }, {
        'label': 'B',
        'value': 2
      }, {
        'label': 'C',
        'value': 3
      }, {
        'label': 'D',
        'value': 4
      }, {
        'label': 'E',
        'value': 5
      }, {
        'label': 'F',
        'value': 6
      }, {
        'label': 'G',
        'value': 7
      }],
      listQuery: { // 列表请求
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        type: undefined,
        sort: '+id'
      },
      importanceOptions: [1, 2, 3], // 总要星级列表
      calendarTypeOptions,
      sortOptions: [{ label: '序号升序', key: '+id' }, { label: '序号降序', key: '-id' }],
      statusOptions: ['published', 'draft', 'deleted'],
      showReviewer: false,
      temp: { // 当前编辑表格信息
        id: undefined, // 序号
        importance: 1, // 重要星级
        remark: '', // 评论
        timestamp: new Date(), // 时间戳
        title: '', // 标题
        type: 'math_1', // 知识点类型
        status: 'published', // 题目状态
        // 添加
        tigan: '', // 题目题干
        rightAns: '', // 题目答案
        usrAns: '' // 用户所选择的答案
      },
      dialogFormVisible: false, // 状态控制变量，控制编辑窗口是否弹出可见
      QuestionVisible: false, // 状态控制变量，控制答题窗口是否弹出可见
      dialogStatus: '',
      textMap: { // 编辑窗口类别信息，当点编辑时窗口显示编辑，点添加按钮时显示创建
        update: '编辑',
        create: '创建'
      },
      dialogPvVisible: false, // 读入数据窗口是否可见
      pvData: [],
      rules: { // 校验规则
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }],
        tigan: [{
          required: true,
          message: '请输入题目',
          trigger: 'blur'
        }],
        rightAns: [{
          required: true,
          message: '请选择选项',
          trigger: 'change'
        }],
        usrAns: [{
          required: true,
          message: '请选择选项',
          trigger: 'change'
        }]
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      // this.listLoading = false
      fetchList(this.listQuery).then(response => {
        this.list = response.data.items
        this.total = response.data.total

        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: '操作成功',
        type: 'success'
      })
      row.status = status
    },
    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        id: undefined,
        importance: 1,
        remark: '',
        timestamp: new Date(),
        title: '',
        status: 'published',
        type: '',
        tigan: '',
        rightAns: '',
        usrAns: ''
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create' // 状态改为创建，弹窗显示创建
      this.dialogFormVisible = true // 显示弹窗
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.temp.id = parseInt(Math.random() * 100) + 1024 // mock a id
          this.temp.author = 'vue-element-admin'
          createArticle(this.temp).then(() => {
            this.list.unshift(this.temp) // unshift(data)把元素加在数组的前面
            this.dialogFormVisible = false
            this.$notify({
              title: 'Success',
              message: 'Created Successfully',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.temp.timestamp = new Date(this.temp.timestamp)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    AnswerQuestion(row) {
      this.temp = Object.assign({}, row) // copy obj
      this.QuestionVisible = true
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
          updateArticle(tempData).then((mess) => {
            const index = this.list.findIndex(v => v.id === this.temp.id)
            this.list.splice(index, 1, this.temp)
            this.dialogFormVisible = false
            if (mess === 'success') {
              this.$notify({
                title: 'Success',
                message: 'Update Successfully',
                type: 'success',
                duration: 2000
              })
            } else {
              this.$notify({
                title: 'Fail',
                message: 'Update Failed',
                type: 'fail',
                duration: 2000
              })
            }
          })
        }
      })
    },
    handleDelete(row, index) {
      this.$notify({
        title: 'Success',
        message: 'Delete Successfully',
        type: 'success',
        duration: 2000
      })
      this.list.splice(index, 1)
    },
    handleFetchPv(pv) {
      fetchPv(pv).then(response => {
        this.pvData = response.data.pvData
        this.dialogPvVisible = true
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
        const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
        const data = this.formatJson(filterVal)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'table-list'
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal) {
      return this.list.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },
    getSortClass: function(key) {
      const sort = this.listQuery.sort
      return sort === `+${key}` ? 'ascending' : 'descending'
    },
    // 添加方法
    submitAns() {
      var options = {
        url: '', // 服务器地址
        data: this.usrAns, // 用户答案
        method: 'post',
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      axios(options).then(function(resp) { // resp返回判断结果true/false
        this.AnsRes = resp
        if (resp) {
          alert('回答正确！')
        } else {
          alert('回答错误！' + this.rightAns)
        }
      }).catch(this.AnsResFail)
      this.QuestionVisible = false
    },
    AnsResFail() {
      alert('连接失败！')
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    }
  }
}
</script>
// 添加
<style>
.el-upload__tip {
  line-height: 1.2;
}

</style>
