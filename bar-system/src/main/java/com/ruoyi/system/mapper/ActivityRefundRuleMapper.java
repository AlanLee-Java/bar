package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ActivityRefundRule;

import java.util.List;

/**
 * 活动退款规则Mapper接口
 * 
 * @author AlanLee
 * @date 2022-08-28
 */
public interface ActivityRefundRuleMapper 
{
    /**
     * 查询活动退款规则
     * 
     * @param id 活动退款规则主键
     * @return 活动退款规则
     */
    public ActivityRefundRule selectActivityRefundRuleById(Long id);

    /**
     * 查询活动退款规则列表
     * 
     * @param activityRefundRule 活动退款规则
     * @return 活动退款规则集合
     */
    public List<ActivityRefundRule> selectActivityRefundRuleList(ActivityRefundRule activityRefundRule);

    /**
     * 新增活动退款规则
     * 
     * @param activityRefundRule 活动退款规则
     * @return 结果
     */
    public int insertActivityRefundRule(ActivityRefundRule activityRefundRule);

    /**
     * 修改活动退款规则
     * 
     * @param activityRefundRule 活动退款规则
     * @return 结果
     */
    public int updateActivityRefundRule(ActivityRefundRule activityRefundRule);

    /**
     * 删除活动退款规则
     * 
     * @param id 活动退款规则主键
     * @return 结果
     */
    public int deleteActivityRefundRuleById(Long id);

    /**
     * 批量删除活动退款规则
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActivityRefundRuleByIds(String[] ids);

}