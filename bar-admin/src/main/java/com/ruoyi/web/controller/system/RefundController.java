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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.Refund;
import com.ruoyi.system.service.IMerchantService;
import com.ruoyi.system.service.IRefundService;

import cn.hutool.core.collection.CollUtil;

/**
 * 退款单Controller
 *
 * @author AlanLee
 * @date 2022-09-22
 */
@Controller
@RequestMapping("/system/refund")
public class RefundController extends BaseController {

    private String prefix = "system/refund";

    @Autowired
    private IRefundService refundService;

    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:refund:view")
    @GetMapping()
    public String refund() {
        return prefix + "/refund";
    }

    /**
     * 查询退款单列表
     */
    @RequiresPermissions("system:refund:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Refund refund) {
        startPage();
		// 获取当前登录用户
		SysUser user = getSysUser();
		// 获取用户角色列表
		List<SysRole> roles = user.getRoles();
		// 角色列表不为空并且角色为商家管理员
		if (CollUtil.isNotEmpty(roles) && roles.get(0).getRoleId().equals(Merchant.ROLE_MERCHANT)) {
			Long mId = merchantService.getIdByUserId(user.getUserId());
			refund.setMerchantId(mId);
		}
        List<Refund> list = refundService.selectRefundList(refund);
        return getDataTable(list);
    }

    /**
     * 导出退款单列表
     */
    @RequiresPermissions("system:refund:export")
    @Log(title = "退款单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Refund refund) {
		// 获取当前登录用户
		SysUser user = getSysUser();
		// 获取用户角色列表
		List<SysRole> roles = user.getRoles();
		// 角色列表不为空并且角色为商家管理员
		if (CollUtil.isNotEmpty(roles) && roles.get(0).getRoleId().equals(Merchant.ROLE_MERCHANT)) {
			Long mId = merchantService.getIdByUserId(user.getUserId());
			refund.setMerchantId(mId);
		}
        List<Refund> list = refundService.selectRefundList(refund);
        ExcelUtil<Refund> util = new ExcelUtil<Refund>(Refund.class);
        return util.exportExcel(list, "退款单数据");
    }
    
    /**
     * 详情
     */
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, ModelMap mmap) {
    	Refund refund= refundService.selectRefundById(id);
        mmap.put("refund", refund);
        return prefix + "/view";
    }

}