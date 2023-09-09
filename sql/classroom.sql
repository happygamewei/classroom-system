drop table if exists tb_course;
CREATE TABLE tb_course (
    course_id       BIGINT          NOT NULL PRIMARY KEY        COMMENT '课程id，主键',
    name            VARCHAR(64)     NOT NULL                    COMMENT '课程名称',
    code            VARCHAR(10)     NOT NULL                    COMMENT '课程码',
    teach_class     VARCHAR(32)     NOT NULL                     COMMENT '教学班级',
    school_year     VARCHAR(20)     NOT NULL                     COMMENT '学年',
    term            VARCHAR(20)     NOT NULL                     COMMENT '学期',
    teach_mode      char(1)         default null                COMMENT '授课模式，1线上，2线下，3混合',
    credit_hours    INT             default null                COMMENT '学时数',
    introduce       TEXT            default null                COMMENT '课程介绍',
    place           VARCHAR(255)    default null                COMMENT '授课地点',
    institutional   char(1)         default null                COMMENT '机构相关，0不相关，1相关',
    join_number     INT             default null                COMMENT '参加人数',
    status          char(1)         default ''                 COMMENT '状态，0禁用，1激活',
    create_by       VARCHAR(32)     NOT NULL                    COMMENT '创建人',
    create_time     TIMESTAMP       NOT NULL                    COMMENT '创建时间',
    update_by       VARCHAR(32)     default null                COMMENT '更新人',
    update_time     TIMESTAMP       default null                COMMENT '更新时间'
) engine=innodb auto_increment=1 comment = '课程表';

drop table if exists tb_course_user;
CREATE TABLE tb_course_user (
    id              BIGINT          NOT NULL PRIMARY KEY        COMMENT 'id，主键',
    user_id         BIGINT          NOT NULL                    COMMENT '用户id，外键',
    course_id       BIGINT          NOT NULL                    COMMENT '课程id，外键',
    type            char(1)         default ''                  COMMENT '类型，1是我教的，2是我协助的，3学的'
)engine=innodb auto_increment=1 comment = '课程用户关系表';

drop table if exists tb_course_chapter;
CREATE TABLE tb_course_chapter (
    id              BIGINT          NOT NULL PRIMARY KEY        COMMENT 'id，主键',
    chapter_id      BIGINT          NOT NULL                    COMMENT '章节id，外键',
    course_id       BIGINT          NOT NULL                    COMMENT '课程id，外键'
)engine=innodb auto_increment=1 comment = '课程目录关系表';

CREATE TABLE tb_chapter (
    chapter_id      BIGINT      NOT NULL                COMMENT '目录id，主键',
    parent_id       BIGINT      default null            COMMENT '父目录id',
    user_id         BIGINT      default null            COMMENT '创建者id',
    name            VARCHAR(20) NOT NULL                COMMENT '章节/小节名称',
    teach_mode      CHAR(1)     default ''              COMMENT '授课模式，1线上，2线下，3混合',
    credit_hours    INT         default null            COMMENT '学时数',
    type            CHAR(1)     default ''              COMMENT '目录类型，1章节，2小节',
    sort            INT         default null            COMMENT '顺序',
    status          CHAR(1)     default ''              COMMENT '状态，0禁用，1激活',
    create_by       VARCHAR(20) NOT NULL                COMMENT '创建人',
    create_time     TIMESTAMP   NOT NULL                COMMENT '创建时间',
    update_by       VARCHAR(20) default null            COMMENT '更新人',
    update_time     TIMESTAMP   default null            COMMENT '更新时间',
    PRIMARY KEY (chapter_id)
)engine=innodb auto_increment=1 comment = '章节表';

drop table if exists tb_chapter_content;
CREATE TABLE tb_chapter_content (
    id              BIGINT      NOT NULL PRIMARY KEY    COMMENT '主键',
    chapter_id      BIGINT      NOT NULL                COMMENT '章节id，外键',
    content_id      BIGINT      NOT NULL                COMMENT '内容id，外键',
    content_type    CHAR(1)     default ''              COMMENT '类型，1作业，2测试，3资料，4公告，5话题'
) engine=innodb auto_increment=1 comment = '章节内容表';

