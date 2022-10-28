package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.BrowseHistory;
import com.ruoyi.system.service.IBrowseHistoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 浏览历史Controller
 *
 * @author AlanLee
 * @date 2022-09-22
 */
@Controller
@RequestMapping("/system/browseHistory")
public class BrowseHistoryController extends BaseController {

    private String prefix = "system/browseHistory";

    @Autowired
    private IBrowseHistoryService browseHistoryService;

    @RequiresPermissions("system:browseHistory:view")
    @GetMapping()
    public String browseHistory() {
        return prefix + "/browseHistory";
    }

    /**
     * 查询浏览历史列表
     */
    @RequiresPermissions("system:browseHistory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BrowseHistory browseHistory) {
        startPage();
        List<BrowseHistory> list = browseHistoryService.selectBrowseHistoryList(browseHistory);
        return getDataTable(list);
    }

}