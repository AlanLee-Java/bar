package com.ruoyi.applet.web;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.applet.domain.*;
import com.ruoyi.applet.model.dto.ActivityQueryDTO;
import com.ruoyi.applet.model.vo.ActivityQueryVO;
import com.ruoyi.applet.service.*;
import com.ruoyi.common.core.domain.AppletResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 活动 前端控制器
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-17
 */
@Api(tags = "活动相关接口")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ActivityGoodsJoinService activityGoodsJoinService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ActivityGuestsService activityGuestsService;

    /**
     * 分页查询活动
     *
     * @param page
     * @return
     */
    @ApiOperation("分页查询活动")
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页大小", dataType = "int", paramType = "query", defaultValue = "5")
    })
    public AppletResult page(@ApiIgnore Page<Activity> page) {
        QueryWrapper<Activity> activityQueryWrapper = new QueryWrapper<>();
        Page<Activity> activityPage = activityService.page(page, activityQueryWrapper);
        for (Activity activity : activityPage.getRecords()) {
            // 商家信息
            Merchant merchant = merchantService.getById(activity.getMerchantId());
            if (Optional.ofNullable(merchant).isPresent()) {
                activity.setMechantName(merchant.getName());
            }
            // 获取活动商品信息
            QueryWrapper<ActivityGoodsJoin> activityGoodsJoinQueryWrapper = new QueryWrapper<>();
            activityGoodsJoinQueryWrapper.eq("activity_id", activity.getId());
            List<ActivityGoodsJoin> activityGoodsJoins = activityGoodsJoinService.list(activityGoodsJoinQueryWrapper);
            // 商品列表
            List<Goods> goodsList = new ArrayList<>();
            if (CollUtil.isNotEmpty(activityGoodsJoins)) {
                for (ActivityGoodsJoin activityGoodsJoin : activityGoodsJoins) {
                    // 查询商品信息
                    Goods goods = goodsService.getById(activityGoodsJoin.getGoodsId());
                    goodsList.add(goods);
                }
            }
            if (CollUtil.isNotEmpty(goodsList)) {
                List<Goods> sortGoodsList = ListUtil.sortByProperty(goodsList, "price");
                // 最低价
                BigDecimal minimumPrice = sortGoodsList.get(0).getPrice();
                activity.setMinimumPrice(minimumPrice);
                // 最高价
                BigDecimal maximumPrice = sortGoodsList.get(sortGoodsList.size() - 1).getPrice();
                activity.setMaximumPrice(maximumPrice);
            }
        }
        return AppletResult.success(activityPage);
    }

    /**
     * 查询活动详情
     *
     * @param activityId
     * @return
     */
    @ApiOperation("查询活动详情")
    @GetMapping("/detail")
    public AppletResult detail(@RequestParam("activityId") Long activityId) {
        Activity activity = activityService.getById(activityId);
        // 商家信息
        Merchant merchant = merchantService.getById(activity.getMerchantId());
        if (Optional.ofNullable(merchant).isPresent()) {
            activity.setMechantName(merchant.getName());
        }
        // 获取活动商品信息
        List<Goods> goodsList = new ArrayList<>();

        QueryWrapper<ActivityGoodsJoin> activityGoodsJoinQueryWrapper = new QueryWrapper<>();
        activityGoodsJoinQueryWrapper.eq("activity_id", activity.getId());
        List<ActivityGoodsJoin> activityGoodsJoins = activityGoodsJoinService.list(activityGoodsJoinQueryWrapper);
        if (CollUtil.isNotEmpty(activityGoodsJoins)) {
            for (ActivityGoodsJoin activityGoodsJoin : activityGoodsJoins) {
                // 查询商品信息
                Goods goods = goodsService.getById(activityGoodsJoin.getGoodsId());
                goodsList.add(goods);
            }
        }
        if (CollUtil.isNotEmpty(goodsList)) {
            List<Goods> sortGoodsList = ListUtil.sortByProperty(goodsList, "price");
            // 最低价
            BigDecimal minimumPrice = sortGoodsList.get(0).getPrice();
            activity.setMinimumPrice(minimumPrice);
            // 最高价
            BigDecimal maximumPrice = sortGoodsList.get(sortGoodsList.size() - 1).getPrice();
            activity.setMaximumPrice(maximumPrice);
            // 活动商品列表
            activity.setGoodsList(goodsList);
        }
        // 获取活动嘉宾列表
        QueryWrapper<ActivityGuests> activityGuestsQueryWrapper = new QueryWrapper<>();
        activityGuestsQueryWrapper.eq("activity_id", activity.getId());
        List<ActivityGuests> activityGuestsList = activityGuestsService.list(activityGuestsQueryWrapper);
        if (CollUtil.isNotEmpty(activityGuestsList)) {
            activity.setActivityGuestsList(activityGuestsList);
        }

        return AppletResult.success(activity);
    }

    /**
     * 查询活动日历
     *
     * @param page
     * @return
     */
    @ApiOperation("查询活动日历接口")
    @GetMapping("/queryActivity")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页大小", dataType = "int", paramType = "query", defaultValue = "5")
    })
    public AppletResult queryActivity(ActivityQueryDTO activityQueryDTO, @ApiIgnore Page<ActivityQueryVO> page) {
        Page<ActivityQueryVO> activityQueryVOPage = activityService.queryActivity(page, activityQueryDTO);
        return AppletResult.success(activityQueryVOPage);
    }

}