CREATE TABLE tb_comment (
    comment_id      BIGINT      NOT NULL                COMMENT '评论id，主键',
    parent_id       BIGINT      default null            COMMENT '父评论id',
    type_id         BIGINT      NOT NULL                COMMENT '被评论表的id',
    content         TEXT        NOT NULL                COMMENT '内容',
    to_comment_user_id BIGINT   default null            COMMENT '所回复评论的userId',
    to_comment_id   BIGINT      default null            COMMENT '回复评论的id',
    likes_number    INT         default null            COMMENT '评论点赞数',
    status          CHAR(1)     default ''              COMMENT '状态，0禁用，1激活',
    create_by       VARCHAR(20) NOT NULL                COMMENT '创建人',
    create_time     TIMESTAMP   NOT NULL                COMMENT '创建时间',
    update_by       VARCHAR(20) default null            COMMENT '更新人',
    update_time     TIMESTAMP   default null            COMMENT '更新时间',
    PRIMARY KEY (comment_id)
)engine=innodb auto_increment=1 comment = '评论表';

CREATE TABLE tb_likes (
    id              BIGINT      NOT NULL                COMMENT '点赞记录id，主键',
    comment_id      BIGINT      NOT NULL                COMMENT '评论id，非空',
    user_id         BIGINT      NOT NULL                COMMENT '点赞人id，非空',
    type            CHAR(1)     default ''              COMMENT '内容类型，0是没点，1点赞',
    PRIMARY KEY (id)
)engine=innodb auto_increment=1 comment = '点赞记录表';

CREATE TABLE tb_comment_content (
    id              BIGINT      NOT NULL                COMMENT '评论内容关系id，主键',
    comment_id      BIGINT      NOT NULL                COMMENT '评论id，非空',
    content_id      BIGINT      NOT NULL                COMMENT '内容id，非空',
    type            CHAR(1)     default ''              COMMENT '内容类型，1是作业，2是测试，3是公告，4是话题',
    PRIMARY KEY (id)
)engine=innodb auto_increment=1 comment = ' 评论内容关系表';

drop table if exists tb_homework;
create table tb_homework (
    homework_id          bigint(20)      not null          comment '作业id',
    title                varchar(30)     not null          comment '作业标题',
    content              longtext        default null      comment '作业内容',
    user_id              bigint(20)                        comment '创建者id',
    type_label           varchar(30)                       comment '活动类型标签',
    share_protocol       varchar(30)                       comment '共享协议',
    process              varchar(30)                       comment '应用环节',
    chapter_id           int(30)                           comment '所属章节',
    If_publish           varchar(1)                        comment '发布状态，未发布为0，发布为1',
    publish_date         datetime                          comment '发布时间',
    deadline             datetime                          comment '截至时间',
    total_score          int(5)                            comment '总分',
    approved             int(30)         default 0         comment '已批完',
    unpaid               int(30)                           comment '未交',
    is_check             varchar(1)                        comment '是否查重，0否，1是',
    check_number         int(5)                            comment '查重警戒值',
    If_back              varchar(1)                        comment '是否自动打回，0否1是',
    status               varchar(1)                        comment '状态，0禁用，1激活',
    create_by            varchar(64)     default ''        comment '创建者',
    create_time 	      datetime                          comment '创建时间',
    update_by            varchar(64)     default ''        comment '更新者',
    update_time          datetime                          comment '更新时间',
    primary key (homework_id )
) engine=innodb auto_increment=100 comment = '发布作业表';

