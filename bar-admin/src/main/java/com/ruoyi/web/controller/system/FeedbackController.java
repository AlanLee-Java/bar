package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Feedback;
import com.ruoyi.system.service.IFeedbackService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 问题反馈Controller
 *
 * @author AlanLee
 * @date 2022-09-08
 */
@Controller
@RequestMapping("/system/feedback")
public class FeedbackController extends BaseController {

    private String prefix = "system/feedback";

    @Autowired
    private IFeedbackService feedbackService;

    @RequiresPermissions("system:feedback:view")
    @GetMapping()
    public String feedback() {
        return prefix + "/feedback";
    }

    /**
     * 查询问题反馈列表
     */
    @RequiresPermissions("system:feedback:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Feedback feedback) {
        startPage();
        List<Feedback> list = feedbackService.selectFeedbackList(feedback);
        return getDataTable(list);
    }

    /**
     * 修改问题反馈
     */
    @RequiresPermissions("system:feedback:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Feedback feedback = feedbackService.selectFeedbackById(id);
        mmap.put("feedback", feedback);
        if(StringUtils.isEmpty(feedback.getScreenshot())){
        	 mmap.put("screenshots", new String[]{});
        }else{
        	String[]  screenshots = feedback.getScreenshot().split(",");
        	 mmap.put("screenshots", screenshots);
        }
       
        return prefix + "/edit";
    }

    /**
     * 修改保存问题反馈
     */
    @RequiresPermissions("system:feedback:edit")
    @Log(title = "问题反馈", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Feedback feedback) {
        return toAjax(feedbackService.updateFeedback(feedback));
    }

}