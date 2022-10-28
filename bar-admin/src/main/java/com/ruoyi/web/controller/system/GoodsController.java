package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.Optional;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.GoodsType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.domain.Goods;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.SetMeal;
import com.ruoyi.system.mapper.ActivityMapper;
import com.ruoyi.system.service.IGoodsService;
import com.ruoyi.system.service.IMerchantService;
import com.ruoyi.system.service.ISetMealService;

import cn.hutool.core.collection.CollUtil;

/**
 * 商品Controller
 *
 * @author AlanLee
 * @date 2022-08-29
 */
@Controller
@RequestMapping("/system/goods")
public class GoodsController extends BaseController {

    private String prefix = "system/goods";

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISetMealService setMealService;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:goods:view")
    @GetMapping()
    public String goods() {
        return prefix + "/goods";
    }

    /**
     * 查询商品列表
     */
    @RequiresPermissions("system:goods:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Goods goods) {
        startPage();
        // 获取当前登录用户
        SysUser user = getSysUser();
        // 获取用户角色列表
        List<SysRole> roles = user.getRoles();
        // 角色列表不为空并且角色为商家管理员
        if (CollUtil.isNotEmpty(roles) && roles.get(0).getRoleId().equals(Merchant.ROLE_MERCHANT)) {
            Long mId = merchantService.getIdByUserId(user.getUserId());
            goods.setMerchantId(mId);
        }
        List<Goods> list = goodsService.selectGoodsList(goods);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @RequiresPermissions("system:goods:export")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Goods goods) {
        List<Goods> list = goodsService.selectGoodsList(goods);
        ExcelUtil<Goods> util = new ExcelUtil<Goods>(Goods.class);
        return util.exportExcel(list, "商品数据");
    }

    /**
     * 新增商品
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存商品
     */
    @RequiresPermissions("system:goods:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Goods goods) {
        goods.setCreateBy(getLoginName());
        goods.setUpdateBy(getLoginName());
        return toAjax(goodsService.insertGoods(goods));
    }

    /**
     * 修改商品
     */
    @RequiresPermissions("system:goods:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Goods goods = goodsService.selectGoodsById(id);
        mmap.put("goods", goods);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品
     */
    @RequiresPermissions("system:goods:edit")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Goods goods) {
        goods.setUpdateBy(getLoginName());
        return toAjax(goodsService.updateGoods(goods));
    }

    /**
     * 删除商品
     */
    @RequiresPermissions("system:goods:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(goodsService.deleteGoodsByIds(ids));
    }


    /**
     * 新增散票商品
     */
    @GetMapping("/scattered")
    public String scattered(ModelMap mmap) {
		// 根据登录账号，查询该商户下是否有散票，有就返回
		Merchant m = merchantService.getBySysUserId(getUserId());
		if (!Optional.ofNullable(m).isPresent()) {
			throw new ServiceException("用户未绑定商家");
		}
		Goods find = new Goods();
		find.setMerchantId(m.getId());
		find.setType(1); // 散票
		List<Goods> list = goodsService.selectGoodsList(find);
		Goods good = new Goods();
		if (list != null && list.size() > 0) {
			good = list.get(0);
		} else {
			good.setMerchantId(m.getId());
			good.setMerchantName(m.getName());
		}
		// 查询散票 TODO
		mmap.put("goods", good);
		return prefix + "/scattered";
    }
    
    
    /**
     * 审核商品
     */
    @RequiresPermissions("system:goods:check")
    @GetMapping("/check/{id}")
    public String check(@PathVariable("id") Long id, ModelMap mmap) {
        Goods goods = goodsService.selectGoodsById(id);
        //活动票
        if(goods!=null && goods.getType() == GoodsType.ACTIVITY_TICKET.getType()){
        	
        	Activity activity = activityMapper.selectActivityByGoodsId(id);
        	mmap.put("activity" , activity);
        }
        // 套餐票
        else if(goods!=null && goods.getType() == GoodsType.PACKAGE.getType()){
        	SetMeal find = new SetMeal();
        	find.setGoodsId(id);
        	find = setMealService.selectSetMealList(find).get(0);
        	mmap.put("setMeal" , find);
        	mmap.put("setMealPics", find.getPicture().split(","));
        }
        mmap.put("goods", goods);
        return prefix + "/check";
    }

    /**
     * 修改审核商品
     */
    @RequiresPermissions("system:goods:check")
    @Log(title = "审核商品", businessType = BusinessType.UPDATE)
    @PostMapping("/check")
    @ResponseBody
    public AjaxResult checkSave(Goods goods) {
    	Goods newGood = new Goods();
    	newGood.setId(goods.getId());
    	if(goods.getStatus() == 1){
    		newGood.setStatus(Goods.STATUS_CHECK_SUCCESS);
    	}else{
    		newGood.setStatus(Goods.STATUS_CHECK_FAIL);
    	}
    	newGood.setUpdateBy(getLoginName());
        return toAjax(goodsService.updateGoods(newGood));
    }
    
    /**
     * 下架商品
     */
    @RequiresPermissions("system:goods:close")
    @Log(title = "下架商品", businessType = BusinessType.UPDATE)
    @PostMapping("/close/{id}")
    @ResponseBody
    public AjaxResult closeSave(@PathVariable("id") Long id) {
    	Goods goods = goodsService.selectGoodsById(id);
    	if(goods == null || goods.getStatus() != Goods.STATUS_CHECK_SUCCESS){
    		return error("操作商品状态异常！");
    	}
    	Goods newGood = new Goods();
    	newGood.setId(goods.getId());
    	newGood.setStatus(Goods.STATUS_XIAJIA);
    	newGood.setUpdateBy(getLoginName());
        return toAjax(goodsService.updateGoods(newGood));
    }
    
    /**
     * 重新提交商品
     */
    @RequiresPermissions("system:goods:againDeliver")
    @Log(title = "重新提交商品", businessType = BusinessType.UPDATE)
    @PostMapping("/againDeliver/{id}")
    @ResponseBody
    public AjaxResult againDeliver(@PathVariable("id") Long id) {
    	Goods goods = goodsService.selectGoodsById(id);
    	if(goods == null || goods.getStatus() != Goods.STATUS_XIAJIA){
    		return error("操作商品状态异常！");
    	}
    	Goods newGood = new Goods();
    	newGood.setId(goods.getId());
    	newGood.setStatus(Goods.STATUS_DAI_CHECK);
    	newGood.setUpdateBy(getLoginName());
        return toAjax(goodsService.updateGoods(newGood));
    }
}
