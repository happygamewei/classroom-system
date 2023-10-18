package com.ruoyi.classroom.service;

import java.util.List;
import com.ruoyi.classroom.domain.Notice;
import com.ruoyi.classroom.domain.vo.CommentVo;
import org.quartz.SchedulerException;

/**
 * 公告Service接口
 *
 * @author Qiao
 * @date 2023-09-08
 */
public interface INoticeService
{
    /**
     * 查询公告
     *
     * @param noticeId 公告主键
     * @return 公告
     */
    public Notice selectNoticeByNoticeId(Long noticeId);

    /**
     * 查询公告列表
     *
     * @param notice 公告
     * @return 公告集合
     */
    public List<Notice> selectNoticeList(Notice notice);

    /**
     * 新增公告
     *
     * @param notice 公告
     * @return 结果
     */
    public int insertNotice(Notice notice);

    /**
     * 修改公告
     *
     * @param notice 公告
     * @return 结果
     */
    public int updateNotice(Notice notice);

    /**
     * 批量删除公告
     *
     * @param noticeIds 需要删除的公告主键集合
     * @return 结果
     */
    public int deleteNoticeByNoticeIds(Long[] noticeIds);

    /**
     * 删除公告信息
     *
     * @param noticeId 公告主键
     * @return 结果
     */
    public int deleteNoticeByNoticeId(Long noticeId);

    /**
     * 获取公告下的全部评论
     * @param noticeId
     * @return
     */
    public  List<CommentVo> processComments(Long noticeId);

//    List<Notice> getNoticeByCourseId(Long courseId);

    public int TimingIssue(Notice notice) throws SchedulerException;
}
