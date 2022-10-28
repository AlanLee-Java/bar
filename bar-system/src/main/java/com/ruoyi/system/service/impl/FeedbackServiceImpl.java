package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Feedback;
import com.ruoyi.system.mapper.FeedbackMapper;
import com.ruoyi.system.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 问题反馈Service业务层处理
 * 
 * @author AlanLee
 * @date 2022-09-08
 */
@Service
public class FeedbackServiceImpl implements IFeedbackService 
{
    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 查询问题反馈
     * 
     * @param id 问题反馈主键
     * @return 问题反馈
     */
    @Override
    public Feedback selectFeedbackById(Long id)
    {
        return feedbackMapper.selectFeedbackById(id);
    }

    /**
     * 查询问题反馈列表
     * 
     * @param feedback 问题反馈
     * @return 问题反馈
     */
    @Override
    public List<Feedback> selectFeedbackList(Feedback feedback)
    {
        return feedbackMapper.selectFeedbackList(feedback);
    }

    /**
     * 新增问题反馈
     * 
     * @param feedback 问题反馈
     * @return 结果
     */
    @Override
    public int insertFeedback(Feedback feedback)
    {
        feedback.setCreateTime(DateUtils.getNowDate());
        return feedbackMapper.insertFeedback(feedback);
    }

    /**
     * 修改问题反馈
     * 
     * @param feedback 问题反馈
     * @return 结果
     */
    @Override
    public int updateFeedback(Feedback feedback)
    {
        feedback.setUpdateTime(DateUtils.getNowDate());
        return feedbackMapper.updateFeedback(feedback);
    }

    /**
     * 批量删除问题反馈
     * 
     * @param ids 需要删除的问题反馈主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackByIds(String ids)
    {
        return feedbackMapper.deleteFeedbackByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除问题反馈信息
     * 
     * @param id 问题反馈主键
     * @return 结果
     */
    @Override
    public int deleteFeedbackById(Long id)
    {
        return feedbackMapper.deleteFeedbackById(id);
    }
}
