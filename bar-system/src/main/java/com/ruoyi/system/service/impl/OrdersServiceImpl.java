package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.OrderStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.mapper.OrdersMapper;
import com.ruoyi.system.model.dto.OrdersVerificationDTO;
import com.ruoyi.system.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单Service业务层处理
 *
 * @author AlanLee
 * @date 2022-09-01
 */
@Service
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public Orders selectOrdersById(Long id) {
        return ordersMapper.selectOrdersById(id);
    }

    /**
     * 根据核销码查询订单
     *
     * @param verificationCode 核销码
     * @return
     */
    @Override
    public Orders selectByVerificationCode(Long verificationCode) {
        return ordersMapper.selectByVerificationCode(verificationCode);
    }

    /**
     * 查询订单列表
     *
     * @param orders 订单
     * @return 订单
     */
    @Override
    public List<Orders> selectOrdersList(Orders orders) {
        return ordersMapper.selectOrdersList(orders);
    }

    /**
     * 新增订单
     *
     * @param orders 订单
     * @return 结果
     */
    @Override
    public int insertOrders(Orders orders) {
        orders.setCreateTime(DateUtils.getNowDate());
        return ordersMapper.insertOrders(orders);
    }

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public int deleteOrdersByIds(String ids) {
        return ordersMapper.deleteOrdersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    @Override
    public int deleteOrdersById(Long id) {
        return ordersMapper.deleteOrdersById(id);
    }

    /**
     * 核销
     *
     * @param ordersVerificationDTO
     */
    @Override
    public int verification(OrdersVerificationDTO ordersVerificationDTO) {
        Orders orders = new Orders();
        orders.setVerificationCode(ordersVerificationDTO.getVerificationCode());
        orders.setStatus(OrderStatus.DONE.getStatus());
        orders.setConsumeTime(DateUtils.getNowDate());
        return ordersMapper.verification(orders);
    }

}