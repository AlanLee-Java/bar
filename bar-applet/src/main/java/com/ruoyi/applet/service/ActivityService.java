package com.ruoyi.applet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.applet.domain.Activity;
import com.ruoyi.applet.model.dto.ActivityQueryDTO;
import com.ruoyi.applet.model.vo.ActivityQueryVO;

/**
 * <p>
 * 活动 服务类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-17
 */
public interface ActivityService extends IService<Activity> {

    /**
     * 分页查询活动日历
     *
     * @param page
     * @param activityQueryDTO
     * @return
     */
    Page<ActivityQueryVO> queryActivity(Page<ActivityQueryVO> page, ActivityQueryDTO activityQueryDTO);

    /**
     * 分页查询活动
     *
     * @param page
     * @param activityQueryDTO
     * @return
     */
    Page<Activity> queryActivityPage(Page<ActivityQueryVO> page, ActivityQueryDTO activityQueryDTO);

}