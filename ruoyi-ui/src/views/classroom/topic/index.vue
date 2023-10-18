<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="话题标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入话题标题"
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
      <el-form-item label="至少回复次数" prop="leastReplyNumber">
        <el-input
          v-model="queryParams.leastReplyNumber"
          placeholder="请输入至少回复次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="已参与数量" prop="joinNumber">
        <el-input
          v-model="queryParams.joinNumber"
          placeholder="请输入已参与数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="点赞次数" prop="likeCount">
        <el-input
          v-model="queryParams.likeCount"
          placeholder="请输入点赞次数"
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
          v-hasPermi="['classroom:topic:add']"
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
          v-hasPermi="['classroom:topic:edit']"
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
          v-hasPermi="['classroom:topic:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['classroom:topic:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="topicList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="话题id" align="center" prop="topicId" />
      <el-table-column label="话题标题" align="center" prop="title" />
      <el-table-column label="话题内容" align="center" prop="content" />
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
      <el-table-column label="至少回复次数" align="center" prop="leastReplyNumber" />
      <el-table-column label="已参与数量" align="center" prop="joinNumber" />
      <el-table-column label="点赞次数" align="center" prop="likeCount" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['classroom:topic:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['classroom:topic:remove']"
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

    <!-- 添加或修改话题管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="话题标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入话题标题" />
        </el-form-item>
        <el-form-item label="话题内容">
          <editor v-model="form.content" :min-height="192"/>
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
        <el-form-item label="至少回复次数" prop="leastReplyNumber">
          <el-input v-model="form.leastReplyNumber" placeholder="请输入至少回复次数" />
        </el-form-item>
        <el-form-item label="已参与数量" prop="joinNumber">
          <el-input v-model="form.joinNumber" placeholder="请输入已参与数量" />
        </el-form-item>
        <el-form-item label="点赞次数" prop="likeCount">
          <el-input v-model="form.likeCount" placeholder="请输入点赞次数" />
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
import { listTopic, getTopic, delTopic, addTopic, updateTopic } from "@/api/classroom/topic";

export default {
  name: "Topic",
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
      // 话题管理表格数据
      topicList: [],
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
        typeLabel: null,
        shareProtocol: null,
        process: null,
        chapterId: null,
        publishDate: null,
        deadline: null,
        totalScore: null,
        leastReplyNumber: null,
        joinNumber: null,
        likeCount: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "话题标题不能为空", trigger: "blur" }
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
    /** 查询话题管理列表 */
    getList() {
      this.loading = true;
      listTopic(this.queryParams).then(response => {
        this.topicList = response.rows;
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
        topicId: null,
        title: null,
        content: null,
        typeLabel: null,
        shareProtocol: null,
        process: null,
        chapterId: null,
        publishDate: null,
        deadline: null,
        totalScore: null,
        leastReplyNumber: null,
        joinNumber: null,
        likeCount: null,
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
      this.ids = selection.map(item => item.topicId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加话题管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const topicId = row.topicId || this.ids
      getTopic(topicId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改话题管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.topicId != null) {
            updateTopic(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTopic(this.form).then(response => {
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
      const topicIds = row.topicId || this.ids;
      this.$modal.confirm('是否确认删除话题管理编号为"' + topicIds + '"的数据项？').then(function() {
        return delTopic(topicIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('classroom/topic/export', {
        ...this.queryParams
      }, `topic_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
