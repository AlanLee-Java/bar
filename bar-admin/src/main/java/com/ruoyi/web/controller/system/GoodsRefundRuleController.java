package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.GoodsRefundRule;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.service.IGoodsRefundRuleService;
import com.ruoyi.system.service.IMerchantService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 商品退款规则Controller
 *
 * @author AlanLee
 * @date 2022-08-29
 */
@Controller
@RequestMapping("/system/goodsRefundRule")
public class GoodsRefundRuleController extends BaseController {

    private String prefix = "system/goodsRefundRule";

    @Autowired
    private IGoodsRefundRuleService goodsRefundRuleService;

    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:goodsRefundRule:view")
    @GetMapping()
    public String goodsRefundRule() {
        return prefix + "/goodsRefundRule";
    }

    /**
     * 查询商品退款规则列表
     */
    @RequiresPermissions("system:goodsRefundRule:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GoodsRefundRule goodsRefundRule) {
        startPage();
        List<GoodsRefundRule> list = goodsRefundRuleService.selectGoodsRefundRuleList(goodsRefundRule);
        for (GoodsRefundRule refundRule : list) {
            // 查询商家名称
            Merchant merchant = merchantService.selectMerchantById(refundRule.getMerchantId());
            if (Optional.ofNullable(merchant).isPresent()) {
                refundRule.setMerchantName(merchant.getName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 新增商品退款规则
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存商品退款规则
     */
    @RequiresPermissions("system:goodsRefundRule:add")
    @Log(title = "商品退款规则", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GoodsRefundRule goodsRefundRule) {
        goodsRefundRule.setCreateBy(getLoginName());
        goodsRefundRule.setUpdateBy(getLoginName());
        return toAjax(goodsRefundRuleService.insertGoodsRefundRule(goodsRefundRule));
    }

    /**
     * 修改商品退款规则
     */
    @RequiresPermissions("system:goodsRefundRule:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        GoodsRefundRule goodsRefundRule = goodsRefundRuleService.selectGoodsRefundRuleById(id);
        mmap.put("goodsRefundRule", goodsRefundRule);
        return prefix + "/edit";
    }

    /**
     * 修改保存商品退款规则
     */
    @RequiresPermissions("system:goodsRefundRule:edit")
    @Log(title = "商品退款规则", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GoodsRefundRule goodsRefundRule) {
        goodsRefundRule.setUpdateBy(getLoginName());
        return toAjax(goodsRefundRuleService.updateGoodsRefundRule(goodsRefundRule));
    }

    /**
     * 删除商品退款规则
     */
    @RequiresPermissions("system:goodsRefundRule:remove")
    @Log(title = "商品退款规则", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(goodsRefundRuleService.deleteGoodsRefundRuleByIds(ids));
    }

}