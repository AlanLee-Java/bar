package com.ruoyi.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.applet.domain.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-13
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 减商品数量
     *
     * @param id
     * @param quantity
     */
    void subtractGoodsQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);

}