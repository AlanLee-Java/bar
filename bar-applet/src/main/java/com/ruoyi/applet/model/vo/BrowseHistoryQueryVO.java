package com.ruoyi.applet.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.applet.domain.Activity;
import com.ruoyi.applet.domain.Merchant;
import com.ruoyi.applet.domain.MerchantTopic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 浏览历史
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BrowseHistoryQueryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 浏览类型：1商家，2活动
     */
    private Integer type;

    /**
     * 对象ID
     */
    private Long objectId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 日期
     */
    private String date;

    /**
     * 商家信息
     */
    private Merchant merchant;

    /**
     * 商家话题列表
     */
    private List<MerchantTopic> merchantTopicList;

    /**
     * 活动信息
     */
    private Activity activity;

}