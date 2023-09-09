<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课程名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入课程名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入课程码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教学班级" prop="teachClass">
        <el-input
          v-model="queryParams.teachClass"
          placeholder="请输入教学班级"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学时数" prop="creditHours">
        <el-input
          v-model="queryParams.creditHours"
          placeholder="请输入学时数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="授课地点" prop="place">
        <el-input
          v-model="queryParams.place"
          placeholder="请输入授课地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="参加人数" prop="joinNumber">
        <el-input
          v-model="queryParams.joinNumber"
          placeholder="请输入参加人数"
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
          v-hasPermi="['classroom:course:add']"
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
          v-hasPermi="['classroom:course:edit']"
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
          v-hasPermi="['classroom:course:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['classroom:course:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课程id，主键" align="center" prop="courseId" />
      <el-table-column label="课程名称" align="center" prop="name" />
      <el-table-column label="课程码" align="center" prop="code" />
      <el-table-column label="教学班级" align="center" prop="teachClass" />
      <el-table-column label="学年" align="center" prop="schoolYear" />
      <el-table-column label="学期" align="center" prop="term" />
      <el-table-column label="授课模式" align="center" prop="teachMode" />
      <el-table-column label="学时数" align="center" prop="creditHours" />
      <el-table-column label="课程介绍" align="center" prop="introduce" />
      <el-table-column label="授课地点" align="center" prop="place" />
      <el-table-column label="机构相关" align="center" prop="institutional" />
      <el-table-column label="参加人数" align="center" prop="joinNumber" />
      <el-table-column label="状态，0禁用，1激活" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['classroom:course:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['classroom:course:remove']"
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

    <!-- 添加或修改课程管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="课程码" prop="code">
          <el-input v-model="form.code" placeholder="请输入课程码" />
        </el-form-item>
        <el-form-item label="教学班级" prop="teachClass">
          <el-input v-model="form.teachClass" placeholder="请输入教学班级" />
        </el-form-item>
        <el-form-item label="学时数" prop="creditHours">
          <el-input v-model="form.creditHours" placeholder="请输入学时数" />
        </el-form-item>
        <el-form-item label="课程介绍">
          <editor v-model="form.introduce" :min-height="192"/>
        </el-form-item>
        <el-form-item label="授课地点" prop="place">
          <el-input v-model="form.place" placeholder="请输入授课地点" />
        </el-form-item>
        <el-form-item label="参加人数" prop="joinNumber">
          <el-input v-model="form.joinNumber" placeholder="请输入参加人数" />
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
import { listCourse, getCourse, delCourse, addCourse, updateCourse } from "@/api/classroom/course";

export default {
  name: "Course",
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
      // 课程管理表格数据
      courseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        code: null,
        teachClass: null,
        schoolYear: null,
        term: null,
        teachMode: null,
        creditHours: null,
        introduce: null,
        place: null,
        institutional: null,
        joinNumber: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "课程名称不能为空", trigger: "blur" }
        ],
        code: [
          { required: true, message: "课程码不能为空", trigger: "blur" }
        ],
        teachClass: [
          { required: true, message: "教学班级不能为空", trigger: "blur" }
        ],
        schoolYear: [
          { required: true, message: "学年不能为空", trigger: "change" }
        ],
        term: [
          { required: true, message: "学期不能为空", trigger: "change" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询课程管理列表 */
    getList() {
      this.loading = true;
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows;
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
        courseId: null,
        name: null,
        code: null,
        teachClass: null,
        schoolYear: null,
        term: null,
        teachMode: [],
        creditHours: null,
        introduce: null,
        place: null,
        institutional: null,
        joinNumber: null,
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
      this.ids = selection.map(item => item.courseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加课程管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const courseId = row.courseId || this.ids
      getCourse(courseId).then(response => {
        this.form = response.data;
        this.form.teachMode = this.form.teachMode.split(",");
        this.open = true;
        this.title = "修改课程管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.teachMode = this.form.teachMode.join(",");
          if (this.form.courseId != null) {
            updateCourse(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCourse(this.form).then(response => {
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
      const courseIds = row.courseId || this.ids;
      this.$modal.confirm('是否确认删除课程管理编号为"' + courseIds + '"的数据项？').then(function() {
        return delCourse(courseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('classroom/course/export', {
        ...this.queryParams
      }, `course_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
