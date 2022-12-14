package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Goods;

import java.util.List;

/**
 * 商品Mapper接口
 * 
 * @author AlanLee
 * @date 2022-08-29
 */
public interface GoodsMapper 
{
    /**
     * 查询商品
     * 
     * @param id 商品主键
     * @return 商品
     */
    public Goods selectGoodsById(Long id);

    /**
     * 查询商品列表
     * 
     * @param goods 商品
     * @return 商品集合
     */
    public List<Goods> selectGoodsList(Goods goods);

    /**
     * 新增商品
     * 
     * @param goods 商品
     * @return 结果
     */
    public int insertGoods(Goods goods);

    /**
     * 修改商品
     * 
     * @param goods 商品
     * @return 结果
     */
    public int updateGoods(Goods goods);

    /**
     * 删除商品
     * 
     * @param id 商品主键
     * @return 结果
     */
    public int deleteGoodsById(Long id);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGoodsByIds(String[] ids);
}
