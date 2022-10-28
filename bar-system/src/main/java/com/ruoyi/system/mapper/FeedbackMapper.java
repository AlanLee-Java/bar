package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Feedback;

import java.util.List;

/**
 * 问题反馈Mapper接口
 * 
 * @author AlanLee
 * @date 2022-09-08
 */
public interface FeedbackMapper 
{
    /**
     * 查询问题反馈
     * 
     * @param id 问题反馈主键
     * @return 问题反馈
     */
    public Feedback selectFeedbackById(Long id);

    /**
     * 查询问题反馈列表
     * 
     * @param feedback 问题反馈
     * @return 问题反馈集合
     */
    public List<Feedback> selectFeedbackList(Feedback feedback);

    /**
     * 新增问题反馈
     * 
     * @param feedback 问题反馈
     * @return 结果
     */
    public int insertFeedback(Feedback feedback);

    /**
     * 修改问题反馈
     * 
     * @param feedback 问题反馈
     * @return 结果
     */
    public int updateFeedback(Feedback feedback);

    /**
     * 删除问题反馈
     * 
     * @param id 问题反馈主键
     * @return 结果
     */
    public int deleteFeedbackById(Long id);

    /**
     * 批量删除问题反馈
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFeedbackByIds(String[] ids);
}
