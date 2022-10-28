package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Merchant;

import java.util.List;
import java.util.Map;

/**
 * 商家管理Mapper接口
 *
 * @author AlanLee
 * @date 2022-08-11
 */
public interface MerchantMapper {

    /**
     * 根据商家管理员ID获取商家信息
     *
     * @param sysUserId
     * @return
     */
    Merchant getBySysUserId(Long sysUserId);
    
    /**
     * 根据商家管理员ID获取商家id
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
     * 根据商户id 查询订单统计 
     * @param id
     * @return orderMonthNum 月订单统计数
     * @return orderYearNum 年订单统计数
     * @return refundNum 月退款统计数
     */
    public Map<String,Object> selectMerchantSumOrder(Long id);

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
     * 删除商家管理
     *
     * @param id 商家管理主键
     * @return 结果
     */
    public int deleteMerchantById(Long id);

    /**
     * 批量删除商家管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMerchantByIds(String[] ids);
}
