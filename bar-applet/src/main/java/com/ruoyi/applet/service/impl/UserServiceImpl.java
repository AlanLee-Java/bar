package com.ruoyi.applet.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.applet.config.AppletConfig;
import com.ruoyi.applet.constant.AppletConstant;
import com.ruoyi.applet.domain.User;
import com.ruoyi.applet.mapper.UserMapper;
import com.ruoyi.applet.model.dto.AppletLoginDTO;
import com.ruoyi.applet.model.vo.AppletLoginVO;
import com.ruoyi.applet.service.UserService;
import com.ruoyi.applet.support.api.AppletApi;
import com.ruoyi.applet.support.api.response.Code2SessionResponse;
import com.ruoyi.applet.support.utils.AppletUtils;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-09
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private AppletApi appletApi;

    @Autowired
    private AppletConfig appletConfig;

    /**
     * 用户小程序登录业务
     *
     * @param appletLoginDTO
     * @return
     */
    @Override
    public AppletLoginVO appletLogin(AppletLoginDTO appletLoginDTO) throws Exception {
        log.info("小程序登录请求参数：{}", JSON.toJSONString(appletLoginDTO));
        // code2session返回JSON数据
        JSONObject resultJson = appletApi.code2Session(appletLoginDTO.getCode());
        log.info("响应数据转换为JSON对象：{}", JSON.toJSONString(resultJson));
        // 解析响应数据
        Code2SessionResponse code2SessionResponse = JSON.toJavaObject(resultJson, Code2SessionResponse.class);
        log.info("解析响应数据为实体对象：{}", JSON.toJSONString(code2SessionResponse));
        if (Optional.ofNullable(code2SessionResponse.getErrcode()).isPresent()) {
            throw new ServiceException("登录失败：" + code2SessionResponse.getErrmsg());
        }
        // 解密用户信息
        JSONObject userInfoJSONObject = AppletUtils.decryptData(appletLoginDTO.getEncryptedData(), appletLoginDTO.getIv(), code2SessionResponse.getSession_key());
        if (!Optional.ofNullable(userInfoJSONObject).isPresent()) {
            throw new ServiceException("登录失败，用户信息为空");
        }
        // 微信昵称
        String nickName = userInfoJSONObject.getString("nickName");
        // 微信头像
        String avatarUrl = userInfoJSONObject.getString("avatarUrl");

        // 盐加密
        String salt = ShiroUtils.randomSalt();
        // 生成访问令牌
        String accessToken = new Md5Hash(code2SessionResponse.getOpenid() + salt).toHex();

        // 查询用户是否已存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("openid", code2SessionResponse.getOpenid());
        User user = this.getOne(userQueryWrapper);
        if (!Optional.ofNullable(user).isPresent()) {
            // 用户不存在新增用户
            user = new User();
            user.setOpenid(code2SessionResponse.getOpenid());
            user.setSessionKey(code2SessionResponse.getSession_key());
            user.setUnionid(code2SessionResponse.getUnionid());
            user.setWxName(nickName);
            user.setWxAvatar(avatarUrl);
            user.setSalt(salt);
            user.setAccessToken(accessToken);
            // 登录时间
            LocalDateTime loginTime = LocalDateTime.now();
            user.setAccessTokenTime(loginTime.plusDays(appletConfig.getSessionExpireDay()));
            user.setLoginTime(loginTime);
            user.setCreateTime(loginTime);
            user.setUpdateTime(loginTime);
            this.save(user);
        } else {
            user.setSessionKey(code2SessionResponse.getSession_key());
            user.setWxName(nickName);
            user.setWxAvatar(avatarUrl);
            user.setSalt(salt);
            user.setAccessToken(accessToken);
            // 登录时间
            LocalDateTime loginTime = LocalDateTime.now();
            user.setAccessTokenTime(loginTime.plusDays(appletConfig.getSessionExpireDay()));
            user.setLoginTime(loginTime);
            user.setUpdateTime(loginTime);
            this.updateById(user);
        }
        // 响应实体
        AppletLoginVO appletLoginVO = new AppletLoginVO();
        appletLoginVO.setUserId(user.getId());
        appletLoginVO.setWxName(user.getWxName());
        appletLoginVO.setWxAvatar(user.getWxAvatar());
        appletLoginVO.setAccessToken(user.getAccessToken());
        appletLoginVO.setAccessTokenTime(user.getAccessTokenTime());
        return appletLoginVO;
    }

    /**
     * 根据请求获取用户信息
     *
     * @param request
     * @return
     */
    @Override
    public User getUserByRequest(HttpServletRequest request) {
        // 获取访问令牌
        String accessToken = request.getHeader(AppletConstant.AUTHORIZATION_HEADER);
        if (StringUtils.isBlank(accessToken)) {
            return null;
        }
        // 根据accessToken查询用户信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        userQueryWrapper.eq("access_token", accessToken);
        return this.getOne(userQueryWrapper);
    }

}