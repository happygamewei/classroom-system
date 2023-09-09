<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="作业id" prop="homeworkId">
        <el-input
          v-model="queryParams.homeworkId"
          placeholder="请输入作业id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生id" prop="studentId">
        <el-input
          v-model="queryParams.studentId"
          placeholder="请输入学生id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传文件路径" prop="filePath">
        <el-input
          v-model="queryParams.filePath"
          placeholder="请输入上传文件路径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交时间" prop="submitTime">
        <el-date-picker clearable
          v-model="queryParams.submitTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择提交时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="字数" prop="wordNumber">
        <el-input
          v-model="queryParams.wordNumber"
          placeholder="请输入字数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批阅次数" prop="checkNumber">
        <el-input
          v-model="queryParams.checkNumber"
          placeholder="请输入批阅次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="催交(次数)" prop="urgeSubmit">
        <el-input
          v-model="queryParams.urgeSubmit"
          placeholder="请输入催交(次数)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成绩" prop="grade">
        <el-input
          v-model="queryParams.grade"
          placeholder="请输入成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['classroom:studentHomework:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['classroom:studentHomework:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['classroom:studentHomework:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['classroom:studentHomework:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentHomeworkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生作业表id" align="center" prop="shId" />
      <el-table-column label="作业id" align="center" prop="homeworkId" />
      <el-table-column label="学生id" align="center" prop="studentId" />
      <el-table-column label="上传文件路径" align="center" prop="filePath" />
      <el-table-column label="提交状态，0未提交，1提交" align="center" prop="submitStatus" />
      <el-table-column label="提交时间" align="center" prop="submitTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.submitTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="字数" align="center" prop="wordNumber" />
      <el-table-column label="批阅次数" align="center" prop="checkNumber" />
      <el-table-column label="催交(次数)" align="center" prop="urgeSubmit" />
      <el-table-column label="成绩" align="center" prop="grade" />
      <el-table-column label="作业状态，1批改，2打回" align="center" prop="workStatus" />
      <el-table-column label="状态，0禁用，1激活" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['classroom:studentHomework:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['classroom:studentHomework:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改学生作业对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="作业id" prop="homeworkId">
          <el-input v-model="form.homeworkId" placeholder="请输入作业id" />
        </el-form-item>
        <el-form-item label="学生id" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学生id" />
        </el-form-item>
        <el-form-item label="上传文件路径" prop="filePath">
          <el-input v-model="form.filePath" placeholder="请输入上传文件路径" />
        </el-form-item>
        <el-form-item label="提交时间" prop="submitTime">
          <el-date-picker clearable
            v-model="form.submitTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择提交时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="字数" prop="wordNumber">
          <el-input v-model="form.wordNumber" placeholder="请输入字数" />
        </el-form-item>
        <el-form-item label="批阅次数" prop="checkNumber">
          <el-input v-model="form.checkNumber" placeholder="请输入批阅次数" />
        </el-form-item>
        <el-form-item label="催交(次数)" prop="urgeSubmit">
          <el-input v-model="form.urgeSubmit" placeholder="请输入催交(次数)" />
        </el-form-item>
        <el-form-item label="成绩" prop="grade">
          <el-input v-model="form.grade" placeholder="请输入成绩" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStudentHomework, getStudentHomework, delStudentHomework, addStudentHomework, updateStudentHomework } from "@/api/classroom/studentHomework";

export default {
  name: "StudentHomework",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 学生作业表格数据
      studentHomeworkList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        homeworkId: null,
        studentId: null,
        filePath: null,
        submitStatus: null,
        submitTime: null,
        wordNumber: null,
        checkNumber: null,
        urgeSubmit: null,
        grade: null,
        workStatus: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        homeworkId: [
          { required: true, message: "作业id不能为空", trigger: "blur" }
        ],
        studentId: [
          { required: true, message: "学生id不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询学生作业列表 */
    getList() {
      this.loading = true;
      listStudentHomework(this.queryParams).then(response => {
        this.studentHomeworkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        shId: null,
        homeworkId: null,
        studentId: null,
        filePath: null,
        submitStatus: null,
        submitTime: null,
        wordNumber: null,
        checkNumber: null,
        urgeSubmit: null,
        grade: null,
        workStatus: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.shId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生作业";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const shId = row.shId || this.ids
      getStudentHomework(shId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生作业";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.shId != null) {
            updateStudentHomework(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStudentHomework(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const shIds = row.shId || this.ids;
      this.$modal.confirm('是否确认删除学生作业编号为"' + shIds + '"的数据项？').then(function() {
        return delStudentHomework(shIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('classroom/studentHomework/export', {
        ...this.queryParams
      }, `studentHomework_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
