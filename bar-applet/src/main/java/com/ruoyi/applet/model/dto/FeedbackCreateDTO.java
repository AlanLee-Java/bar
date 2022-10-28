package com.ruoyi.applet.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 问题反馈创建参数实体
 * </p>
 *
 * @author AlanLee
 * @since 2022-09-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("问题反馈创建参数实体")
public class FeedbackCreateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 反馈内容
     */
    @NotBlank(message = "反馈内容不能为空")
    @ApiModelProperty("反馈内容")
    private String content;

    /**
     * 反馈截图
     */
    @ApiModelProperty("反馈截图")
    private String screenshot;

    /**
     * 联系人
     */
    @NotBlank(message = "联系人不能为空")
    @ApiModelProperty("联系人")
    private String linkman;

    /**
     * 联系方式
     */
    @NotBlank(message = "联系方式不能为空")
    @ApiModelProperty("联系方式")
    private String contact;

}