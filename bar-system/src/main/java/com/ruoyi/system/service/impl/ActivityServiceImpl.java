package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.mapper.ActivityMapper;
import com.ruoyi.system.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动Service业务层处理
 *
 * @author AlanLee
 * @date 2022-08-28
 */
@Service
public class ActivityServiceImpl implements IActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 查询活动
     *
     * @param id 活动主键
     * @return 活动
     */
    @Override
    public Activity selectActivityById(Long id) {
        return activityMapper.selectActivityById(id);
    }

    /**
     * 查询活动列表
     *
     * @param activity 活动
     * @return 活动
     */
    @Override
    public List<Activity> selectActivityList(Activity activity) {
        return activityMapper.selectActivityList(activity);
    }

    /**
     * 新增活动
     *
     * @param activity 活动
     * @return 结果
     */
    @Override
    public Long insertActivity(Activity activity) {
        activity.setCreateTime(DateUtils.getNowDate());
        activity.setUpdateTime(DateUtils.getNowDate());
        int rows = activityMapper.insertActivity(activity);
        if (rows <= 0) {
            throw new ServiceException("新增活动失败");
        }
        return activity.getId();
    }

    /**
     * 修改活动
     *
     * @param activity 活动
     * @return 结果
     */
    @Override
    public int updateActivity(Activity activity) {
        activity.setUpdateTime(DateUtils.getNowDate());
        return activityMapper.updateActivity(activity);
    }

    /**
     * 批量删除活动
     *
     * @param ids 需要删除的活动主键
     * @return 结果
     */
    @Override
    public int deleteActivityByIds(String ids) {
        return activityMapper.deleteActivityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除活动信息
     *
     * @param id 活动主键
     * @return 结果
     */
    @Override
    public int deleteActivityById(Long id) {
        return activityMapper.deleteActivityById(id);
    }
}
