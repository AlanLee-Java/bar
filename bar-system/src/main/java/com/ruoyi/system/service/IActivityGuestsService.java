package com.ruoyi.system.service;

import com.ruoyi.system.domain.ActivityGuests;

import java.util.List;

/**
 * 活动嘉宾Service接口
 * 
 * @author AlanLee
 * @date 2022-08-28
 */
public interface IActivityGuestsService 
{
    /**
     * 查询活动嘉宾
     * 
     * @param id 活动嘉宾主键
     * @return 活动嘉宾
     */
    public ActivityGuests selectActivityGuestsById(Long id);

    /**
     * 查询活动嘉宾列表
     * 
     * @param activityGuests 活动嘉宾
     * @return 活动嘉宾集合
     */
    public List<ActivityGuests> selectActivityGuestsList(ActivityGuests activityGuests);

    /**
     * 新增活动嘉宾
     * 
     * @param activityGuests 活动嘉宾
     * @return 结果
     */
    public int insertActivityGuests(ActivityGuests activityGuests);

    /**
     * 修改活动嘉宾
     * 
     * @param activityGuests 活动嘉宾
     * @return 结果
     */
    public int updateActivityGuests(ActivityGuests activityGuests);

    /**
     * 批量删除活动嘉宾
     * 
     * @param ids 需要删除的活动嘉宾主键集合
     * @return 结果
     */
    public int deleteActivityGuestsByIds(String ids);

    /**
     * 删除活动嘉宾信息
     * 
     * @param id 活动嘉宾主键
     * @return 结果
     */
    public int deleteActivityGuestsById(Long id);
}
