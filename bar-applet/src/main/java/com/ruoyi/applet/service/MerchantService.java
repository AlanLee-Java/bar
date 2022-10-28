package com.ruoyi.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.applet.domain.Merchant;

import java.math.BigDecimal;

/**
 * <p>
 * 商家 服务类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-11
 */
public interface MerchantService extends IService<Merchant> {

    /**
     * 增加余额
     *
     * @param id
     * @param amount
     */
    void increaseBalance(Long id, BigDecimal amount);

    /**
     * 减少余额
     *
     * @param id
     * @param amount
     */
    void decreaseBalance(Long id, BigDecimal amount);

}