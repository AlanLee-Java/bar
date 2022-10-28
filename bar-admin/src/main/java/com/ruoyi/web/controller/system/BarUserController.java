package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.BarUser;
import com.ruoyi.system.service.IBarUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * bar用户Controller
 *
 * @author AlanLee
 * @date 2022-09-22
 */
@Controller
@RequestMapping("/system/barUser")
public class BarUserController extends BaseController {

    private String prefix = "system/barUser";

    @Autowired
    private IBarUserService barUserService;

    @RequiresPermissions("system:barUser:view")
    @GetMapping()
    public String barUser() {
        return prefix + "/barUser";
    }

    /**
     * 查询bar用户列表
     */
    @RequiresPermissions("system:barUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BarUser barUser) {
        startPage();
        List<BarUser> list = barUserService.selectBarUserList(barUser);
        return getDataTable(list);
    }

}