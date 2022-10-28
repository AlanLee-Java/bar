package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.model.dto.MerchantSysUserIdDTO;
import com.ruoyi.system.service.IMerchantService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 商家管理Controller
 *
 * @author AlanLee
 * @date 2022-08-11
 */
@Controller
@RequestMapping("/system/merchant")
@Api(tags = "商家管理相关接口")
public class MerchantController extends BaseController {

    private String prefix = "system/merchant";

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @RequiresPermissions("system:merchant:view")
    @GetMapping()
    public String merchant() {
        return prefix + "/merchant";
    }

    /**
     * 查询商家管理列表
     */
    @RequiresPermissions("system:merchant:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Merchant merchant) {
        startPage();
        List<Merchant> list = merchantService.selectMerchantList(merchant);
        return getDataTable(list);
    }

    /**
     * 新增商家管理
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存商家管理
     */
    @RequiresPermissions("system:merchant:add")
    @Log(title = "商家管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Merchant merchant) {
        merchant.setCreateBy(getLoginName());
        merchant.setUpdateBy(getLoginName());
        return toAjax(merchantService.insertMerchant(merchant));
    }

    /**
     * 修改商家管理
     */
    @RequiresPermissions("system:merchant:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Merchant merchant = merchantService.selectMerchantById(id);
        mmap.put("merchant", merchant);
        return prefix + "/edit";
    }

    /**
     * 修改保存商家管理
     */
    @RequiresPermissions("system:merchant:edit")
    @Log(title = "商家管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Merchant merchant) {
        merchant.setUpdateBy(getLoginName());
        merchant.setBalance(null); //不允许修改余额
        return toAjax(merchantService.updateMerchant(merchant));
    }

    /**
     * 修改保存商家管理员ID
     */
    @RequiresPermissions("system:merchant:edit")
    @Log(title = "商家管理", businessType = BusinessType.UPDATE)
    @PostMapping("/editSysUserId")
    @ResponseBody
    @ApiOperation("修改保存商家管理员ID")
    public AjaxResult editSysUserId(@Validated MerchantSysUserIdDTO merchantSysUserIdDTO) {
        Merchant merchant = new Merchant();
        merchant.setId(merchantSysUserIdDTO.getId());
        merchant.setSysUserId(merchantSysUserIdDTO.getSysUserId());
        merchant.setUpdateBy(getLoginName());
        try {
            return toAjax(merchantService.updateMerchant(merchant));
        } catch (Exception e) {
            return error("该账号已被绑定！");
        }
    }

    /**
     * 获取用户绑定的商家信息
     */
    @GetMapping("/getUserMerchant")
    @ResponseBody
    @ApiOperation("获取用户绑定的商家信息")
    public AjaxResult getUserMerchant() {
        // 用户ID
        Long userId = getUserId();
        Merchant merchant = merchantService.getBySysUserId(userId);
        if (!Optional.ofNullable(merchant).isPresent()) {
            throw new ServiceException("用户未绑定商家");
        }
        return AjaxResult.success(merchant);
    }

    /**
     * 删除商家管理
     */
    @RequiresPermissions("system:merchant:remove")
    @Log(title = "商家管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(merchantService.deleteMerchantByIds(ids));
    }

    /**
     * 选择用户
     */
    @RequiresPermissions("system:merchant:selectUser")
    @GetMapping("/selectUser/{id}")
    public String selectUser(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("id", id);
        mmap.put("role", roleService.selectRoleById(3L));
        return prefix + "/selectUser";
    }

    /**
     * 查询未分配用户角色列表
     */
    @RequiresPermissions("system:merchant:selectUser")
    @PostMapping("/unallocatedList")
    @ResponseBody
    public TableDataInfo unallocatedList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectAllocatedList(user);
        return getDataTable(list);
    }

}