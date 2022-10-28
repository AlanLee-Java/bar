package com.ruoyi.applet.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.applet.domain.SysDictData;
import com.ruoyi.applet.service.SysDictDataService;
import com.ruoyi.common.core.domain.AppletResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author AlanLee
 * @since 2022-09-12
 */
@RestController
@RequestMapping("/sysDictData")
@Api(tags = "字典数据相关接口")
public class SysDictDataController {

    @Autowired
    private SysDictDataService sysDictDataService;

    @GetMapping("/hotCity")
    @ApiOperation("热门城市")
    public AppletResult hotCity() {
        String dictType = "applet_hot_city";
        QueryWrapper<SysDictData> sysDictDataQueryWrapper = new QueryWrapper<>();
        sysDictDataQueryWrapper.eq("dict_type", dictType);
        sysDictDataQueryWrapper.eq("status", 0);
        List<SysDictData> sysDictDataList = sysDictDataService.list(sysDictDataQueryWrapper);
        return AppletResult.success(sysDictDataList);
    }

    @GetMapping("/hotSearch")
    @ApiOperation("热门搜索")
    public AppletResult hotSearch() {
        String dictType = "applet_hot_search";
        QueryWrapper<SysDictData> sysDictDataQueryWrapper = new QueryWrapper<>();
        sysDictDataQueryWrapper.eq("dict_type", dictType);
        sysDictDataQueryWrapper.eq("status", 0);
        List<SysDictData> sysDictDataList = sysDictDataService.list(sysDictDataQueryWrapper);
        return AppletResult.success(sysDictDataList);
    }

}