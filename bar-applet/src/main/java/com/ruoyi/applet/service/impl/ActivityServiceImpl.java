package com.ruoyi.applet.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.applet.domain.Activity;
import com.ruoyi.applet.mapper.ActivityMapper;
import com.ruoyi.applet.model.dto.ActivityQueryDTO;
import com.ruoyi.applet.model.vo.ActivityQueryVO;
import com.ruoyi.applet.service.ActivityService;

/**
 * <p>
 * 活动 服务实现类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-17
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    /**
     * 分页查询活动日历
     *
     * @param page
     * @param activityQueryDTO
     * @return
     */
    @Override
    public Page<ActivityQueryVO> queryActivity(Page<ActivityQueryVO> page, ActivityQueryDTO activityQueryDTO) {
        return this.baseMapper.queryActivity(page, activityQueryDTO);
    }
    
    /**
     * 分页查询活动日历
     *
     * @param page
     * @param activityQueryDTO
     * @return
     */
    @Override
    public Page<Activity> queryActivityPage(Page<ActivityQueryVO> page, ActivityQueryDTO activityQueryDTO) {
        return this.baseMapper.queryActivityPage(page, activityQueryDTO);
    }

}