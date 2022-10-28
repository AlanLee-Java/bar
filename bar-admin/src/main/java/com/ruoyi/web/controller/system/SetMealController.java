package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.SetMeal;
import com.ruoyi.system.service.IMerchantService;
import com.ruoyi.system.service.ISetMealService;

import cn.hutool.core.collection.CollUtil;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 套餐Controller
 *
 * @author AlanLee
 * @date 2022-09-01
 */
@Controller
@RequestMapping("/system/setMeal")
public class SetMealController extends BaseController {

    private String prefix = "system/setMeal";

    @Autowired
    private ISetMealService setMealService;

    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:setMeal:view")
    @GetMapping()
    public String setMeal() {
        return prefix + "/setMeal";
    }

    /**
     * 查询套餐列表
     */
    @RequiresPermissions("system:setMeal:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SetMeal setMeal) {
        startPage();
        // 获取当前登录用户
        SysUser user = getSysUser();
        // 获取用户角色列表
        List<SysRole> roles = user.getRoles();
        // 角色列表不为空并且角色为商家管理员
        if (CollUtil.isNotEmpty(roles) && roles.get(0).getRoleId().equals(Merchant.ROLE_MERCHANT)) {
            Long mId = merchantService.getIdByUserId(user.getUserId());
            setMeal.setMerchantId(mId);
        }
        List<SetMeal> list = setMealService.selectSetMealList(setMeal);
        for (SetMeal meal : list) {
            // 查询商家名称
            Merchant merchant = merchantService.selectMerchantById(meal.getMerchantId());
            if (Optional.ofNullable(merchant).isPresent()) {
                meal.setMerchantName(merchant.getName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出套餐列表
     */
    @RequiresPermissions("system:setMeal:export")
    @Log(title = "套餐", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SetMeal setMeal) {
        List<SetMeal> list = setMealService.selectSetMealList(setMeal);
        ExcelUtil<SetMeal> util = new ExcelUtil<SetMeal>(SetMeal.class);
        return util.exportExcel(list, "套餐数据");
    }

    /**
     * 新增套餐
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add2";
    }

    /**
     * 新增保存套餐
     */
    @RequiresPermissions("system:setMeal:add")
    @Log(title = "套餐", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SetMeal setMeal) {
		Long mId = merchantService.getIdByUserId(getUserId());
		if (!Optional.ofNullable(mId).isPresent()) {
			return error("用户未绑定商家");
		}
		setMeal.setMerchantId(mId);
		setMeal.setCreateBy(getLoginName());
		setMeal.setUpdateBy(getLoginName());
        return AjaxResult.success(setMealService.insertSetMeal(setMeal));
    }

    /**
     * 修改套餐
     */
    @RequiresPermissions("system:setMeal:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SetMeal setMeal = setMealService.selectSetMealById(id);
        mmap.put("setMeal", setMeal);
        return prefix + "/edit";
    }

    /**
     * 修改保存套餐
     */
    @RequiresPermissions("system:setMeal:edit")
    @Log(title = "套餐", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SetMeal setMeal) {
        setMeal.setUpdateBy(getLoginName());
        return toAjax(setMealService.updateSetMeal(setMeal));
    }

    /**
     * 删除套餐
     */
    @RequiresPermissions("system:setMeal:remove")
    @Log(title = "套餐", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(setMealService.deleteSetMealByIds(ids));
    }

}