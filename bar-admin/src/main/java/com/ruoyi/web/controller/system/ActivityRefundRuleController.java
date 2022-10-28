package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ActivityRefundRule;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.service.IActivityRefundRuleService;
import com.ruoyi.system.service.IMerchantService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 活动退款规则Controller
 *
 * @author AlanLee
 * @date 2022-08-28
 */
@Controller
@RequestMapping("/system/activityRefundRule")
public class ActivityRefundRuleController extends BaseController {

    private String prefix = "system/activityRefundRule";

    @Autowired
    private IActivityRefundRuleService activityRefundRuleService;

    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:activityRefundRule:view")
    @GetMapping()
    public String activityRefundRule() {
        return prefix + "/activityRefundRule";
    }

    /**
     * 查询活动退款规则列表
     */
    @RequiresPermissions("system:activityRefundRule:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ActivityRefundRule activityRefundRule) {
        startPage();
        List<ActivityRefundRule> list = activityRefundRuleService.selectActivityRefundRuleList(activityRefundRule);
        for (ActivityRefundRule refundRule : list) {
            // 查询商家名称
            Merchant merchant = merchantService.selectMerchantById(refundRule.getMerchantId());
            if (Optional.ofNullable(merchant).isPresent()) {
                refundRule.setMerchantName(merchant.getName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 新增活动退款规则
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)  {
    	mmap.put("activityId", id);
        return prefix + "/add";
    }

    /**
     * 新增保存活动退款规则
     */
    @RequiresPermissions("system:activityRefundRule:add")
    @Log(title = "活动退款规则", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ActivityRefundRule activityRefundRule) {
        activityRefundRule.setCreateBy(getLoginName());
        activityRefundRule.setUpdateBy(getLoginName());
        return toAjax(activityRefundRuleService.insertActivityRefundRule(activityRefundRule));
    }

    /**
     * 修改活动退款规则
     */
    @RequiresPermissions("system:activityRefundRule:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ActivityRefundRule activityRefundRule = activityRefundRuleService.selectActivityRefundRuleById(id);
        mmap.put("activityRefundRule", activityRefundRule);
        return prefix + "/edit";
    }

    /**
     * 修改保存活动退款规则
     */
    @RequiresPermissions("system:activityRefundRule:edit")
    @Log(title = "活动退款规则", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ActivityRefundRule activityRefundRule) {
        activityRefundRule.setUpdateBy(getLoginName());
        return toAjax(activityRefundRuleService.updateActivityRefundRule(activityRefundRule));
    }

    /**
     * 删除活动退款规则
     */
    @RequiresPermissions("system:activityRefundRule:remove")
    @Log(title = "活动退款规则", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(activityRefundRuleService.deleteActivityRefundRuleByIds(ids));
    }

}