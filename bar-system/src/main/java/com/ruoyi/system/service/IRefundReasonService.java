package com.ruoyi.system.service;

import com.ruoyi.system.domain.RefundReason;

import java.util.List;

/**
 * 退款原因Service接口
 * 
 * @author AlanLee
 * @date 2022-09-22
 */
public interface IRefundReasonService 
{
    /**
     * 查询退款原因
     * 
     * @param id 退款原因主键
     * @return 退款原因
     */
    public RefundReason selectRefundReasonById(Long id);

    /**
     * 查询退款原因列表
     * 
     * @param refundReason 退款原因
     * @return 退款原因集合
     */
    public List<RefundReason> selectRefundReasonList(RefundReason refundReason);

    /**
     * 新增退款原因
     * 
     * @param refundReason 退款原因
     * @return 结果
     */
    public int insertRefundReason(RefundReason refundReason);

    /**
     * 修改退款原因
     * 
     * @param refundReason 退款原因
     * @return 结果
     */
    public int updateRefundReason(RefundReason refundReason);

    /**
     * 批量删除退款原因
     * 
     * @param ids 需要删除的退款原因主键集合
     * @return 结果
     */
    public int deleteRefundReasonByIds(String ids);

    /**
     * 删除退款原因信息
     * 
     * @param id 退款原因主键
     * @return 结果
     */
    public int deleteRefundReasonById(Long id);
}
