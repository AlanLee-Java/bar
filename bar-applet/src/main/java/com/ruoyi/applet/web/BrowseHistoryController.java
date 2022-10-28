package com.ruoyi.applet.web;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.applet.domain.BrowseHistory;
import com.ruoyi.applet.domain.User;
import com.ruoyi.applet.model.dto.BrowseHistoryAddDTO;
import com.ruoyi.applet.model.vo.BrowseHistoryQueryVO;
import com.ruoyi.applet.service.BrowseHistoryService;
import com.ruoyi.applet.service.UserService;
import com.ruoyi.common.core.domain.AppletResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * 浏览历史 前端控制器
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-17
 */
@Api(tags = "浏览历史相关接口")
@RestController
@RequestMapping("/browseHistory")
public class BrowseHistoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private BrowseHistoryService browseHistoryService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 浏览历史分页查询
     *
     * @param type
     * @param page
     * @return
     */
    @ApiOperation("浏览历史分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "浏览类型：1商家，2活动", dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页大小", dataType = "int", paramType = "query", defaultValue = "20")
    })
    @GetMapping("/page")
    public AppletResult page(@RequestParam("type") Integer type, @ApiIgnore Page<BrowseHistoryQueryVO> page) {
        // 获取当前登录用户
        User user = userService.getUserByRequest(request);
        if (!Optional.ofNullable(user).isPresent()) {
            return AppletResult.loginUserNotExist("登录用户不存在");
        }
        Page<BrowseHistoryQueryVO> browseHistoryQueryVOPage = browseHistoryService.queryBrowseHistoryPage(page, user.getId(), type);
        return AppletResult.success(browseHistoryQueryVOPage);
    }

    /**
     * 新增浏览历史
     *
     * @param browseHistoryAddDTO
     * @return
     */
    @ApiOperation("新增浏览历史")
    @PostMapping("/add")
    public AppletResult add(@RequestBody @Validated BrowseHistoryAddDTO browseHistoryAddDTO) {
        // 获取当前登录用户
        User user = userService.getUserByRequest(request);
        if (!Optional.ofNullable(user).isPresent()) {
            return AppletResult.loginUserNotExist("登录用户不存在");
        }
        QueryWrapper<BrowseHistory> browseHistoryQueryWrapper = new QueryWrapper<>();
        browseHistoryQueryWrapper.eq("user_id", user.getId());
        browseHistoryQueryWrapper.eq("type", browseHistoryAddDTO.getType());
        browseHistoryQueryWrapper.eq("object_id", browseHistoryAddDTO.getObjectId());
        // 获取当前年月日
        String today = DateUtil.today();
        browseHistoryQueryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", today);
        BrowseHistory browseHistory = browseHistoryService.getOne(browseHistoryQueryWrapper);
        // 如果当天已存在该对象的浏览记录，则先删除当天老的浏览记录
        if (Optional.ofNullable(browseHistory).isPresent()) {
            browseHistoryService.removeById(browseHistory.getId());
        }
        // 新增当天浏览记录
        browseHistory = new BrowseHistory();
        browseHistory.setUserId(user.getId());
        browseHistory.setType(browseHistoryAddDTO.getType());
        browseHistory.setObjectId(browseHistoryAddDTO.getObjectId());
        browseHistory.setCreateTime(LocalDateTime.now());
        browseHistory.setUpdateTime(LocalDateTime.now());
        boolean result = browseHistoryService.save(browseHistory);
        return AppletResult.success(result);
    }

}