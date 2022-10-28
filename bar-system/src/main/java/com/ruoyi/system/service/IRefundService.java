package com.ruoyi.system.service;

import com.ruoyi.system.domain.Refund;

import java.util.List;

/**
 * 退款单Service接口
 * 
 * @author AlanLee
 * @date 2022-09-22
 */
public interface IRefundService 
{
    /**
     * 查询退款单
     * 
     * @param id 退款单主键
     * @return 退款单
     */
    public Refund selectRefundById(Long id);

    /**
     * 查询退款单列表
     * 
     * @param refund 退款单
     * @return 退款单集合
     */
    public List<Refund> selectRefundList(Refund refund);

    /**
     * 新增退款单
     * 
     * @param refund 退款单
     * @return 结果
     */
    public int insertRefund(Refund refund);

    /**
     * 修改退款单
     * 
     * @param refund 退款单
     * @return 结果
     */
    public int updateRefund(Refund refund);

    /**
     * 批量删除退款单
     * 
     * @param ids 需要删除的退款单主键集合
     * @return 结果
     */
    public int deleteRefundByIds(String ids);

    /**
     * 删除退款单信息
     * 
     * @param id 退款单主键
     * @return 结果
     */
    public int deleteRefundById(Long id);
}
