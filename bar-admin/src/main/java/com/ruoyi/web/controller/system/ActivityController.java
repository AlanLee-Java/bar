package com.ruoyi.web.controller.system;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.service.IActivityService;
import com.ruoyi.system.service.IMerchantService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 活动Controller
 *
 * @author AlanLee
 * @date 2022-08-28
 */
@Controller
@RequestMapping("/system/activity")
public class ActivityController extends BaseController {

    private String prefix = "system/activity";

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:activity:view")
    @GetMapping()
    public String activity() {
        return prefix + "/activity";
    }

    /**
     * 查询活动列表
     */
    @RequiresPermissions("system:activity:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Activity activity) {
        startPage();
        // 获取当前登录用户
        SysUser user = getSysUser();
        // 获取用户角色列表
        List<SysRole> roles = user.getRoles();
        // 角色列表不为空并且角色为商家管理员
        if (CollUtil.isNotEmpty(roles) && roles.get(0).getRoleId().equals(Merchant.ROLE_MERCHANT)) {
            Long mId = merchantService.getIdByUserId(user.getUserId());
            activity.setMerchantId(mId);
        }
        List<Activity> list = activityService.selectActivityList(activity);
        for (Activity activity1 : list) {
            // 查询商家名称
            Merchant merchant = merchantService.selectMerchantById(activity1.getMerchantId());
            if (Optional.ofNullable(merchant).isPresent()) {
                activity1.setMerchantName(merchant.getName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 新增活动
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add2";
    }

    /**
     * 新增保存活动
     */
    @RequiresPermissions("system:activity:add")
    @Log(title = "活动", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Activity activity) {
        Long mId = merchantService.getIdByUserId(getUserId());
        if (!Optional.ofNullable(mId).isPresent()) {
            return error("用户未绑定商家");
        }
        activity.setMerchantId(mId);
        activity.setCreateBy(getLoginName());
        activity.setUpdateBy(getLoginName());
        return AjaxResult.success(activityService.insertActivity(activity));
    }

    /**
     * 修改活动
     */
    @RequiresPermissions("system:activity:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Activity activity = activityService.selectActivityById(id);
        mmap.put("activity", activity);
        return prefix + "/edit2";
    }

    /**
     * 修改保存活动
     */
    @RequiresPermissions("system:activity:edit")
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Activity activity) {
        activity.setUpdateBy(getLoginName());
        return toAjax(activityService.updateActivity(activity));
    }

    /**
     * 删除活动
     */
    @RequiresPermissions("system:activity:remove")
    @Log(title = "活动", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(activityService.deleteActivityByIds(ids));
    }

}