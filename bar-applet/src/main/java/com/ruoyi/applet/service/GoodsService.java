package com.ruoyi.applet.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.applet.domain.Goods;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-13
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 减商品数量
     *
     * @param id
     * @param quantity
     */
    void subtractGoodsQuantity(Long id, Integer quantity);

}