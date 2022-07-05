<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="数据服务器IP" suffix-icon="el-icon-search" v-model="dbHostIp"></el-input>
      <el-input style="width: 200px" placeholder="数据库名" suffix-icon="el-icon-message" class="ml-5" v-model="dbName"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-upload :action="'http://' + serverIp + ':9090/backupDB/import'" :show-file-list="false" accept="xlsx"
                 :on-success="handleExcelImportSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button type="primary" @click="exp" class="ml-5">导出 <i class="el-icon-top"></i></el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'"
              @selection-change="handleSelectionChange" ref="backupDBForm">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="55"></el-table-column>
      <el-table-column prop="dbHostIp" label="数据库服务器IP" width="120"></el-table-column>
      <el-table-column prop="dbUsername" label="数据库用户名"></el-table-column>
      <el-table-column prop="dbPassword" label="数据库密码" width="100"></el-table-column>
      <el-table-column prop="dbPort" label="数据库端口"></el-table-column>
      <el-table-column prop="dbName" label="数据库名"></el-table-column>
      <el-table-column prop="tarDir" label="备份文件夹"></el-table-column>
      <el-table-column prop="restoreFile" label="恢复文件"></el-table-column>
      <el-table-column label="操作" width="500" align="left">
        <template slot-scope="scope">
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定吗？"
              @confirm="backupDB(scope.row)"
          >
            <el-button type="primary" slot="reference">备份数据库 <i class="el-icon-document"></i></el-button>
            <el-button type="primary" @click="restoreDB(scope.row)" slot="reference">还原数据库 <i class="el-icon-document"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

    <el-dialog title="数据库服务器信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="120px" size="small">
        <el-form-item label="数据库服务器IP">
          <el-input v-model="form.dbHostIp" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="数据库用户名">
          <el-input v-model="form.dbUsername" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="数据库密码">
          <el-input v-model="form.dbPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="数据库端口">
          <el-input v-model="form.dbPort" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="数据库名">
          <el-input v-model="form.dbName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="备份文件夹">
          <el-input v-model="form.tarDir" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="恢复文件">
          <el-input v-model="form.restoreFile" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {serverIp} from "../../../public/config";

export default {
  name: "DataBase",
  data() {
    return {
      serverIp: serverIp,
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      dbHostIp: "",
      dbUsername: "",
      dbPassword:"",
      dbPort: "",
      dbName: "",
      tarDir: "",
      restoreFile:"",
      form: {},
      dialogFormVisible: false,
      multipleSelection: [],
      roles: [],
      vis: false,
      stuVis: false
    }
  },
  created() {
    this.load()
  },
  methods: {
    backupDB(row) {
      this.request.post("/backup/backupDatabase",row)
    },
    restoreDB(row) {
      this.request.post("/backup/restoreDatabase",row)
    },
    lookStuCourse(stuCourses) {
      this.stuCourses = stuCourses
      this.stuVis = true
    },
    load() {
      this.request.get("/backup/DBpage", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          dbHostIp: this.dbHostIp,
          dbName: this.dbName,
        }
      }).then(res => {

        this.tableData = res.data.records
        this.total = res.data.total

      })

      this.request.get("/role").then(res => {
        this.roles = res.data
      })
    },
    save() {
      this.request.post("/backup/saveBackupDB", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    del(id) {
      this.request.delete("/backup/deleteDB/" + id).then(res => {
        if (res.code === '200') {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
      this.request.post("/backup/delDB/batch", ids).then(res => {
        if (res.code === '200') {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },
    reset() {
      this.dbHostIp = ""
      this.dbName = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    exp() {
      window.open(`http://${serverIp}:9090/backupDB/export`)
    },
    handleExcelImportSuccess() {
      this.$message.success("导入成功")
      this.load()
    }
  }
}
</script>


<style>
.headerBg {
  background: #eee !important;
}
</style>
