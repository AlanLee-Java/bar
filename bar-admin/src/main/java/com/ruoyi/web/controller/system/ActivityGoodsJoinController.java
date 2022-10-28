package com.ruoyi.web.controller.system;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ActivityGoodsJoin;
import com.ruoyi.system.domain.Goods;
import com.ruoyi.system.service.IActivityGoodsJoinService;
import com.ruoyi.system.service.IGoodsService;

/**
 * 活动商品关联Controller
 *
 * @author AlanLee
 * @date 2022-08-28
 */
@Controller
@RequestMapping("/system/activityGoodsJoin")
public class ActivityGoodsJoinController extends BaseController {

    private String prefix = "system/activityGoodsJoin";

    @Autowired
    private IActivityGoodsJoinService activityGoodsJoinService;
    @Autowired
    private IGoodsService goodsService;


    @RequiresPermissions("system:activityGoodsJoin:view")
    @GetMapping()
    public String activityGoodsJoin(@RequestParam("id") Long id, ModelMap mmap)  {
    	mmap.put("activityId", id);
        return prefix + "/activityGoodsJoin";
    }

    /**
     * 查询活动商品关联列表
     */
    @RequiresPermissions("system:activityGoodsJoin:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ActivityGoodsJoin activityGoodsJoin,String goodName,
			Integer goodStatus) {
        startPage();
        List<ActivityGoodsJoin> list = activityGoodsJoinService.selectActivityGoodsJoinList(activityGoodsJoin,goodName,goodStatus);
        return getDataTable(list);
    }

    /**
     * 新增活动商品关联
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id, ModelMap mmap)  {
    	mmap.put("activityId", id);
        return prefix + "/add";
    }

    /**
     * 新增保存活动商品关联
     */
    @RequiresPermissions("system:activityGoodsJoin:add")
    @Log(title = "活动商品关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ActivityGoodsJoin activityGoodsJoin) {
    	
        if(activityGoodsJoin.getActivityId() == null ||activityGoodsJoin.getActivityId() == 0){
        	return error("请先保存活动！");
        }
        activityGoodsJoin.setCreateBy(getLoginName());
        activityGoodsJoin.setUpdateBy(getLoginName());
        return toAjax(activityGoodsJoinService.insertActivityGoodsJoin(activityGoodsJoin));
    }

    /**
     * 修改活动商品关联
     */
    @RequiresPermissions("system:activityGoodsJoin:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
    	 Goods good = goodsService.selectGoodsById(id);
         mmap.put("goods", good);
        return prefix + "/edit";
    }

    /**
     * 修改保存活动商品关联
     */
    @RequiresPermissions("system:activityGoodsJoin:edit")
    @Log(title = "活动商品关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Goods good) {
    	good.setUpdateBy(getLoginName());
        return toAjax(goodsService.updateGoods(good));
    }

    /**
     * 删除活动商品关联
     */
    @RequiresPermissions("system:activityGoodsJoin:remove")
    @Log(title = "活动商品关联", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(activityGoodsJoinService.deleteActivityGoodsJoinByIds(ids));
    }

}