package com.ruoyi.applet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动查询响应实体
 *
 * @author AlanLee
 * @date 2022-09-03
 */
@Data
public class ActivityQueryVO implements Serializable {

    private static final long serialVersionUID = 2772666688737514171L;

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动图片
     */
    private String picture;

    /**
     * 颜色
     */
    private String color;

    /**
     * 活动年月
     */
    private String activityMonth;

    /**
     * 城市
     */
    private String city;

    /**
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

}