package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.MerchantTopic;
import com.ruoyi.system.service.IMerchantService;
import com.ruoyi.system.service.IMerchantTopicService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 商家话题Controller
 *
 * @author AlanLee
 * @date 2022-08-29
 */
@Controller
@RequestMapping("/system/merchantTopic")
public class MerchantTopicController extends BaseController {

    private String prefix = "system/merchantTopic";

    @Autowired
    private IMerchantTopicService merchantTopicService;

    @Autowired
    private IMerchantService merchantService;

    @RequiresPermissions("system:merchantTopic:view")
    @GetMapping()
    public String merchantTopic(@RequestParam("id") Long id,@RequestParam("name") String name, ModelMap mmap)
    {
    	mmap.put("mid", id); //商家id
    	mmap.put("name", name); //商家name
        return prefix + "/merchantTopic";
    }

    /**
     * 查询商家话题列表
     */
    @RequiresPermissions("system:merchantTopic:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MerchantTopic merchantTopic) {
        startPage();
        List<MerchantTopic> list = merchantTopicService.selectMerchantTopicList(merchantTopic);
        for (MerchantTopic topic : list) {
            // 查询商家名称
            Merchant merchant = merchantService.selectMerchantById(topic.getMerchantId());
            if (Optional.ofNullable(merchant).isPresent()) {
                topic.setMerchantName(merchant.getName());
            }
        }
        return getDataTable(list);
    }

    /**
     * 新增商家话题
     */
    @GetMapping("/add")
    public String add(@RequestParam("id") Long id, ModelMap mmap)
    {
    	mmap.put("mid", id); //商家id
        return prefix + "/add";
    }

    /**
     * 新增保存商家话题
     */
    @RequiresPermissions("system:merchantTopic:add")
    @Log(title = "商家话题", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MerchantTopic merchantTopic) {
        merchantTopic.setCreateBy(getLoginName());
        merchantTopic.setUpdateBy(getLoginName());
        return toAjax(merchantTopicService.insertMerchantTopic(merchantTopic));
    }

    /**
     * 修改商家话题
     */
    @RequiresPermissions("system:merchantTopic:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        MerchantTopic merchantTopic = merchantTopicService.selectMerchantTopicById(id);
        mmap.put("merchantTopic", merchantTopic);
        return prefix + "/edit";
    }

    /**
     * 修改保存商家话题
     */
    @RequiresPermissions("system:merchantTopic:edit")
    @Log(title = "商家话题", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MerchantTopic merchantTopic) {
        merchantTopic.setUpdateBy(getLoginName());
        return toAjax(merchantTopicService.updateMerchantTopic(merchantTopic));
    }

    /**
     * 删除商家话题
     */
    @RequiresPermissions("system:merchantTopic:remove")
    @Log(title = "商家话题", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(merchantTopicService.deleteMerchantTopicByIds(ids));
    }

}