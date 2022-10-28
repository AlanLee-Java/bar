package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.MerchantTopic;
import com.ruoyi.system.mapper.MerchantTopicMapper;
import com.ruoyi.system.service.IMerchantTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商家话题Service业务层处理
 *
 * @author AlanLee
 * @date 2022-08-29
 */
@Service
public class MerchantTopicServiceImpl implements IMerchantTopicService {
    @Autowired
    private MerchantTopicMapper merchantTopicMapper;

    /**
     * 查询商家话题
     *
     * @param id 商家话题主键
     * @return 商家话题
     */
    @Override
    public MerchantTopic selectMerchantTopicById(Long id) {
        return merchantTopicMapper.selectMerchantTopicById(id);
    }

    /**
     * 查询商家话题列表
     *
     * @param merchantTopic 商家话题
     * @return 商家话题
     */
    @Override
    public List<MerchantTopic> selectMerchantTopicList(MerchantTopic merchantTopic) {
        return merchantTopicMapper.selectMerchantTopicList(merchantTopic);
    }

    /**
     * 新增商家话题
     *
     * @param merchantTopic 商家话题
     * @return 结果
     */
    @Override
    public int insertMerchantTopic(MerchantTopic merchantTopic) {
        merchantTopic.setCreateTime(DateUtils.getNowDate());
        merchantTopic.setUpdateTime(DateUtils.getNowDate());
        return merchantTopicMapper.insertMerchantTopic(merchantTopic);
    }

    /**
     * 修改商家话题
     *
     * @param merchantTopic 商家话题
     * @return 结果
     */
    @Override
    public int updateMerchantTopic(MerchantTopic merchantTopic) {
        merchantTopic.setUpdateTime(DateUtils.getNowDate());
        return merchantTopicMapper.updateMerchantTopic(merchantTopic);
    }

    /**
     * 批量删除商家话题
     *
     * @param ids 需要删除的商家话题主键
     * @return 结果
     */
    @Override
    public int deleteMerchantTopicByIds(String ids) {
        return merchantTopicMapper.deleteMerchantTopicByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商家话题信息
     *
     * @param id 商家话题主键
     * @return 结果
     */
    @Override
    public int deleteMerchantTopicById(Long id) {
        return merchantTopicMapper.deleteMerchantTopicById(id);
    }
}
