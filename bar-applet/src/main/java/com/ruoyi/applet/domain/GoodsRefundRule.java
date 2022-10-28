package com.ruoyi.applet.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 商品退款规则
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsRefundRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 退款时间
     */
    private LocalDateTime refundTime;

    /**
     * 条件类型：1前，不包括规则时间，2后，包括规则时间
     */
    private Integer conditionType;

    /**
     * 是否允许退款：0不允许，1允许
     */
    private Integer isAllow;

    /**
     * 退款比例
     */
    private BigDecimal refundRate;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
