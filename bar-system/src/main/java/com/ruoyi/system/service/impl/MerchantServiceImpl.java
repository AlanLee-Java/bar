package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.mapper.MerchantMapper;
import com.ruoyi.system.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商家管理Service业务层处理
 *
 * @author AlanLee
 * @date 2022-08-11
 */
@Service
public class MerchantServiceImpl implements IMerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public Merchant getBySysUserId(Long sysUserId) {
        return merchantMapper.getBySysUserId(sysUserId);
    }
    
    @Override
    public Long getIdByUserId(Long sysUserId) {
        return merchantMapper.getIdByUserId(sysUserId);
    }

    /**
     * 查询商家管理
     *
     * @param id 商家管理主键
     * @return 商家管理
     */
    @Override
    public Merchant selectMerchantById(Long id) {
        return merchantMapper.selectMerchantById(id);
    }

    /**
     * 查询商家管理列表
     *
     * @param merchant 商家管理
     * @return 商家管理
     */
    @Override
    public List<Merchant> selectMerchantList(Merchant merchant) {
        return merchantMapper.selectMerchantList(merchant);
    }

    /**
     * 新增商家管理
     *
     * @param merchant 商家管理
     * @return 结果
     */
    @Override
    public int insertMerchant(Merchant merchant) {
        merchant.setCreateTime(DateUtils.getNowDate());
        merchant.setUpdateTime(DateUtils.getNowDate());
        return merchantMapper.insertMerchant(merchant);
    }

    /**
     * 修改商家管理
     *
     * @param merchant 商家管理
     * @return 结果
     */
    @Override
    public int updateMerchant(Merchant merchant) {
        merchant.setUpdateTime(DateUtils.getNowDate());
        return merchantMapper.updateMerchant(merchant);
    }

    /**
     * 批量删除商家管理
     *
     * @param ids 需要删除的商家管理主键
     * @return 结果
     */
    @Override
    public int deleteMerchantByIds(String ids) {
        return merchantMapper.deleteMerchantByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商家管理信息
     *
     * @param id 商家管理主键
     * @return 结果
     */
    @Override
    public int deleteMerchantById(Long id) {
        return merchantMapper.deleteMerchantById(id);
    }

}