<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="测试标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入测试标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入创建者id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="测试标签，1普通测试，2考试" prop="testLabel">
        <el-input
          v-model="queryParams.testLabel"
          placeholder="请输入测试标签，1普通测试，2考试"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动类型标签" prop="typeLabel">
        <el-input
          v-model="queryParams.typeLabel"
          placeholder="请输入活动类型标签"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="共享协议" prop="shareProtocol">
        <el-input
          v-model="queryParams.shareProtocol"
          placeholder="请输入共享协议"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应用环节" prop="process">
        <el-input
          v-model="queryParams.process"
          placeholder="请输入应用环节"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属章节" prop="chapterId">
        <el-input
          v-model="queryParams.chapterId"
          placeholder="请输入所属章节"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布时间" prop="publishDate">
        <el-date-picker clearable
          v-model="queryParams.publishDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择发布时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="截至时间" prop="deadline">
        <el-date-picker clearable
          v-model="queryParams.deadline"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择截至时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="总分" prop="totalScore">
        <el-input
          v-model="queryParams.totalScore"
          placeholder="请输入总分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="已批人数" prop="corrrcted">
        <el-input
          v-model="queryParams.corrrcted"
          placeholder="请输入已批人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="未交人数" prop="unpaid">
        <el-input
          v-model="queryParams.unpaid"
          placeholder="请输入未交人数"
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
          v-hasPermi="['classroom:test:add']"
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
          v-hasPermi="['classroom:test:edit']"
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
          v-hasPermi="['classroom:test:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['classroom:test:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="testList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="测试id" align="center" prop="testId" />
      <el-table-column label="测试标题" align="center" prop="title" />
      <el-table-column label="测试内容" align="center" prop="content" />
      <el-table-column label="创建者id" align="center" prop="userId" />
      <el-table-column label="测试标签，1普通测试，2考试" align="center" prop="testLabel" />
      <el-table-column label="活动类型标签" align="center" prop="typeLabel" />
      <el-table-column label="共享协议" align="center" prop="shareProtocol" />
      <el-table-column label="应用环节" align="center" prop="process" />
      <el-table-column label="所属章节" align="center" prop="chapterId" />
      <el-table-column label="发布时间" align="center" prop="publishDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="截至时间" align="center" prop="deadline" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deadline, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="总分" align="center" prop="totalScore" />
      <el-table-column label="已批人数" align="center" prop="corrrcted" />
      <el-table-column label="未交人数" align="center" prop="unpaid" />
      <el-table-column label="int	状态，0禁用，1激活" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['classroom:test:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['classroom:test:remove']"
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

    <!-- 添加或修改测试管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="测试标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入测试标题" />
        </el-form-item>
        <el-form-item label="测试内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="创建者id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入创建者id" />
        </el-form-item>
        <el-form-item label="测试标签，1普通测试，2考试" prop="testLabel">
          <el-input v-model="form.testLabel" placeholder="请输入测试标签，1普通测试，2考试" />
        </el-form-item>
        <el-form-item label="活动类型标签" prop="typeLabel">
          <el-input v-model="form.typeLabel" placeholder="请输入活动类型标签" />
        </el-form-item>
        <el-form-item label="共享协议" prop="shareProtocol">
          <el-input v-model="form.shareProtocol" placeholder="请输入共享协议" />
        </el-form-item>
        <el-form-item label="应用环节" prop="process">
          <el-input v-model="form.process" placeholder="请输入应用环节" />
        </el-form-item>
        <el-form-item label="所属章节" prop="chapterId">
          <el-input v-model="form.chapterId" placeholder="请输入所属章节" />
        </el-form-item>
        <el-form-item label="发布时间" prop="publishDate">
          <el-date-picker clearable
            v-model="form.publishDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="截至时间" prop="deadline">
          <el-date-picker clearable
            v-model="form.deadline"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择截至时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="总分" prop="totalScore">
          <el-input v-model="form.totalScore" placeholder="请输入总分" />
        </el-form-item>
        <el-form-item label="已批人数" prop="corrrcted">
          <el-input v-model="form.corrrcted" placeholder="请输入已批人数" />
        </el-form-item>
        <el-form-item label="未交人数" prop="unpaid">
          <el-input v-model="form.unpaid" placeholder="请输入未交人数" />
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
import { listTest, getTest, delTest, addTest, updateTest } from "@/api/classroom/test";

export default {
  name: "Test",
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
      // 测试管理表格数据
      testList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        content: null,
        userId: null,
        testLabel: null,
        typeLabel: null,
        shareProtocol: null,
        process: null,
        chapterId: null,
        publishDate: null,
        deadline: null,
        totalScore: null,
        corrrcted: null,
        unpaid: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "测试标题不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建者不能为空", trigger: "blur" }
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
    /** 查询测试管理列表 */
    getList() {
      this.loading = true;
      listTest(this.queryParams).then(response => {
        this.testList = response.rows;
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
        testId: null,
        title: null,
        content: null,
        userId: null,
        testLabel: null,
        typeLabel: null,
        shareProtocol: null,
        process: null,
        chapterId: null,
        publishDate: null,
        deadline: null,
        totalScore: null,
        corrrcted: null,
        unpaid: null,
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
      this.ids = selection.map(item => item.testId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加测试管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const testId = row.testId || this.ids
      getTest(testId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改测试管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.testId != null) {
            updateTest(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTest(this.form).then(response => {
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
      const testIds = row.testId || this.ids;
      this.$modal.confirm('是否确认删除测试管理编号为"' + testIds + '"的数据项？').then(function() {
        return delTest(testIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('classroom/test/export', {
        ...this.queryParams
      }, `test_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
