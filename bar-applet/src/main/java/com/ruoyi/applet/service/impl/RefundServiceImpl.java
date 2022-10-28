package com.ruoyi.applet.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.applet.domain.ActivityRefundRule;
import com.ruoyi.applet.domain.GoodsRefundRule;
import com.ruoyi.applet.domain.Orders;
import com.ruoyi.applet.domain.Refund;
import com.ruoyi.applet.mapper.RefundMapper;
import com.ruoyi.applet.service.ActivityRefundRuleService;
import com.ruoyi.applet.service.GoodsRefundRuleService;
import com.ruoyi.applet.service.OrdersService;
import com.ruoyi.applet.service.RefundService;
import com.ruoyi.applet.support.utils.CalculateUtils;
import com.ruoyi.applet.support.wxpay.AppletWXPayConfig;
import com.ruoyi.applet.support.wxpay.WXPay;
import com.ruoyi.applet.support.wxpay.WXPayConstants;
import com.ruoyi.common.enums.GoodsType;
import com.ruoyi.common.enums.RefundStatus;
import com.ruoyi.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 退款 服务实现类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-16
 */
@Service
@Slf4j
public class RefundServiceImpl extends ServiceImpl<RefundMapper, Refund> implements RefundService {

    @Autowired
    private ActivityRefundRuleService activityRefundRuleService;

    @Autowired
    private GoodsRefundRuleService goodsRefundRuleService;

    @Autowired
    private OrdersService ordersService;

    /**
     * 获取订单退款金额
     *
     * @param orders
     * @return
     */
    @Override
    public BigDecimal refundAmount(Orders orders) {
        // 获取退款规则计算退款金额
        LocalDateTime now = LocalDateTime.now();
        // 初始化退款金额
        BigDecimal refundAmount = BigDecimal.ZERO;
        // 如果是活动票获取活动退款规则
        if (orders.getGoodsType() == GoodsType.ACTIVITY_TICKET.getType()) {
            QueryWrapper<ActivityRefundRule> activityRefundRuleQueryWrapper = new QueryWrapper<>();
            activityRefundRuleQueryWrapper.eq("activity_id", orders.getActivityId());
            activityRefundRuleQueryWrapper.orderByAsc("refund_time");
            List<ActivityRefundRule> activityRefundRules = activityRefundRuleService.list(activityRefundRuleQueryWrapper);
            if (CollUtil.isEmpty(activityRefundRules)) {
                throw new ServiceException("该活动没有退款规则，不支持退款");
            }
            for (ActivityRefundRule activityRefundRule : activityRefundRules) {
                // 退款时间
                LocalDateTime refundTime = activityRefundRule.getRefundTime();
                // 条件类型：1前，不包括规则时间，2后，包括规则时间
                Integer conditionType = activityRefundRule.getConditionType();
                // 是否允许退款：0不允许，1允许
                Integer isAllow = activityRefundRule.getIsAllow();
                // 时间前，不包括规则时间
                if (conditionType == 1) {
                    if (now.isBefore(refundTime)) {
                        // 允许退款
                        if (isAllow == 1) {
                            // 计算退款金额
                            refundAmount = CalculateUtils.refundAmount(orders.getOrderAmount(), activityRefundRule.getRefundRate());
                        } else {
                            throw new ServiceException("该时间点不允许退款，无法退款");
                        }
                    } else {
                        // 时间不符合该退款规则，继续下一个
                        continue;
                    }
                } else {
                    // 时间后，包括规则时间
                    if (now.isEqual(refundTime) || now.isAfter(refundTime)) {
                        // 允许退款
                        if (isAllow == 1) {
                            // 计算退款金额
                            refundAmount = CalculateUtils.refundAmount(orders.getOrderAmount(), activityRefundRule.getRefundRate());
                        } else {
                            throw new ServiceException("该时间点不允许退款，无法退款");
                        }
                    } else {
                        // 时间不符合该退款规则，继续下一个
                        continue;
                    }
                }
            }
        } else {
            // 获取商品退款规则
            QueryWrapper<GoodsRefundRule> goodsRefundRuleQueryWrapper = new QueryWrapper<>();
            goodsRefundRuleQueryWrapper.eq("goods_id", orders.getGoodsId());
            goodsRefundRuleQueryWrapper.orderByAsc("refund_time");
            List<GoodsRefundRule> goodsRefundRules = goodsRefundRuleService.list(goodsRefundRuleQueryWrapper);
            if (CollUtil.isEmpty(goodsRefundRules)) {
                throw new ServiceException("该商品没有退款规则，不支持退款");
            }
            for (GoodsRefundRule goodsRefundRule : goodsRefundRules) {
                // 退款时间
                LocalDateTime refundTime = goodsRefundRule.getRefundTime();
                // 条件类型：1前，不包括规则时间，2后，包括规则时间
                Integer conditionType = goodsRefundRule.getConditionType();
                // 是否允许退款：0不允许，1允许
                Integer isAllow = goodsRefundRule.getIsAllow();
                // 时间前，不包括规则时间
                if (conditionType == 1) {
                    if (now.isBefore(refundTime)) {
                        // 允许退款
                        if (isAllow == 1) {
                            // 计算退款金额
                            refundAmount = CalculateUtils.refundAmount(orders.getOrderAmount(), goodsRefundRule.getRefundRate());
                        } else {
                            throw new ServiceException("该时间点不允许退款，无法退款");
                        }
                    } else {
                        // 时间不符合该退款规则，继续下一个
                        continue;
                    }
                } else {
                    // 时间后，包括规则时间
                    if (now.isEqual(refundTime) || now.isAfter(refundTime)) {
                        // 允许退款
                        if (isAllow == 1) {
                            // 计算退款金额
                            refundAmount = CalculateUtils.refundAmount(orders.getOrderAmount(), goodsRefundRule.getRefundRate());
                        } else {
                            throw new ServiceException("该时间点不允许退款，无法退款");
                        }
                    } else {
                        // 时间不符合该退款规则，继续下一个
                        continue;
                    }
                }
            }
        }
        // 退款金额小于等于0不符合退订规则，无法退款
        if (refundAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ServiceException("退款金额小于等于0，无法退款");
        }

        // 退款金额不能大于订单金额
        if (refundAmount.compareTo(orders.getOrderAmount()) > 0) {
            throw new ServiceException("退款金额大于订单金额，无法退款");
        }

        return refundAmount;
    }

