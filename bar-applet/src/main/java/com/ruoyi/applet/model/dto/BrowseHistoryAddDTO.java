package com.ruoyi.applet.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
public class BrowseHistoryAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 浏览类型：1商家，2活动
     */
    @NotNull(message = "浏览类型不能为空")
    @Range(min = 1, max = 2, message = "浏览类型不正确")
    private Integer type;

    /**
     * 对象ID
     */
    @NotNull(message = "对象ID不能为空")
    private Long objectId;

}