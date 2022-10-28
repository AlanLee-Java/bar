package com.ruoyi.applet.web;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ruoyi.applet.domain.User;
import com.ruoyi.applet.model.dto.AppletLoginDTO;
import com.ruoyi.applet.model.vo.AppletLoginVO;
import com.ruoyi.applet.service.UserService;
import com.ruoyi.common.core.domain.AppletResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-09
 */
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 用户小程序登录
     *
     * @param appletLoginDTO
     * @return
     */
    @ApiOperation("用户小程序登录接口")
    @PostMapping("/appletLogin")
    public AppletResult appletLogin(@RequestBody @Validated AppletLoginDTO appletLoginDTO) throws Exception {
        AppletLoginVO appletLoginVO = userService.appletLogin(appletLoginDTO);
        return AppletResult.success(appletLoginVO);
    }

    /**
     * 用户小程序退出登录
     *
     * @return
     */
    @ApiOperation("用户小程序退出登录接口")
    @PostMapping("/appletLogout")
    public AppletResult appletLogout() {
        // 获取当前登录用户
        User user = userService.getUserByRequest(request);
        if (!Optional.ofNullable(user).isPresent()) {
            return AppletResult.loginUserNotExist("登录用户不存在");
        }
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("access_token", "");
        userUpdateWrapper.eq("id", user.getId());
        boolean result = userService.update(userUpdateWrapper);
        return AppletResult.success(result);
    }

}