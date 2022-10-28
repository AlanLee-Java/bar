package com.ruoyi.applet.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.applet.domain.RefundReason;
import com.ruoyi.applet.service.RefundReasonService;
import com.ruoyi.common.core.domain.AppletResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-16
 */
@Api(tags = "退款原因相关接口")
@RestController
@RequestMapping("/refundReason")
public class RefundReasonController {

    @Autowired
    private RefundReasonService refundReasonService;

    @ApiOperation("根据商户ID获取退款原因")
    @GetMapping("/getByMerchantId")
    public AppletResult getByMerchantId(@RequestParam("merchantId") Long merchantId) {
        QueryWrapper<RefundReason> refundReasonQueryWrapper = new QueryWrapper<>();
        refundReasonQueryWrapper.eq("merchant_id", merchantId);
        refundReasonQueryWrapper.orderByAsc("sort");
        List<RefundReason> refundReasonList = refundReasonService.list(refundReasonQueryWrapper);
        return AppletResult.success(refundReasonList);
    }

}