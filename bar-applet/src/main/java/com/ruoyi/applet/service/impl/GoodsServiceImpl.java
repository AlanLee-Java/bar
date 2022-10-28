package com.ruoyi.applet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.applet.domain.Goods;
import com.ruoyi.applet.mapper.GoodsMapper;
import com.ruoyi.applet.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-13
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    /**
     * 减商品数量
     *
     * @param id
     * @param quantity
     */
    @Override
    public void subtractGoodsQuantity(Long id, Integer quantity) {
        this.baseMapper.subtractGoodsQuantity(id, quantity);
    }

}