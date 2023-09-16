<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="章节/小节名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入章节/小节名称"
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
      <el-form-item label="顺序" prop="sort">
        <el-input
          v-model="queryParams.sort"
          placeholder="请输入顺序"
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
          v-hasPermi="['classroom:chapter:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="chapterList"
      row-key="chapterId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="章节/小节名称" align="center" prop="name" />
      <el-table-column label="授课模式" align="center" prop="teachMode" />
      <el-table-column label="学时数" align="center" prop="creditHours" />
      <el-table-column label="目录类型" align="center" prop="type" />
      <el-table-column label="顺序" align="center" prop="sort" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['classroom:chapter:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['classroom:chapter:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['classroom:chapter:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改章节对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程" prop="CourseSmallVo">
          <el-select v-model="form.courseId" placeholder="请选择课程" style="width: 250px">
            <el-option
              v-for="item in courseInfoList"
              :key="item.courseId"
              :label="item.name"
              :value="item.courseId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="父目录" prop="parentId">
          <treeselect v-model="form.parentId" :options="chapterOptions" :normalizer="normalizer" placeholder="请选择父目录" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入章节/小节名称" />
        </el-form-item>
        <el-form-item label="学时数" prop="creditHours">
          <el-input v-model="form.creditHours" placeholder="请输入学时数" />
        </el-form-item>
        <el-form-item label="顺序" prop="sort">
          <el-input-number v-model="form.sort" controls-position="right" :min="0" />
        </el-form-item>
        <el-form-item label="授课模式" prop="teachMode">
          <el-radio-group v-model="form.teachMode">
            <el-radio
              v-for="dict in dict.type.class_teach_mode"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
import {listChapter, getChapter, delChapter, addChapter, updateChapter, getCourseInfo} from "@/api/classroom/chapter";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Chapter",
  dicts: ['class_teach_mode'],
  components: {
    Treeselect
  },
  data() {
    return {
      // 课程信息
      courseInfoList: [],
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 章节表格数据
      chapterList: [],
      // 章节树选项
      chapterOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        parentId: null,
        name: null,
        teachMode: null,
        creditHours: null,
        type: null,
        sort: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "章节/小节名称不能为空", trigger: "blur" }
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
    /** 查询章节列表 */
    getList() {
      this.loading = true;
      listChapter(this.queryParams).then(response => {
        this.chapterList = this.handleTree(response.data, "chapterId", "parentId");
        this.loading = false;
      });
    },
    /** 转换章节数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.chapterId,
        label: node.name,
        children: node.children
      };
    },
	/** 查询章节下拉树结构 */
    getTreeselect() {
      listChapter().then(response => {
        this.chapterOptions = [];
        const data = { chapterId: 0, name: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "chapterId", "parentId");
        this.chapterOptions.push(data);
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
        chapterId: null,
        parentId: null,
        userId: null,
        name: null,
        teachMode: [],
        creditHours: null,
        type: null,
        sort: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        courseId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.selectCourseInfo()
      this.reset();
      this.getTreeselect();
      if (row != null && row.chapterId) {
        this.form.parentId = row.chapterId;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加章节";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.parentId = row.chapterId;
      }
      getChapter(row.chapterId).then(response => {
        this.form = response.data;
        this.form.teachMode = this.form.teachMode.split(",");
        this.open = true;
        this.title = "修改章节";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // this.form.teachMode = this.form.teachMode.join(",");
          if (this.form.chapterId != null) {
            updateChapter(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addChapter(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除章节编号为"' + row.chapterId + '"的数据项？').then(function() {
        return delChapter(row.chapterId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    // 查询课程
    selectCourseInfo() {
      getCourseInfo().then((res) => {
        if(res.code === 200){
          this.courseInfoList = res.data
        }
      })
    }
  }
};
</script>
