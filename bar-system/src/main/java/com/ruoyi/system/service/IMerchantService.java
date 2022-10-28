package com.ruoyi.system.service;

import com.ruoyi.system.domain.Merchant;

import java.util.List;

/**
 * 商家管理Service接口
 *
 * @author AlanLee
 * @date 2022-08-11
 */
public interface IMerchantService {

    /**
     * 根据商家管理员ID获取商家信息
     *
     * @param sysUserId
     * @return
     */
    Merchant getBySysUserId(Long sysUserId);
    
    /**
     * 根据商家管理员ID获取商家信息
     *
     * @param sysUserId
     * @return
     */
    Long getIdByUserId(Long sysUserId);

    /**
     * 查询商家管理
     *
     * @param id 商家管理主键
     * @return 商家管理
     */
    public Merchant selectMerchantById(Long id);

    /**
     * 查询商家管理列表
     *
     * @param merchant 商家管理
     * @return 商家管理集合
     */
    public List<Merchant> selectMerchantList(Merchant merchant);

    /**
     * 新增商家管理
     *
     * @param merchant 商家管理
     * @return 结果
     */
    public int insertMerchant(Merchant merchant);

    /**
     * 修改商家管理
     *
     * @param merchant 商家管理
     * @return 结果
     */
    public int updateMerchant(Merchant merchant);

    /**
     * 批量删除商家管理
     *
     * @param ids 需要删除的商家管理主键集合
     * @return 结果
     */
    public int deleteMerchantByIds(String ids);

    /**
     * 删除商家管理信息
     *
     * @param id 商家管理主键
     * @return 结果
     */
    public int deleteMerchantById(Long id);


}
