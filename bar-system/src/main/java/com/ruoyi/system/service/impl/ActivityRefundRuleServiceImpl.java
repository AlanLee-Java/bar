package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.domain.ActivityRefundRule;
import com.ruoyi.system.mapper.ActivityMapper;
import com.ruoyi.system.mapper.ActivityRefundRuleMapper;
import com.ruoyi.system.service.IActivityRefundRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动退款规则Service业务层处理
 *
 * @author AlanLee
 * @date 2022-08-28
 */
@Service
public class ActivityRefundRuleServiceImpl implements IActivityRefundRuleService {

    @Autowired
    private ActivityRefundRuleMapper activityRefundRuleMapper;

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 查询活动退款规则
     *
     * @param id 活动退款规则主键
     * @return 活动退款规则
     */
    @Override
    public ActivityRefundRule selectActivityRefundRuleById(Long id) {
        return activityRefundRuleMapper.selectActivityRefundRuleById(id);
    }

    /**
     * 查询活动退款规则列表
     *
     * @param activityRefundRule 活动退款规则
     * @return 活动退款规则
     */
    @Override
    public List<ActivityRefundRule> selectActivityRefundRuleList(ActivityRefundRule activityRefundRule) {
        return activityRefundRuleMapper.selectActivityRefundRuleList(activityRefundRule);
    }

    /**
     * 新增活动退款规则
     *
     * @param activityRefundRule 活动退款规则
     * @return 结果
     */
    @Override
    public int insertActivityRefundRule(ActivityRefundRule activityRefundRule) {
        // 查询活动所属商家ID
        Activity activity = activityMapper.selectActivityById(activityRefundRule.getActivityId());
        activityRefundRule.setMerchantId(activity.getMerchantId());
        activityRefundRule.setCreateTime(DateUtils.getNowDate());
        activityRefundRule.setUpdateTime(DateUtils.getNowDate());
        return activityRefundRuleMapper.insertActivityRefundRule(activityRefundRule);
    }

    /**
     * 修改活动退款规则
     *
     * @param activityRefundRule 活动退款规则
     * @return 结果
     */
    @Override
    public int updateActivityRefundRule(ActivityRefundRule activityRefundRule) {
        activityRefundRule.setUpdateTime(DateUtils.getNowDate());
        return activityRefundRuleMapper.updateActivityRefundRule(activityRefundRule);
    }

    /**
     * 批量删除活动退款规则
     *
     * @param ids 需要删除的活动退款规则主键
     * @return 结果
     */
    @Override
    public int deleteActivityRefundRuleByIds(String ids) {
        return activityRefundRuleMapper.deleteActivityRefundRuleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除活动退款规则信息
     *
     * @param id 活动退款规则主键
     * @return 结果
     */
    @Override
    public int deleteActivityRefundRuleById(Long id) {
        return activityRefundRuleMapper.deleteActivityRefundRuleById(id);
    }

}