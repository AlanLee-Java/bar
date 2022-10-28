package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.SetMealItem;
import com.ruoyi.system.service.IMerchantService;
import com.ruoyi.system.service.ISetMealItemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 套餐项目Controller
 *
 * @author AlanLee
 * @date 2022-09-01
 */
@Controller
@RequestMapping("/system/setMealItem")
public class SetMealItemController extends BaseController {

    private String prefix = "system/setMealItem";

    @Autowired
    private ISetMealItemService setMealItemService;

    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:setMealItem:view")
    @GetMapping()
    public String setMealItem() {
        return prefix + "/setMealItem";
    }

    /**
     * 查询套餐项目列表
     */
    @RequiresPermissions("system:setMealItem:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SetMealItem setMealItem) {
        startPage();
        List<SetMealItem> list = setMealItemService.selectSetMealItemList(setMealItem);
        for (SetMealItem mealItem : list) {
            // 查询商家名称
            Merchant merchant = merchantService.selectMerchantById(mealItem.getMerchantId());
            if (Optional.ofNullable(merchant).isPresent()) {
                mealItem.setMerchantName(merchant.getName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出套餐项目列表
     */
    @RequiresPermissions("system:setMealItem:export")
    @Log(title = "套餐项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SetMealItem setMealItem) {
        List<SetMealItem> list = setMealItemService.selectSetMealItemList(setMealItem);
        ExcelUtil<SetMealItem> util = new ExcelUtil<SetMealItem>(SetMealItem.class);
        return util.exportExcel(list, "套餐项目数据");
    }

    /**
     * 新增套餐项目
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("id", id);
        return prefix + "/add";
    }

    /**
     * 新增保存套餐项目
     */
    @RequiresPermissions("system:setMealItem:add")
    @Log(title = "套餐项目", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SetMealItem setMealItem) {
        setMealItem.setCreateBy(getLoginName());
        setMealItem.setUpdateBy(getLoginName());
        return AjaxResult.success(setMealItemService.insertSetMealItem(setMealItem));
    }

    /**
     * 修改套餐项目
     */
    @RequiresPermissions("system:setMealItem:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SetMealItem setMealItem = setMealItemService.selectSetMealItemById(id);
        mmap.put("setMealItem", setMealItem);
        return prefix + "/edit";
    }

    /**
     * 修改保存套餐项目
     */
    @RequiresPermissions("system:setMealItem:edit")
    @Log(title = "套餐项目", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SetMealItem setMealItem) {
        setMealItem.setUpdateBy(getLoginName());
        return toAjax(setMealItemService.updateSetMealItem(setMealItem));
    }

    /**
     * 删除套餐项目
     */
    @RequiresPermissions("system:setMealItem:remove")
    @Log(title = "套餐项目", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(setMealItemService.deleteSetMealItemByIds(ids));
    }

}