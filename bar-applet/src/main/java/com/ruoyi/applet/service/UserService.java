package com.ruoyi.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.applet.domain.User;
import com.ruoyi.applet.model.dto.AppletLoginDTO;
import com.ruoyi.applet.model.vo.AppletLoginVO;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-09
 */
public interface UserService extends IService<User> {

    /**
     * 用户小程序登录业务
     *
     * @param appletLoginDTO
     * @return
     */
    AppletLoginVO appletLogin(AppletLoginDTO appletLoginDTO) throws Exception;

    /**
     * 根据请求获取用户信息
     *
     * @param request
     * @return
     */
    User getUserByRequest(HttpServletRequest request);

}