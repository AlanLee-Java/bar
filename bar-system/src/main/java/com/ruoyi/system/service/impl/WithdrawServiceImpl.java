package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.RefundStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Merchant;
import com.ruoyi.system.domain.Withdraw;
import com.ruoyi.system.mapper.MerchantMapper;
import com.ruoyi.system.mapper.WithdrawMapper;
import com.ruoyi.system.service.IWithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 提现申请Service业务层处理
 *
 * @author AlanLee
 * @date 2022-10-11
 */
@Service
public class WithdrawServiceImpl implements IWithdrawService {
    @Autowired
    private WithdrawMapper withdrawMapper;
    @Autowired
    private MerchantMapper merchantMapper;

    /**
     * 查询提现申请
     *
     * @param id 提现申请主键
     * @return 提现申请
     */
    @Override
    public Withdraw selectWithdrawById(Long id) {
        return withdrawMapper.selectWithdrawById(id);
    }

    /**
     * 根据商家ID查询提现
     *
     * @param merchantId 商家ID
     * @return
     */
    @Override
    public Withdraw selectWithdrawByMerchantId(Long merchantId) {
        return withdrawMapper.selectWithdrawByMerchantId(merchantId);
    }

    /**
     * 查询提现申请列表
     *
     * @param withdraw 提现申请
     * @return 提现申请
     */
    @Override
    public List<Withdraw> selectWithdrawList(Withdraw withdraw) {
        return withdrawMapper.selectWithdrawList(withdraw);
    }

    /**
     * 新增提现申请
     *
     * @param withdraw 提现申请
     * @return 结果
     */
    @Override
    public int insertWithdraw(Withdraw withdraw) {
        withdraw.setCreateTime(DateUtils.getNowDate());
        withdraw.setUpdateTime(DateUtils.getNowDate());
        return withdrawMapper.insertWithdraw(withdraw);
    }

    /**
     * 修改提现申请
     *
     * @param withdraw 提现申请
     * @return 结果
     */
    @Override
    public int updateWithdraw(Withdraw withdraw) {
        withdraw.setUpdateTime(DateUtils.getNowDate());
        //同意提现
        if(withdraw.getStatus() == RefundStatus.SUCCESS.getStatus()){
        	Merchant m = merchantMapper.selectMerchantById(withdraw.getMerchantId());
            Merchant newMer = new Merchant();
            newMer.setId(m.getId());
            newMer.setBalance(m.getBalance().subtract(withdraw.getAmount()));
            merchantMapper.updateMerchant(newMer); //修改商户金额
        }
        return withdrawMapper.updateWithdraw(withdraw);
    }

    /**
     * 批量删除提现申请
     *
     * @param ids 需要删除的提现申请主键
     * @return 结果
     */
    @Override
    public int deleteWithdrawByIds(String ids) {
        return withdrawMapper.deleteWithdrawByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除提现申请信息
     *
     * @param id 提现申请主键
     * @return 结果
     */
    @Override
    public int deleteWithdrawById(Long id) {
        return withdrawMapper.deleteWithdrawById(id);
    }
}
