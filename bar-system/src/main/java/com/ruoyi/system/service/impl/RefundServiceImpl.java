package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Refund;
import com.ruoyi.system.mapper.RefundMapper;
import com.ruoyi.system.service.IRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 退款单Service业务层处理
 * 
 * @author AlanLee
 * @date 2022-09-22
 */
@Service
public class RefundServiceImpl implements IRefundService 
{
    @Autowired
    private RefundMapper refundMapper;

    /**
     * 查询退款单
     * 
     * @param id 退款单主键
     * @return 退款单
     */
    @Override
    public Refund selectRefundById(Long id)
    {
        return refundMapper.selectRefundById(id);
    }

    /**
     * 查询退款单列表
     * 
     * @param refund 退款单
     * @return 退款单
     */
    @Override
    public List<Refund> selectRefundList(Refund refund)
    {
        return refundMapper.selectRefundList(refund);
    }

    /**
     * 新增退款单
     * 
     * @param refund 退款单
     * @return 结果
     */
    @Override
    public int insertRefund(Refund refund)
    {
        refund.setCreateTime(DateUtils.getNowDate());
        return refundMapper.insertRefund(refund);
    }

    /**
     * 修改退款单
     * 
     * @param refund 退款单
     * @return 结果
     */
    @Override
    public int updateRefund(Refund refund)
    {
        refund.setUpdateTime(DateUtils.getNowDate());
        return refundMapper.updateRefund(refund);
    }

    /**
     * 批量删除退款单
     * 
     * @param ids 需要删除的退款单主键
     * @return 结果
     */
    @Override
    public int deleteRefundByIds(String ids)
    {
        return refundMapper.deleteRefundByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退款单信息
     * 
     * @param id 退款单主键
     * @return 结果
     */
    @Override
    public int deleteRefundById(Long id)
    {
        return refundMapper.deleteRefundById(id);
    }
}
