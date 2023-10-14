package com.ruoyi.classroom.service.impl;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.ruoyi.classroom.domain.*;
import com.ruoyi.classroom.domain.vo.ChapterVo;
import com.ruoyi.classroom.domain.vo.CommentVo;
import com.ruoyi.classroom.domain.vo.ContentVo;
import com.ruoyi.classroom.domain.vo.TopicVo;
import com.ruoyi.classroom.mapper.*;
import com.ruoyi.classroom.utils.ClassRoomConstants;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.service.ITopicService;

/**
 * 话题Service业务层处理
 *
 * @author Yuan
 * @date 2023-09-07
 */
@Service
public class TopicServiceImpl implements ITopicService {
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private CommentContentMapper commentContentMapper;
    @Autowired
    private CourseChapterMapper courseChapterMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserTopicMapper userTopicMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private LikesMapper likesMapper;
    @Autowired
    private ChapterContentMapper chapterContentMapper;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询话题
     *
     * @param topicId 话题主键
     * @return 话题
     */
    @Override
    public Topic selectTopicByTopicId(Long topicId) {


        return topicMapper.selectTopicByTopicId(topicId);
    }

    /**
     * 查询话题列表
     *
     * @param topic 话题
     * @return 话题
     */
    @Override
    public List<Topic> selectTopicList(Topic topic) {
        return topicMapper.selectTopicList(topic);
    }

    /**
     * 新增话题
     *
     * @param topic 话题
     * @return 结果
     */
    @Override
    public void insertTopic(Topic topic, Long userId) {
        System.out.println("共享协议："+topic.getShareProtocol());
        topic.setCreateTime(DateUtils.getNowDate());
        topic.setCreateBy(SecurityUtils.getUsername());
        topic.setUserId(userId);
        Long topicId = topicMapper.insertTopic(topic);
        UserTopic userTopic = new UserTopic();
        userTopic.setTopicId(topic.getTopicId());
        userTopic.setUserId(userId);
        userTopic.setCreateTime(DateUtils.getNowDate());
        userTopic.setCreateBy(SecurityUtils.getUsername());
        userTopicMapper.insertUserTopic(userTopic);
        ChapterContent chapterContent = new ChapterContent();
        chapterContent.setChapterId(topic.getChapterId());
        chapterContent.setContentId(topicId);
        chapterContent.setContentType("5");
        chapterContentMapper.insertChapterContent(chapterContent);

    }

    /**
     * 修改话题
     *
     * @param topic 话题
     * @return 结果
     */
    @Override
    public int updateTopic(Topic topic) {
        topic.setUpdateTime(DateUtils.getNowDate());
        return topicMapper.updateTopic(topic);
    }

    /**
     * 批量删除话题
     *
     * @param topicIds 需要删除的话题主键
     * @return 结果
     */
    @Override
    public int deleteTopicByTopicIds(Long[] topicIds) {
        return topicMapper.deleteTopicByTopicIds(topicIds);
    }

    /**
     * 删除话题信息
     *
     * @param topicId 话题主键
     * @return 结果
     */
    @Override
    public int deleteTopicByTopicId(Long topicId) {
        return topicMapper.deleteTopicByTopicId(topicId);
    }

    /**
     * 查找该课程的全部话题
     *
     * @param courseId
     * @return
     */
    @Override
    public List<TopicVo> findChapterByCourseById(Long courseId) {
        List<CourseChapter> courseChapters = courseChapterMapper.findChapterByCourseById(courseId);
        List<TopicVo> topicVos = new ArrayList<>();
        courseChapters.forEach(courseChapter -> {
            List<Topic> topics = topicMapper.findTopicByChapterId(courseChapter.getChapterId());
            System.out.println(courseChapter.getChapterId()+"----"+topics);
            if (topics != null) {
                topics.forEach(topic -> {
                    TopicVo topicVo = new TopicVo();
                    topicVo.setTopicId(topic.getTopicId());
                    topicVo.setTitle(topic.getTitle());
                    topicVo.setDeadline(topic.getDeadline());
                    topicVo.setPublishDate(topic.getPublishDate());
                    topicVo.setJoinNumber(topic.getJoinNumber());
                    topicVo.setNoJoinNumber(courseMapper.selectCourseByCourseId(courseId).getJoinNumber() - topic.getJoinNumber());
                    topicVo.setCommentCount(commentContentMapper.findContentCountByTopic(topic.getTopicId()).size());
                    topicVos.add(topicVo);
                });


            }
        });
        System.out.println(topicVos);
        return topicVos;
    }

