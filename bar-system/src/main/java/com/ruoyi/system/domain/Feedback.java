package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 问题反馈对象 feedback
 * 
 * @author AlanLee
 * @date 2022-09-08
 */
public class Feedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一主键 */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 反馈内容 */
    @Excel(name = "反馈内容")
    private String content;

    /** 反馈截图 */
    @Excel(name = "反馈截图")
    private String screenshot;

    /** 联系人 */
    @Excel(name = "联系人")
    private String linkman;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

    /** 0：待处理，1：已处理 */
    @Excel(name = "0：待处理，1：已处理")
    private Integer status;

    /** 处理人 */
    @Excel(name = "处理人")
    private String handleBy;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setScreenshot(String screenshot) 
    {
        this.screenshot = screenshot;
    }

    public String getScreenshot() 
    {
        return screenshot;
    }
    public void setLinkman(String linkman) 
    {
        this.linkman = linkman;
    }

    public String getLinkman() 
    {
        return linkman;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setHandleBy(String handleBy) 
    {
        this.handleBy = handleBy;
    }

    public String getHandleBy() 
    {
        return handleBy;
    }
    public void setHandleTime(Date handleTime) 
    {
        this.handleTime = handleTime;
    }

    public Date getHandleTime() 
    {
        return handleTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("content", getContent())
            .append("screenshot", getScreenshot())
            .append("linkman", getLinkman())
            .append("contact", getContact())
            .append("status", getStatus())
            .append("handleBy", getHandleBy())
            .append("handleTime", getHandleTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