    /**
     * 申请退款异步处理
     *
     * @param refund
     */
    @Override
    @Async
    public void applyRefund(Refund refund, String notifyUrl) throws Exception {
        // 获取订单
        Orders orders = ordersService.getById(refund.getOrderId());
        if (!Optional.ofNullable(orders).isPresent()) {
            throw new ServiceException("订单不存在");
        }

        AppletWXPayConfig appletWXPayConfig = new AppletWXPayConfig();
        WXPay wxPay = new WXPay(appletWXPayConfig, notifyUrl, false, false);

        Map<String, String> params = new HashMap<>();
        // 商户订单号
        params.put("out_trade_no", orders.getOrderNo().toString());
        // 商户退款单号
        params.put("out_refund_no", refund.getRefundNo().toString());
        // 标价金额，订单总金额，单位为分
        int total_fee = CalculateUtils.yuanToFen(orders.getOrderAmount());
        params.put("total_fee", String.valueOf(total_fee));
        // 退款金额
        int refund_fee = CalculateUtils.yuanToFen(refund.getRefundAmount());
        params.put("refund_fee", String.valueOf(refund_fee));
        // 回调地址
        params.put("notify_url", notifyUrl);

        // 调用申请退款接口
        Map<String, String> results = wxPay.refund(params);
        log.info("调用微信申请退款接口，响应数据：{}", JSON.toJSONString(results));
        // 获取通信标识
        String return_code = results.get("return_code");
        if (!return_code.equals(WXPayConstants.SUCCESS)) {
            throw new ServiceException("申请退款通信失败");
        }
        // 校验业务响应
        String result_code = results.get("result_code");
        if (!result_code.equals(WXPayConstants.SUCCESS)) {
            throw new ServiceException("申请退款业务失败");
        }
        // 获取微信退款单号
        String refund_id = results.get("refund_id");
        // 修改退款单的状态和信息
        UpdateWrapper<Refund> refundUpdateWrapper = new UpdateWrapper<>();
        refundUpdateWrapper.set("trade_no", refund_id);
        refundUpdateWrapper.set("status", RefundStatus.REFUND.getStatus());
        refundUpdateWrapper.set("update_time", LocalDateTime.now());
        refundUpdateWrapper.eq("id", refund.getId());
        refundUpdateWrapper.eq("status", RefundStatus.SUBMIT.getStatus());
        this.update(refundUpdateWrapper);
    }

}