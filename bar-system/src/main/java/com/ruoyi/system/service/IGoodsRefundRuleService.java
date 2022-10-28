package com.ruoyi.system.service;

import com.ruoyi.system.domain.GoodsRefundRule;

import java.util.List;

/**
 * 商品退款规则Service接口
 * 
 * @author AlanLee
 * @date 2022-08-29
 */
public interface IGoodsRefundRuleService 
{
    /**
     * 查询商品退款规则
     * 
     * @param id 商品退款规则主键
     * @return 商品退款规则
     */
    public GoodsRefundRule selectGoodsRefundRuleById(Long id);

    /**
     * 查询商品退款规则列表
     * 
     * @param goodsRefundRule 商品退款规则
     * @return 商品退款规则集合
     */
    public List<GoodsRefundRule> selectGoodsRefundRuleList(GoodsRefundRule goodsRefundRule);

    /**
     * 新增商品退款规则
     * 
     * @param goodsRefundRule 商品退款规则
     * @return 结果
     */
    public int insertGoodsRefundRule(GoodsRefundRule goodsRefundRule);

    /**
     * 修改商品退款规则
     * 
     * @param goodsRefundRule 商品退款规则
     * @return 结果
     */
    public int updateGoodsRefundRule(GoodsRefundRule goodsRefundRule);

    /**
     * 批量删除商品退款规则
     * 
     * @param ids 需要删除的商品退款规则主键集合
     * @return 结果
     */
    public int deleteGoodsRefundRuleByIds(String ids);

    /**
     * 删除商品退款规则信息
     * 
     * @param id 商品退款规则主键
     * @return 结果
     */
    public int deleteGoodsRefundRuleById(Long id);
}
