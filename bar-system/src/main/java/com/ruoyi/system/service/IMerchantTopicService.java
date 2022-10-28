package com.ruoyi.system.service;

import com.ruoyi.system.domain.MerchantTopic;

import java.util.List;

/**
 * 商家话题Service接口
 * 
 * @author AlanLee
 * @date 2022-08-29
 */
public interface IMerchantTopicService 
{
    /**
     * 查询商家话题
     * 
     * @param id 商家话题主键
     * @return 商家话题
     */
    public MerchantTopic selectMerchantTopicById(Long id);

    /**
     * 查询商家话题列表
     * 
     * @param merchantTopic 商家话题
     * @return 商家话题集合
     */
    public List<MerchantTopic> selectMerchantTopicList(MerchantTopic merchantTopic);

    /**
     * 新增商家话题
     * 
     * @param merchantTopic 商家话题
     * @return 结果
     */
    public int insertMerchantTopic(MerchantTopic merchantTopic);

    /**
     * 修改商家话题
     * 
     * @param merchantTopic 商家话题
     * @return 结果
     */
    public int updateMerchantTopic(MerchantTopic merchantTopic);

    /**
     * 批量删除商家话题
     * 
     * @param ids 需要删除的商家话题主键集合
     * @return 结果
     */
    public int deleteMerchantTopicByIds(String ids);

    /**
     * 删除商家话题信息
     * 
     * @param id 商家话题主键
     * @return 结果
     */
    public int deleteMerchantTopicById(Long id);
}
