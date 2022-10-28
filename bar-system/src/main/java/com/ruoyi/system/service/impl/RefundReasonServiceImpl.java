package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.RefundReason;
import com.ruoyi.system.mapper.RefundReasonMapper;
import com.ruoyi.system.service.IRefundReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 退款原因Service业务层处理
 *
 * @author AlanLee
 * @date 2022-09-22
 */
@Service
public class RefundReasonServiceImpl implements IRefundReasonService {
    @Autowired
    private RefundReasonMapper refundReasonMapper;

    /**
     * 查询退款原因
     *
     * @param id 退款原因主键
     * @return 退款原因
     */
    @Override
    public RefundReason selectRefundReasonById(Long id) {
        return refundReasonMapper.selectRefundReasonById(id);
    }

    /**
     * 查询退款原因列表
     *
     * @param refundReason 退款原因
     * @return 退款原因
     */
    @Override
    public List<RefundReason> selectRefundReasonList(RefundReason refundReason) {
        return refundReasonMapper.selectRefundReasonList(refundReason);
    }

    /**
     * 新增退款原因
     *
     * @param refundReason 退款原因
     * @return 结果
     */
    @Override
    public int insertRefundReason(RefundReason refundReason) {
        refundReason.setCreateTime(DateUtils.getNowDate());
        refundReason.setUpdateTime(DateUtils.getNowDate());
        return refundReasonMapper.insertRefundReason(refundReason);
    }

    /**
     * 修改退款原因
     *
     * @param refundReason 退款原因
     * @return 结果
     */
    @Override
    public int updateRefundReason(RefundReason refundReason) {
        refundReason.setUpdateTime(DateUtils.getNowDate());
        return refundReasonMapper.updateRefundReason(refundReason);
    }

    /**
     * 批量删除退款原因
     *
     * @param ids 需要删除的退款原因主键
     * @return 结果
     */
    @Override
    public int deleteRefundReasonByIds(String ids) {
        return refundReasonMapper.deleteRefundReasonByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退款原因信息
     *
     * @param id 退款原因主键
     * @return 结果
     */
    @Override
    public int deleteRefundReasonById(Long id) {
        return refundReasonMapper.deleteRefundReasonById(id);
    }
}