drop table if exists tb_sudent_homework;
create table tb_sudent_homework (
    sh_id                bigint(30)      not null          comment '学生作业表id',
    homework_id          int(30)         not null          comment '作业id',
    student_id           int(30)         not null          comment '学生id',
    file_path            varchar(270)                      comment '上传文件路径',
    submit_status        varchar(1)                            comment '提交状态，0未提交，1提交',
    submit_time	         datetime                          comment '提交时间',
    word_number          int(10)                           comment '字数',
    check_number         int(10)                           comment '批阅次数',
    urge_submit          int(10)                           comment '催交(次数)',
    grade                int(5)                            comment '成绩',
    work_status          varchar(1)                            comment '作业状态，1批改，2打回',
    status               varchar(1)                            comment '状态，0禁用，1激活',
    create_by            varchar(64)     default ''        comment '创建者',
    create_time 	        datetime                          comment '创建时间',
    update_by            varchar(64)     default ''        comment '更新者',
    update_time          datetime                          comment '更新时间',
    primary key (sh_id)
) engine=innodb auto_increment=100 comment = '学生作业表';

drop table if exists tb_home_pass_check;
create table tb_home_pass_check (
    home_id              bigint(30)      not null          comment '作业反馈id',
    student_id           int(30)         not null          comment '学生id',
    teacher_id           int(30)         not null          comment '老师id',
    if_pass              varchar(1)                        comment '批改状态，0未批，1已批',
    if_check             varchar(1)                        comment '上交状态，0未交，1已交',
    status               varchar(1)                        comment '状态，0禁用，1激活',
    create_by            varchar(64)     default ''        comment '创建者',
    create_time 	     datetime                          comment '创建时间',
    update_by            varchar(64)     default ''        comment '更新者',
    update_time          datetime                          comment '更新时间',
    primary key (home_id,student_id,teacher_id)
) engine=innodb auto_increment=100 comment = '测试上交批改表';

