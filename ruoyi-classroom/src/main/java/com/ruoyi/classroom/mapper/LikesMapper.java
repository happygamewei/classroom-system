package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.Likes;
import org.apache.ibatis.annotations.Param;


/**
 * 点赞记录Mapper接口
 *
 * @author Yuan
 * @date 2023-09-08
 */
public interface LikesMapper
{
    /**
     * 查询点赞记录
     *
     * @param id 点赞记录主键
     * @return 点赞记录
     */
    public Likes selectLikesById(Long id);

    /**
     * 查询点赞记录列表
     *
     * @param likes 点赞记录
     * @return 点赞记录集合
     */
    public List<Likes> selectLikesList(Likes likes);

    /**
     * 新增点赞记录
     *
     * @param likes 点赞记录
     * @return 结果
     */
    public int insertLikes(Likes likes);

    /**
     * 修改点赞记录
     *
     * @param likes 点赞记录
     * @return 结果
     */
    public int updateLikes(Likes likes);

    /**
     * 删除点赞记录
     *
     * @param id 点赞记录主键
     * @return 结果
     */
    public int deleteLikesById(Long id);

    /**
     * 批量删除点赞记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLikesByIds(Long[] ids);
    public  String selectType(@Param("userId") Long userId, @Param("commentId") Long commentId);
    public  int updateType(@Param("userId") Long userId, @Param("commentId") Long commentId);
    public  int updateType2(@Param("userId") Long userId, @Param("commentId") Long commentId);

}