    /**
     * 查询该话题评论的数量
     *
     * @param topicId
     * @return
     */
    @Override
    public int findContentCountByTopic(Long topicId) {
        List<CommentContent> commentContents = commentContentMapper.findContentCountByTopic(topicId);
        return commentContents.size();
    }

    /**
     * 查询该话题没有父节点的评论
     *
     * @param topicId
     * @return
     */
    @Override
    public List<Comment> findContentsByTopic(Long topicId) {
        List<CommentContent> commentContents = commentContentMapper.findContentCountByTopic(topicId);
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < commentContents.size(); i++) {
            Comment comment = commentMapper.selectCommentByCommentId(commentContents.get(i).getCommentId());
            if (comment.getParentId() == null) {
                comments.add(comment);
            }
        }

        return comments;
    }

    /**
     * 查找属于该父评论的所有子评论
     *
     * @param parentId
     * @return
     */
    @Override
    public List<Comment> replyComment(Long parentId) {
        List<Comment> comments = commentMapper.replyComment(parentId);
        for (int i = 0; i < comments.size(); i++) {
            if (comments.get(i).getParentId() == null)
                comments.remove(i);
        }
        return comments;
    }

    /**
     * 话题点赞
     *
     * @param userId
     * @param topicId
     */
    @Override
    public void likeClick(Long userId, Long topicId) {
        //首先先判断用户是否之前已经点赞过了该话题
        UserTopic userTopic = userTopicMapper.findByTopicId(userId, topicId);
        if (userTopic.getIsLike() != null && userTopic.getIsLike().equals("0")) {
            topicMapper.updateLikeCount(topicId);
            userTopicMapper.updateIsLikeInt2(userTopic.getUserTopicId());
        } else {
            topicMapper.updateCancleLikeCount(topicId);
            userTopicMapper.updateIsLikeInt1(userTopic.getUserTopicId());
        }

    }

    /**
     * 该话题未参加人的数量
     *
     * @param courseId
     * @param topicId
     * @return
     */
    @Override
    public Long noParticipation(Long courseId, Long topicId) {
        Long Join = topicMapper.selectTopicByTopicId(topicId).getJoinNumber();
        Long All = courseMapper.selectCourseByCourseId(courseId).getJoinNumber();
        return All - Join;
    }

    /**
     * 添加话题的评论
     *
     * @param userId
     * @param topicId
     * @param comment
     */
    @Override
    public void addTopicComment(Long userId, Long topicId, String comment, Long parentId) {
        List<Long> userIds = sysUserMapper.selectAllUserId();

        Date date = new Date();
        Comment comment1 = new Comment();
        comment1.setContent(comment);
        comment1.setTypeId(userId);
        comment1.setCreateTime(date);
        comment1.setCreateBy("xushaofang");
        comment1.setLikesNumber(0L);
        if (parentId != null)
            comment1.setParentId(parentId);
        else
            comment1.setParentId(0L);
        Long commentId = Long.valueOf(commentMapper.insertComment(comment1));
        for (Long userId1 : userIds) {
            Likes likes = new Likes();
            likes.setCommentId(commentId);
            likes.setType("0");
            likes.setUserId(userId1);
            likesMapper.insertLikes(likes);
        }
        CommentContent commentContent = new CommentContent();
        commentContent.setCommentId(comment1.getCommentId());
        commentContent.setContentId(topicId);
        commentContent.setType("4");
        commentContentMapper.insertCommentContent(commentContent);
    }

    /**
     * 判断是否已经加入了话题
     *
     * @param userId
     * @param topicId
     */
    @Override
    public void isJoinTopic(Long userId, Long topicId) {
        UserTopic userTopic = userTopicMapper.findByTopicId(userId, topicId);
        if (userTopic.getIsReadLabel() == null || userTopic.getIsReadLabel().equals("0")) {
            topicMapper.updateJoinNumberInt(topicId);
            userTopicMapper.updateIsReadLabelInt(userTopic.getUserTopicId());
        }
    }

    /**
     * 获取课程的所有章节信息
     *
     * @param courseId
     * @return
     */
    @Override
    public List<Chapter> processChapters(Long courseId) {
        List<CourseChapter> courseChapter = courseChapterMapper.findChapterByCourseById(courseId);
        List<Long> chapterIds = courseChapter.stream().map(CourseChapter::getChapterId).collect(Collectors.toList());
        List<Chapter> chapterList = chapterMapper.selectChapterByChapterIds(chapterIds);

        //子章节加入到父章节的child

        Map<Long, List<Chapter>> chapterMap = chapterList.stream().filter(chapter -> chapter.getParentId() != 0)
                .collect(Collectors.groupingBy(Chapter::getParentId));
        //遍历chapterList，将具有相同的parentId的Chapter对象添加到其父目录的children字段中
        chapterList.forEach(chapter -> {
            Long chapterId = chapter.getChapterId();

            //将父目录的id拿到map中比较得到它的子目录
            List<Chapter> children = chapterMap.get(chapterId);
            if (children != null)
                chapter.setChildren(children);
        });

        return chapterList;
    }

    @Override
    public SysUser findUserByTopicId(Long topicId) {
        Long userId = userTopicMapper.findUserByTopicId(topicId);
        return sysUserMapper.selectUserById(userId);
    }

    public List<CommentVo> processComments(Long topicId) {
        List<CommentContent> commentContents = commentContentMapper.findContentCountByTopic(topicId);
        List<Long> commentIds = commentContents.stream().map(CommentContent::getCommentId).collect(Collectors.toList());
        List<Comment> comments = commentMapper.findCommentsByCommentIds(commentIds);
        List<CommentVo> list = new ArrayList<>();
        comments.stream().forEach(comment -> {
            CommentVo commentVo = new CommentVo();
            BeanUtils.copyProperties(comment, commentVo);
            list.add(commentVo);
        });
        Map<Long, CommentVo> map = new HashMap<>();
        List<CommentVo> result = new ArrayList<>();
        //将所有根评论加入map
        for (CommentVo commentVo : list) {
            map.put(commentVo.getCommentId(), commentVo);
            if (commentVo.getParentId() == 0)
                result.add(commentVo);

        }

        //将子评论加入到父评论的child中
        for (CommentVo commentVo : list) {
            Long id = commentVo.getParentId();
            if (id != 0) {
                CommentVo p = map.get(id);
                if (p.getChlid() == null)
                    p.setChlid(new ArrayList<>());
                p.getChlid().add(commentVo);
            }
        }
        System.out.println("树形结构为：" + result);

        return result;
    }

    @Override
    public int deleteComment(CommentVo commentVo) {
        int result = 0;
        Queue<CommentVo> queue = new LinkedList<>();
        queue.offer(commentVo);
        while (!queue.isEmpty()) {
            CommentVo commentVo1 = queue.poll();
            result = commentMapper.deleteCommentByCommentId(commentVo1.getCommentId());
            commentContentMapper.deleteByCommentId(commentVo1.getCommentId());
            if (commentVo1.getChlid() != null) {
                List<CommentVo> child = commentVo1.getChlid();
                for (CommentVo tmp : child) {
                    queue.offer(tmp);
                }
            }
        }

        return result;
    }

    //评论点赞
    public void CommentLikes(Long userId, Long commentId) {
        String type = likesMapper.selectType(userId, commentId);

        if (type.equals("0")) {

            likesMapper.updateType(userId, commentId);

            commentMapper.updateLikesNumber(commentId);
        } else {

            likesMapper.updateType2(userId, commentId);
            commentMapper.reduceLikesNumber(commentId);
        }
    }

    //判断点赞的状态
    public Boolean likeState(Long userId, Long commentId) {
        String type = likesMapper.selectType(userId, commentId);
        if (type.equals("0")) {
            return false;
        } else
            return true;
    }

    @Override
    public List<ChapterVo> getChapterByCourseId(Long courseId) {
        // 查询课程章节关系
        List<CourseChapter> courseChapters = courseChapterMapper.selectChaptersByCourserId(courseId);

        // 根据chapterIds查询章节
        List<Long> chapterIds = courseChapters.stream().map(CourseChapter::getChapterId).collect(Collectors.toList());
        List<Chapter> chapterList = chapterMapper.selectChapterByChapterIds(chapterIds);

        // 转换成chapterVoList
        List<ChapterVo> chapterVoList = new ArrayList<>();
        chapterList.stream().forEach(chapter -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            chapterVoList.add(chapterVo);
        });

        // 将子目录放在父目录下
        // 将chapterList转换为Map，其中键为parentId，值为具有相同parentId的Chapter对象列表
        Map<Long, List<ChapterVo>> chapterMap = chapterVoList.stream().filter(chapter -> chapter.getParentId() != 0)
                .collect(Collectors.groupingBy(Chapter::getParentId));

        // 遍历chapterList,将具有相同parentId的Chapter对象添加到其父目录的children字段中
        chapterVoList.forEach(chapterVo -> {
            Long chapterId = chapterVo.getChapterId();


            // 将父目录的id拿到map中比较得到它的子目录
            List<ChapterVo> children = chapterMap.get(chapterId);
            if (children != null) {
                chapterVo.setChildren(children);
            }
        });

        // 去除子目录
        List<ChapterVo> collect = chapterVoList.stream()
                .filter(chapterVo -> chapterVo.getParentId() == 0)
                .collect(Collectors.toList());

        return collect;
    }

}
