package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Goods;
import com.ruoyi.system.domain.GoodsRefundRule;
import com.ruoyi.system.mapper.GoodsMapper;
import com.ruoyi.system.mapper.GoodsRefundRuleMapper;
import com.ruoyi.system.service.IGoodsRefundRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品退款规则Service业务层处理
 *
 * @author AlanLee
 * @date 2022-08-29
 */
@Service
public class GoodsRefundRuleServiceImpl implements IGoodsRefundRuleService {

    @Autowired
    private GoodsRefundRuleMapper goodsRefundRuleMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查询商品退款规则
     *
     * @param id 商品退款规则主键
     * @return 商品退款规则
     */
    @Override
    public GoodsRefundRule selectGoodsRefundRuleById(Long id) {
        return goodsRefundRuleMapper.selectGoodsRefundRuleById(id);
    }

    /**
     * 查询商品退款规则列表
     *
     * @param goodsRefundRule 商品退款规则
     * @return 商品退款规则
     */
    @Override
    public List<GoodsRefundRule> selectGoodsRefundRuleList(GoodsRefundRule goodsRefundRule) {
        return goodsRefundRuleMapper.selectGoodsRefundRuleList(goodsRefundRule);
    }

    /**
     * 新增商品退款规则
     *
     * @param goodsRefundRule 商品退款规则
     * @return 结果
     */
    @Override
    public int insertGoodsRefundRule(GoodsRefundRule goodsRefundRule) {
        // 查询商品所属商户ID
        Goods goods = goodsMapper.selectGoodsById(goodsRefundRule.getMerchantId());
        goodsRefundRule.setMerchantId(goods.getMerchantId());
        goodsRefundRule.setCreateTime(DateUtils.getNowDate());
        goodsRefundRule.setUpdateTime(DateUtils.getNowDate());
        return goodsRefundRuleMapper.insertGoodsRefundRule(goodsRefundRule);
    }

    /**
     * 修改商品退款规则
     *
     * @param goodsRefundRule 商品退款规则
     * @return 结果
     */
    @Override
    public int updateGoodsRefundRule(GoodsRefundRule goodsRefundRule) {
        // 查询商品所属商户ID
        Goods goods = goodsMapper.selectGoodsById(goodsRefundRule.getMerchantId());
        goodsRefundRule.setMerchantId(goods.getMerchantId());
        goodsRefundRule.setUpdateTime(DateUtils.getNowDate());
        return goodsRefundRuleMapper.updateGoodsRefundRule(goodsRefundRule);
    }

    /**
     * 批量删除商品退款规则
     *
     * @param ids 需要删除的商品退款规则主键
     * @return 结果
     */
    @Override
    public int deleteGoodsRefundRuleByIds(String ids) {
        return goodsRefundRuleMapper.deleteGoodsRefundRuleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品退款规则信息
     *
     * @param id 商品退款规则主键
     * @return 结果
     */
    @Override
    public int deleteGoodsRefundRuleById(Long id) {
        return goodsRefundRuleMapper.deleteGoodsRefundRuleById(id);
    }

}