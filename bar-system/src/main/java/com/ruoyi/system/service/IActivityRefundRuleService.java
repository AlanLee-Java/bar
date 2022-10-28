package com.ruoyi.system.service;

import com.ruoyi.system.domain.ActivityRefundRule;

import java.util.List;

/**
 * 活动退款规则Service接口
 *
 * @author AlanLee
 * @date 2022-08-28
 */
public interface IActivityRefundRuleService {
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
     * 批量删除活动退款规则
     *
     * @param ids 需要删除的活动退款规则主键集合
     * @return 结果
     */
    public int deleteActivityRefundRuleByIds(String ids);

    /**
     * 删除活动退款规则信息
     *
     * @param id 活动退款规则主键
     * @return 结果
     */
    public int deleteActivityRefundRuleById(Long id);

}