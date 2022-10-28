package com.ruoyi.applet.support.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.applet.constant.AppletConstant;
import com.ruoyi.applet.domain.User;
import com.ruoyi.applet.service.UserService;
import com.ruoyi.common.core.domain.AppletResult;
import com.ruoyi.common.json.JSON;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 用户小程序登录验证拦截器
 *
 * @author AlanLee
 * @date 2022-08-10
 */
@Component
public class AppletLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取访问令牌
        String accessToken = request.getHeader(AppletConstant.AUTHORIZATION_HEADER);
        // 如果访问令牌为空
        if (StringUtils.isBlank(accessToken)) {
            AppletResult appletResult = AppletResult.loginUserNotExist("访问令牌不能为空");
            ServletUtils.renderString(response, JSON.marshal(appletResult));
            return false;
        }
        // 根据accessToken查询用户信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("access_token", accessToken);
        User user = userService.getOne(userQueryWrapper);
        // 如果用户不存在
        if (!Optional.ofNullable(user).isPresent()) {
            AppletResult appletResult = AppletResult.loginUserNotExist("登录用户不存在");
            ServletUtils.renderString(response, JSON.marshal(appletResult));
            return false;
        }
        // 验证accessToken是否合法
        String verifyToken = new Md5Hash(user.getOpenid() + user.getSalt()).toHex();
        if (!accessToken.equals(verifyToken)) {
            AppletResult appletResult = AppletResult.loginUserNotExist("访问令牌非法");
            ServletUtils.renderString(response, JSON.marshal(appletResult));
            return false;
        }

        // 验证accessToken是否过期
        if (LocalDateTime.now().isAfter(user.getAccessTokenTime())) {
            AppletResult appletResult = AppletResult.loginUserNotExist("访问令牌已过期，请重新登录");
            ServletUtils.renderString(response, JSON.marshal(appletResult));
            return false;
        }

        return true;
    }

}