DROP TABLE IF EXISTS `tb_topic`;
CREATE TABLE `tb_topic`  (
  `topic_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '话题id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '话题标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '话题内容',
  `type_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动类型标签',
  `share_protocol` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '共享协议',
  `process` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '应用环节',
  `chapter_id` int(0) NULL DEFAULT NULL COMMENT '所属章节',
  `publish_date` timestamp(0) NULL DEFAULT NULL COMMENT '发布时间',
  `deadline` timestamp(0) NULL DEFAULT NULL COMMENT '截至时间',
  `total_score` int(0) NULL DEFAULT NULL COMMENT '总分',
  `least_reply_number` int(0) NULL DEFAULT NULL COMMENT '至少回复次数',
  `join_number` int(0) NULL DEFAULT NULL COMMENT '已参与数量',
  `like_count` int(0) NULL DEFAULT NULL COMMENT '点赞次数',
  `status` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '话题表' ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `tb_user_topic`;
CREATE TABLE `tb_user_topic`  (
  `user_topic_id` bigint(0) NOT NULL COMMENT '用户话题id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `topic_id` bigint(0) NULL DEFAULT NULL COMMENT '话题id',
  `isReadLabel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否已参与 0未参与，1已参与',
  `isLike` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否已点赞 0未点赞，1已点赞',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态，0禁用，1激活',
  `create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_topic_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

drop table if exists tb_test;
create table tb_test(
    test_id            bigint         not null          comment '测试id',
    title                varchar(30)     not null          comment '测试标题',
    content              longtext        default null      comment '测试内容',
    user_id           int(30)                       comment '创建者id',
    test_label       varchar(30)                       comment '测试标签，1普通测试，2考试',
    type_label              varchar(30)                       comment '活动类型标签',
    share_protocol           varchar(30)                           comment '共享协议',
    process         varchar(30)                          comment '应用环节',
    chapter_id          int(30)                           comment '所属章节',
    publish_date               timestamp                            comment '发布时间',
    deadline              timestamp                          comment '截至时间',
    Total_score           int(30)                         comment '总分',
    corrrcted             int(30)                         comment '已批人数',
    unpaid                int(30)                         comment '未交人数',
    status               int(1)                            comment 'int	状态，0禁用，1激活',
    create_by            varchar(64)     default ''        comment '创建者',
    create_time 	       timestamp                        comment '创建时间',
    update_by            varchar(64)     default ''        comment '更新者',
    update_time          timestamp                        comment '更新时间',
    primary key (test_id)
) engine=innodb auto_increment=1 comment = '测试表';

drop table if exists tb_test_pass_check;
create table tb_test_pass_check(
    test_id            bigint         not null          comment '测试id',
    student_id         bigint         not null          comment '学生id',
    teacher_id         bigint        not null      comment '老师id',
    if_pass           varchar(30)                      comment '批改状态，0未批，1已批',
    if_check           varchar(30)                       comment '上交状态，0未交，1已交',
    status               int(1)                            comment 'int	状态，0禁用，1激活',
    create_by            varchar(64)     default ''        comment '创建者',
    create_time 	      timestamp                         comment '创建时间',
    update_by            varchar(64)     default ''        comment '更新者',
    update_time         timestamp                         comment '更新时间',
    primary key (test_id,student_id,teacher_id)
) engine=innodb auto_increment=1 comment = '测试上交批改表';

DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice`  (
  `notice_id` bigint NOT NULL COMMENT '公告id',
  `title` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '公告内容',
  `type_label` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '活动类型标签',
  `share_protocol` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '共享协议',
  `process` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '应用环节',
  `chapter_id` bigint NULL DEFAULT NULL COMMENT '所属章节',
  `publish_date` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `read_number` int NULL DEFAULT NULL COMMENT '已读数量',
  `is_top` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否置顶，0否，1是',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态，0禁用，1激活',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `document_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件附件',
  `picture_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片附件',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告表' ROW_FORMAT = DYNAMIC;


drop table if exists tb_resource;
CREATE TABLE tb_resource (
    resource_id     BIGINT          NOT NULL PRIMARY KEY        COMMENT '资料id，主键',
    title           VARCHAR(64)     NOT NULL                    COMMENT '资料标题',
    path            VARCHAR(64)     NOT NULL                    COMMENT '资料地址',
    size            VARCHAR(20)     default null                COMMENT '资料大小',
    user_id         BIGINT          default null                COMMENT '创建者id',
    is_download     CHAR(1)         default ''                  COMMENT '是否允许下载，0否，1是',
    type            INT             default null                COMMENT '类型',
    type_label      VARCHAR(50)     default null                COMMENT '活动类型标签',
    share_protocol  VARCHAR(50)     default null                COMMENT '共享协议',
    process         VARCHAR(50)     default null                COMMENT '应用环节',
    chapter_id      BIGINT          default null                COMMENT '所属章节',
    publish_date    TIMESTAMP       default null                COMMENT '发布时间',
    deadline        TIMESTAMP       default null                COMMENT '截至时间',
    total_score     INT             default null                COMMENT '总分',
    completed       INT             default null                COMMENT '已完成人数',
    studying        INT             default null                COMMENT '学习中人数',
    nostudying      INT             default null                COMMENT '未学习人数',
    status          CHAR(1)          default ''                 COMMENT '状态，0禁用，1激活',
    create_by       VARCHAR(20)     NOT NULL                    COMMENT '创建人',
    create_time     TIMESTAMP       NOT NULL                    COMMENT '创建时间',
    update_by       VARCHAR(20)     default null                COMMENT '更新人',
    update_time     TIMESTAMP       default null                COMMENT '更新时间'
)engine=innodb auto_increment=1 comment = '资料表';

drop table if exists tb_exam_record;
CREATE TABLE tb_exam_record (
    exam_record_id  BIGINT          NOT NULL PRIMARY KEY        COMMENT '考试记录id，主键',
    employee_account BIGINT         NOT NULL                    COMMENT '考试用户账号',
    qduration       INT                                         COMMENT '考试时长',
    paper_id        BIGINT                                      COMMENT '考试试卷id',
    score           DOUBLE                                      COMMENT '考试得分',
    status          INT                                         COMMENT '状态，0禁用，1激活',
    create_by       VARCHAR(20)    NOT NULL                    COMMENT '创建人',
    create_time     TIMESTAMP       NOT NULL                    COMMENT '创建时间',
    update_by       VARCHAR(20)                                COMMENT '更新人',
    update_time     TIMESTAMP                                   COMMENT '更新时间'
)engine=innodb auto_increment=1 comment = '考试记录表';





