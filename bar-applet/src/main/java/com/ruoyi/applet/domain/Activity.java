package com.ruoyi.applet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 活动
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 活动详情
     */
    private String details;

    /**
     * 退款规则
     */
    private String refundRule;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    // 补充信息
    /**
     * 商家名称
     */
    @TableField(exist = false)
    private String mechantName;

    /**
     * 最低价
     */
    @TableField(exist = false)
    private BigDecimal minimumPrice;

    /**
     * 最高价
     */
    @TableField(exist = false)
    private BigDecimal maximumPrice;

    /**
     * 嘉宾信息列表
     */
    @TableField(exist = false)
    private List<ActivityGuests> activityGuestsList;

    /**
     * 活动商品列表
     */
    @TableField(exist = false)
    private List<Goods> goodsList;

}