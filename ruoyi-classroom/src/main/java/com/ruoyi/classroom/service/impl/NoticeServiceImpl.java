package com.ruoyi.classroom.service.impl;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.classroom.domain.Comment;
import com.ruoyi.classroom.domain.CommentContent;
import com.ruoyi.classroom.domain.vo.CommentVo;
import com.ruoyi.classroom.mapper.CommentContentMapper;
import com.ruoyi.classroom.mapper.CommentMapper;
import com.ruoyi.common.utils.DateUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.mapper.NoticeMapper;
import com.ruoyi.classroom.domain.Notice;
import com.ruoyi.classroom.service.INoticeService;
import reactor.core.scheduler.Scheduler;

import javax.annotation.Resource;

/**
 * 公告Service业务层处理
 *
 * @author Qiao
 * @date 2023-09-08
 */
@Service
public class NoticeServiceImpl implements INoticeService
{
    @Resource
    private NoticeMapper noticeMapper;
    @Resource
    private CommentContentMapper commentContentMapper;
    @Resource

    private CommentMapper commentMapper;

    /**
     * 查询公告
     *
     * @param noticeId 公告主键
     * @return 公告
     */
    @Override
    public Notice selectNoticeByNoticeId(Long noticeId)
    {
        return noticeMapper.selectNoticeByNoticeId(noticeId);
    }

    /**
     * 查询公告列表
     *
     * @param notice 公告
     * @return 公告
     */
    @Override
    public List<Notice> selectNoticeList(Notice notice)
    {
        return noticeMapper.selectNoticeList(notice);
    }

    /**
     * 新增公告
     *
     * @param notice 公告
     * @return 结果
     */
    @Override
    public int insertNotice(Notice notice)
    {
        notice.setCreateTime(DateUtils.getNowDate());
        return noticeMapper.insertNotice(notice);
    }

    /**
     * 修改公告
     *
     * @param notice 公告
     * @return 结果
     */
    @Override
    public int updateNotice(Notice notice)
    {
        notice.setUpdateTime(DateUtils.getNowDate());
        return noticeMapper.updateNotice(notice);
    }

    /**
     * 批量删除公告
     *
     * @param noticeIds 需要删除的公告主键
     * @return 结果
     */
    @Override
    public int deleteNoticeByNoticeIds(Long[] noticeIds)
    {
        return noticeMapper.deleteNoticeByNoticeIds(noticeIds);
    }

    /**
     * 删除公告信息
     *
     * @param noticeId 公告主键
     * @return 结果
     */
    @Override
    public int deleteNoticeByNoticeId(Long noticeId)
    {
        return noticeMapper.deleteNoticeByNoticeId(noticeId);
    }

/**
 * 查询评论列表
 */
@Override
public  List<CommentVo> processComments(Long noticeId){
    CommentContent commentContent = new CommentContent();
    List<CommentContent> commentContents=commentContentMapper.findContentCountByNotice(noticeId);
    System.out.println("length:"+commentContents.size());
//    List<CommentContent> commentContents=commentContentMapper.selectCommentContentList(commentContent);
    System.out.println("!!!!!!!===================>commentContents:"+commentContents);
    List<Long> commentIds = commentContents.stream().filter(Objects::nonNull).map(CommentContent::getCommentId).collect(Collectors.toList());
    System.out.println("!!!!!!!===================>commentIds:"+commentIds);
    List<Comment> comments=commentMapper.findCommentsByCommentIds(commentIds);

    List<CommentVo> list=new ArrayList<>();
    comments.stream().forEach(comment->{
        CommentVo commentVo=new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        list.add(commentVo);
    });
    Map<Long, CommentVo> map=new HashMap<>();
    List<CommentVo> result=new ArrayList<>();
    //将所有根评论加入map
    for (CommentVo commentVo:list){
        map.put(commentVo.getCommentId(),commentVo);
        if (commentVo.getParentId()==0)
            result.add(commentVo);

    }

    //将子评论加入到父评论的child中
    for (CommentVo commentVo:list){
        Long id=commentVo.getParentId();
        if (id!=0){
            CommentVo p=map.get(id);
            if (p.getChild()==null)
                p.setChild(new ArrayList<>());
            p.getChild().add(commentVo);
        }
    }
    System.out.println("树形结构为："+result);
    System.out.println("result:"+result.size());
    return result;
}

    @Override
    public int TimingIssue(Notice notice) throws SchedulerException {
        // 定义发布时间（示例：2023-10-13 12:00:00）
        notice.setCreateTime(DateUtils.getNowDate());
        final int[] flag = new int[1];
        // 创建Timer对象
        Timer timer = new Timer();

        // 创建TimerTask对象并重写run方法
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 在这里编写发布文章的逻辑
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("发布时间：" + sdf.format(new Date()));
                System.out.println("文章标题：" + notice.getTitle());
                System.out.println("文章内容：" + notice.getContent());
                System.out.println("文章发布完成");
                flag[0] = noticeMapper.insertNotice(notice);
            }
        };

        try {
            // 设置定时任务，在指定发布时间执行
            timer.schedule(task, notice.getPublishDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag[0];
    }

//    @Override
//    public List<Notice> getNoticeByCourseId(Long courseId) {
//        return null;
//    }
}
