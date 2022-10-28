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
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.model.dto.OrdersVerificationDTO;
import com.ruoyi.system.service.IMerchantService;
import com.ruoyi.system.service.IOrdersService;

import cn.hutool.core.collection.CollUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 订单Controller
 *
 * @author AlanLee
 * @date 2022-09-01
 */
@Controller
@RequestMapping("/system/orders")
@Api(tags = "订单相关接口")
public class OrdersController extends BaseController {

    private String prefix = "system/orders";

    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:orders:view")
    @GetMapping()
    public String orders() {
        return prefix + "/orders";
    }

    /**
     * 查询订单列表
     */
    @RequiresPermissions("system:orders:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Orders orders) {
        startPage();
		// 获取当前登录用户
		SysUser user = getSysUser();
		// 获取用户角色列表
		List<SysRole> roles = user.getRoles();
		// 角色列表不为空并且角色为商家管理员
		if (CollUtil.isNotEmpty(roles) && roles.get(0).getRoleId().equals(Merchant.ROLE_MERCHANT)) {
			Long mId = merchantService.getIdByUserId(user.getUserId());
			orders.setMerchantId(mId);
		}
        List<Orders> list = ordersService.selectOrdersList(orders);
        
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @RequiresPermissions("system:orders:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Orders orders) {
		// 获取当前登录用户
		SysUser user = getSysUser();
		// 获取用户角色列表
		List<SysRole> roles = user.getRoles();
		// 角色列表不为空并且角色为商家管理员
		if (CollUtil.isNotEmpty(roles) && roles.get(0).getRoleId().equals(Merchant.ROLE_MERCHANT)) {
			Long mId = merchantService.getIdByUserId(user.getUserId());
			orders.setMerchantId(mId);
		}
        List<Orders> list = ordersService.selectOrdersList(orders);
        ExcelUtil<Orders> util = new ExcelUtil<Orders>(Orders.class);
        return util.exportExcel(list, "订单数据");
    }

    /**
     * 核销
     */
    @GetMapping("/hexiao/{id}")
    public String hexiao(@PathVariable("id") Long id, ModelMap mmap) {
    	Orders orders = ordersService.selectOrdersById(id);
        mmap.put("order", orders);
        return prefix + "/hexiao";
    }

    
    /**
     * 核销订单
     */
    @RequiresPermissions("system:orders:hexiao")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/verification")
    @ResponseBody
    @ApiOperation("核销订单接口")
    public AjaxResult verification(OrdersVerificationDTO ordersVerificationDTO) {
        Long mId = merchantService.getIdByUserId(getUserId());
        if (!Optional.ofNullable(mId).isPresent()) {
            return error("用户未绑定商家");
        }
        // 根据核销码查询订单
        Orders orders = ordersService.selectByVerificationCode(ordersVerificationDTO.getVerificationCode());
        if (!Optional.ofNullable(orders).isPresent()) {
            throw new ServiceException("订单不存在");
        }
        if (!orders.getMerchantId().equals(mId)) {
            throw new ServiceException("只能核销本商家的订单");
        }
        int row = ordersService.verification(ordersVerificationDTO);
        if (row <= 0) {
            throw new ServiceException("核销失败");
        }
        return success();
    }
    
    /**
     * 详情
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, ModelMap mmap) {
    	Orders orders = ordersService.selectOrdersById(id);
        mmap.put("order", orders);
        return prefix + "/view";
    }

}