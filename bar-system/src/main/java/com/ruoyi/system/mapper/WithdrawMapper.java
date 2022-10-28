package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Withdraw;

import java.util.List;

/**
 * 提现申请Mapper接口
 * 
 * @author AlanLee
 * @date 2022-10-11
 */
public interface WithdrawMapper 
{
    /**
     * 查询提现申请
     * 
     * @param id 提现申请主键
     * @return 提现申请
     */
    public Withdraw selectWithdrawById(Long id);

    /**
     * 根据商家ID查询提现
     *
     * @param merchantId 提现申请主键
     * @return 提现申请
     */
    public Withdraw selectWithdrawByMerchantId(Long merchantId);

    /**
     * 查询提现申请列表
     * 
     * @param withdraw 提现申请
     * @return 提现申请集合
     */
    public List<Withdraw> selectWithdrawList(Withdraw withdraw);

    /**
     * 新增提现申请
     * 
     * @param withdraw 提现申请
     * @return 结果
     */
    public int insertWithdraw(Withdraw withdraw);

    /**
     * 修改提现申请
     * 
     * @param withdraw 提现申请
     * @return 结果
     */
    public int updateWithdraw(Withdraw withdraw);

    /**
     * 删除提现申请
     * 
     * @param id 提现申请主键
     * @return 结果
     */
    public int deleteWithdrawById(Long id);

    /**
     * 批量删除提现申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWithdrawByIds(String[] ids);
}
