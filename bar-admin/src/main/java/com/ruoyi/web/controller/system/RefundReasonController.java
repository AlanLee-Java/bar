package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.RefundReason;
import com.ruoyi.system.service.IMerchantService;
import com.ruoyi.system.service.IRefundReasonService;

import cn.hutool.core.collection.CollUtil;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 退款原因Controller
 *
 * @author AlanLee
 * @date 2022-09-22
 */
@Controller
@RequestMapping("/system/refundReason")
public class RefundReasonController extends BaseController {

    private String prefix = "system/refundReason";

    @Autowired
    private IRefundReasonService refundReasonService;

    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:refundReason:view")
    @GetMapping()
    public String refundReason() {
        return prefix + "/refundReason";
    }

    /**
     * 查询退款原因列表
     */
    @RequiresPermissions("system:refundReason:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RefundReason refundReason) {
        startPage();
		// 获取当前登录用户
		SysUser user = getSysUser();
		// 获取用户角色列表
		List<SysRole> roles = user.getRoles();
		// 角色列表不为空并且角色为商家管理员
		if (CollUtil.isNotEmpty(roles) && roles.get(0).getRoleId().equals(Merchant.ROLE_MERCHANT)) {
			Long mId = merchantService.getIdByUserId(user.getUserId());
			refundReason.setMerchantId(mId);
		}
        List<RefundReason> list = refundReasonService.selectRefundReasonList(refundReason);
        for (RefundReason reason : list) {
            // 查询商家名称
            Merchant merchant = merchantService.selectMerchantById(reason.getMerchantId());
            if (Optional.ofNullable(merchant).isPresent()) {
                reason.setMerchantName(merchant.getName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 新增退款原因
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存退款原因
     */
    @RequiresPermissions("system:refundReason:add")
    @Log(title = "退款原因", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RefundReason refundReason) {
    	Long mId = merchantService.getIdByUserId(getUserId());
		refundReason.setMerchantId(mId);
        refundReason.setCreateBy(getLoginName());
        refundReason.setUpdateBy(getLoginName());
        return toAjax(refundReasonService.insertRefundReason(refundReason));
    }

    /**
     * 修改退款原因
     */
    @RequiresPermissions("system:refundReason:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        RefundReason refundReason = refundReasonService.selectRefundReasonById(id);
        mmap.put("refundReason", refundReason);
        return prefix + "/edit";
    }

    /**
     * 修改保存退款原因
     */
    @RequiresPermissions("system:refundReason:edit")
    @Log(title = "退款原因", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RefundReason refundReason) {
        refundReason.setUpdateBy(getLoginName());
        return toAjax(refundReasonService.updateRefundReason(refundReason));
    }

    /**
     * 删除退款原因
     */
    @RequiresPermissions("system:refundReason:remove")
    @Log(title = "退款原因", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(refundReasonService.deleteRefundReasonByIds(ids));
    }

}