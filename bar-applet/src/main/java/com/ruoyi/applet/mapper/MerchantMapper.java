package com.ruoyi.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.applet.domain.Merchant;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 * 商家 Mapper 接口
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-11
 */
public interface MerchantMapper extends BaseMapper<Merchant> {

    /**
     * 增加余额
     *
     * @param id
     * @param amount
     */
    void increaseBalance(@Param("id") Long id, @Param("amount") BigDecimal amount);

    /**
     * 减少余额
     *
     * @param id
     * @param amount
     */
    void decreaseBalance(@Param("id") Long id, @Param("amount") BigDecimal amount);

}