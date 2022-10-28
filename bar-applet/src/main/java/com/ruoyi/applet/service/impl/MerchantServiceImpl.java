package com.ruoyi.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.applet.domain.Merchant;
import com.ruoyi.applet.mapper.MerchantMapper;
import com.ruoyi.applet.service.MerchantService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 商家 服务实现类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-11
 */
@Service
public class MerchantServiceImpl extends ServiceImpl<MerchantMapper, Merchant> implements MerchantService {

    /**
     * 增加商家余额
     *
     * @param id
     * @param amount
     */
    @Override
    public void increaseBalance(Long id, BigDecimal amount) {
        this.baseMapper.increaseBalance(id, amount);
    }

    /**
     * 减少余额
     *
     * @param id
     * @param amount
     */
    @Override
    public void decreaseBalance(Long id, BigDecimal amount) {
        this.baseMapper.decreaseBalance(id, amount);
    }

}