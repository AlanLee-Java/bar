package com.ruoyi.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.applet.domain.Activity;
import com.ruoyi.applet.model.dto.ActivityQueryDTO;
import com.ruoyi.applet.model.vo.ActivityQueryVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 活动 Mapper 接口
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-17
 */
public interface ActivityMapper extends BaseMapper<Activity> {

    /**
     * 分页查询活动日历
     *
     * @param page
     * @param activityQueryDTO
     * @return
     */
    Page<ActivityQueryVO> queryActivity(Page<ActivityQueryVO> page, @Param("param") ActivityQueryDTO activityQueryDTO);

}