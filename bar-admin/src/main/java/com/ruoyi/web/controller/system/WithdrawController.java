package com.ruoyi.web.controller.system;

import cn.hutool.core.collection.CollUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.RefundStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.Withdraw;
import com.ruoyi.system.service.IMerchantService;
import com.ruoyi.system.service.IWithdrawService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 提现申请Controller
 *
 * @author AlanLee
 * @date 2022-10-11
 */
@Controller
@RequestMapping("/system/withdraw")
@Api(tags = "提现申请相关接口")
public class WithdrawController extends BaseController {
    private String prefix = "system/withdraw";

    @Autowired
    private IWithdrawService withdrawService;

    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:withdraw:view")
    @GetMapping()
    public String withdraw() {
        return prefix + "/withdraw";
    }

    /**
     * 查询提现申请列表
     */
    @RequiresPermissions("system:withdraw:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Withdraw withdraw) {
        startPage();
        // 获取当前登录用户
        SysUser user = getSysUser();
        // 获取用户角色列表
        List<SysRole> roles = user.getRoles();
        // 角色列表不为空并且角色为商家管理员
        if (CollUtil.isNotEmpty(roles) && roles.get(0).getRoleId().equals(Merchant.ROLE_MERCHANT)) {
            Long mId = merchantService.getIdByUserId(user.getUserId());
            withdraw.setMerchantId(mId);
        }
        List<Withdraw> list = withdrawService.selectWithdrawList(withdraw);
        for (Withdraw withdraw1 : list) {
            // 查询商家名称
            Merchant merchant = merchantService.selectMerchantById(withdraw1.getMerchantId());
            if (Optional.ofNullable(merchant).isPresent()) {
                withdraw1.setMerchantName(merchant.getName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出提现申请列表
     */
    @RequiresPermissions("system:withdraw:export")
    @Log(title = "提现申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Withdraw withdraw) {
        List<Withdraw> list = withdrawService.selectWithdrawList(withdraw);
        ExcelUtil<Withdraw> util = new ExcelUtil<Withdraw>(Withdraw.class);
        return util.exportExcel(list, "提现申请数据");
    }

    /**
     * 新增提现申请
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        Merchant merchant = merchantService.getBySysUserId(getUserId());
        mmap.put("merchant", merchant);
        return prefix + "/add";
    }

    /**
     * 新增保存提现申请
     */
    @RequiresPermissions("system:withdraw:add")
    @Log(title = "提现申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation("申请提现接口")
    public AjaxResult addSave(Withdraw withdraw) {
        Merchant merchant = merchantService.getBySysUserId(getUserId());
        if (!Optional.ofNullable(merchant).isPresent()) {
            return error("用户未绑定商家");
        }
        // 根据商家ID查询是否有提现中的提现
        Withdraw withdrawOne = withdrawService.selectWithdrawByMerchantId(merchant.getId());
        if (Optional.ofNullable(withdrawOne).isPresent()) {
            return error("本商家还有提现中的提现申请");
        }
        if (merchant.getBalance().compareTo(withdraw.getAmount()) < 0) {
            return error("本商家余额不足以提现！");
        }
        withdraw.setWithdrawType(merchant.getType());
        withdraw.setMerchantId(merchant.getId());
        withdraw.setWechatId(merchant.getWechatId());
        withdraw.setAlipayId(merchant.getAlipayId());
        withdraw.setCollectionName(merchant.getCollectionName());
        withdraw.setCollectionCardNo(merchant.getCollectionCardNo());
        withdraw.setCollectionBank(merchant.getCollectionBank());
        withdraw.setStatus(0);
        withdraw.setCreateBy(getLoginName());
        withdraw.setUpdateBy(getLoginName());
        return toAjax(withdrawService.insertWithdraw(withdraw));
    }

    /**
     * 修改提现申请
     */
    @RequiresPermissions("system:withdraw:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Withdraw withdraw = withdrawService.selectWithdrawById(id);
        withdraw.setHandleTime(DateUtils.getNowDate()); //默认当前时间
        mmap.put("withdraw", withdraw);
        return prefix + "/edit";
    }
    
    /**
     * 详情
     */
    @RequiresPermissions("system:withdraw:view")
    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, ModelMap mmap) {
        Withdraw withdraw = withdrawService.selectWithdrawById(id);
        withdraw.setHandleTime(DateUtils.getNowDate()); //默认当前时间
        mmap.put("withdraw", withdraw);
        return prefix + "/view";
    }

    /**
     * 修改保存提现申请
     */
    @RequiresPermissions("system:withdraw:edit")
    @Log(title = "提现申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation("处理提现接口")
    public AjaxResult editSave(Withdraw withdraw) {
    	
		Merchant merchant = merchantService.selectMerchantById(withdraw.getMerchantId());
		if (!Optional.ofNullable(merchant).isPresent()) {
			return error("商家不存在！");
		}
		
		Withdraw find = withdrawService.selectWithdrawById(withdraw.getId());
		if (!Optional.ofNullable(find).isPresent()) {
			return error("错误提现信息！");
		}
		
		if (merchant.getBalance().compareTo(find.getAmount()) < 0) {
			return error("本商家余额不足以提现！");
		}
		//同意提现
		Withdraw newObj = new Withdraw();
		newObj.setId(withdraw.getId());
		newObj.setMerchantId(find.getMerchantId()); //商户id
		newObj.setAmount(find.getAmount());			//需要计算金额
		newObj.setHandleBy(withdraw.getHandleBy());
		newObj.setHandleTime(withdraw.getHandleTime());
		newObj.setHandleRemark(withdraw.getHandleRemark()); //处理备注
		newObj.setProof(withdraw.getProof());
		if(withdraw.getStatus() == 1){
			if(find.getStatus() != RefundStatus.SUBMIT.getStatus()){
				return error("提现订单状态不允许！");
			}
			newObj.setStatus(RefundStatus.SUCCESS.getStatus());
			newObj.setProof(newObj.getProof()); //提现凭证
		}else{
			newObj.setStatus(RefundStatus.FAIL.getStatus());
			
		}
        return toAjax(withdrawService.updateWithdraw(newObj));
    }

}