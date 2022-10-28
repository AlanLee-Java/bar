package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ActivityGuests;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.service.IActivityGuestsService;
import com.ruoyi.system.service.IMerchantService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 活动嘉宾Controller
 *
 * @author AlanLee
 * @date 2022-08-28
 */
@Controller
@RequestMapping("/system/activityGuests")
public class ActivityGuestsController extends BaseController {

    private String prefix = "system/activityGuests";

    @Autowired
    private IActivityGuestsService activityGuestsService;

    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:activityGuests:view")
    @GetMapping()
    public String activityGuests() {
        return prefix + "/activityGuests";
    }

    /**
     * 查询活动嘉宾列表
     */
    @RequiresPermissions("system:activityGuests:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ActivityGuests activityGuests) {
        startPage();
        List<ActivityGuests> list = activityGuestsService.selectActivityGuestsList(activityGuests);
        for (ActivityGuests guests : list) {
            // 查询商家名称
            Merchant merchant = merchantService.selectMerchantById(guests.getMerchantId());
            if (Optional.ofNullable(merchant).isPresent()) {
                guests.setMerchantName(merchant.getName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 新增活动嘉宾
     */
    @GetMapping("/add")
    public String add(@RequestParam("id") Long id, ModelMap mmap) {
    	mmap.put("id" , id);
        return prefix + "/add";
    }

    /**
     * 新增保存活动嘉宾
     */
    @RequiresPermissions("system:activityGuests:add")
    @Log(title = "活动嘉宾", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ActivityGuests activityGuests) {
        activityGuests.setCreateBy(getLoginName());
        activityGuests.setUpdateBy(getLoginName());
        return toAjax(activityGuestsService.insertActivityGuests(activityGuests));
    }

    /**
     * 修改活动嘉宾
     */
    @RequiresPermissions("system:activityGuests:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ActivityGuests activityGuests = activityGuestsService.selectActivityGuestsById(id);
        mmap.put("activityGuests", activityGuests);
        return prefix + "/edit";
    }

    /**
     * 修改保存活动嘉宾
     */
    @RequiresPermissions("system:activityGuests:edit")
    @Log(title = "活动嘉宾", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ActivityGuests activityGuests) {
        activityGuests.setUpdateBy(getLoginName());
        return toAjax(activityGuestsService.updateActivityGuests(activityGuests));
    }

    /**
     * 删除活动嘉宾
     */
    @RequiresPermissions("system:activityGuests:remove")
    @Log(title = "活动嘉宾", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(activityGuestsService.deleteActivityGuestsByIds(ids));
    }

}