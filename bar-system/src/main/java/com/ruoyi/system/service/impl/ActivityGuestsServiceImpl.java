package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.domain.ActivityGuests;
import com.ruoyi.system.mapper.ActivityGuestsMapper;
import com.ruoyi.system.mapper.ActivityMapper;
import com.ruoyi.system.service.IActivityGuestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动嘉宾Service业务层处理
 *
 * @author AlanLee
 * @date 2022-08-28
 */
@Service
public class ActivityGuestsServiceImpl implements IActivityGuestsService {

    @Autowired
    private ActivityGuestsMapper activityGuestsMapper;

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 查询活动嘉宾
     *
     * @param id 活动嘉宾主键
     * @return 活动嘉宾
     */
    @Override
    public ActivityGuests selectActivityGuestsById(Long id) {
        return activityGuestsMapper.selectActivityGuestsById(id);
    }

    /**
     * 查询活动嘉宾列表
     *
     * @param activityGuests 活动嘉宾
     * @return 活动嘉宾
     */
    @Override
    public List<ActivityGuests> selectActivityGuestsList(ActivityGuests activityGuests) {
        return activityGuestsMapper.selectActivityGuestsList(activityGuests);
    }

    /**
     * 新增活动嘉宾
     *
     * @param activityGuests 活动嘉宾
     * @return 结果
     */
    @Override
    public int insertActivityGuests(ActivityGuests activityGuests) {
        // 查询活动所属商家ID
        Activity activity = activityMapper.selectActivityById(activityGuests.getActivityId());
        activityGuests.setMerchantId(activity.getMerchantId());
        activityGuests.setCreateTime(DateUtils.getNowDate());
        activityGuests.setUpdateTime(DateUtils.getNowDate());
        return activityGuestsMapper.insertActivityGuests(activityGuests);
    }

    /**
     * 修改活动嘉宾
     *
     * @param activityGuests 活动嘉宾
     * @return 结果
     */
    @Override
    public int updateActivityGuests(ActivityGuests activityGuests) {
        // 查询活动所属商家ID
        Activity activity = activityMapper.selectActivityById(activityGuests.getActivityId());
        activityGuests.setMerchantId(activity.getMerchantId());
        activityGuests.setUpdateTime(DateUtils.getNowDate());
        return activityGuestsMapper.updateActivityGuests(activityGuests);
    }

    /**
     * 批量删除活动嘉宾
     *
     * @param ids 需要删除的活动嘉宾主键
     * @return 结果
     */
    @Override
    public int deleteActivityGuestsByIds(String ids) {
        return activityGuestsMapper.deleteActivityGuestsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除活动嘉宾信息
     *
     * @param id 活动嘉宾主键
     * @return 结果
     */
    @Override
    public int deleteActivityGuestsById(Long id) {
        return activityGuestsMapper.deleteActivityGuestsById(id);
    }
}
