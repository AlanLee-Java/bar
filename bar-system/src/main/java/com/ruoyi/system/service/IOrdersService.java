package com.ruoyi.system.service;

import com.ruoyi.system.domain.Orders;
import com.ruoyi.system.model.dto.OrdersVerificationDTO;

import java.util.List;

/**
 * 订单Service接口
 *
 * @author AlanLee
 * @date 2022-09-01
 */
public interface IOrdersService {

    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    public Orders selectOrdersById(Long id);

    /**
     * 根据核销码查询订单
     *
     * @param verificationCode 核销码
     * @return 订单
     */
    public Orders selectByVerificationCode(Long verificationCode);

    /**
     * 查询订单列表
     *
     * @param orders 订单
     * @return 订单集合
     */
    public List<Orders> selectOrdersList(Orders orders);

    /**
     * 新增订单
     *
     * @param orders 订单
     * @return 结果
     */
    public int insertOrders(Orders orders);

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键集合
     * @return 结果
     */
    public int deleteOrdersByIds(String ids);

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    public int deleteOrdersById(Long id);

    /**
     * 核销
     *
     * @param ordersVerificationDTO
     */
    int verification(OrdersVerificationDTO ordersVerificationDTO);

}