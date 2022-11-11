package com.ruoyi.applet.web;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.applet.domain.*;
import com.ruoyi.applet.model.dto.MerchantQueryDTO;
import com.ruoyi.applet.service.*;
import com.ruoyi.common.core.domain.AppletResult;
import com.ruoyi.common.enums.GoodsStatus;
import com.ruoyi.common.enums.GoodsType;
import com.ruoyi.common.enums.MerchantKey;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
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
 * 商家 前端控制器
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-11
 */
@Api(tags = "商家相关接口")
@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantTopicService merchantTopicService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SetMealService setMealService;

    @Autowired
    private SetMealItemService setMealItemService;

    @Autowired
    private ActivityGoodsJoinService activityGoodsJoinService;

    /**
     * 分页查询商家信息
     *
     * @param merchantQueryDTO
     * @param page
     * @return
     */
    @ApiOperation("分页查询商家信息")
    @GetMapping("/page")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页大小", dataType = "int", paramType = "query", defaultValue = "10")
    })
    public AppletResult page(MerchantQueryDTO merchantQueryDTO, @ApiIgnore Page<Merchant> page) {
        QueryWrapper<Merchant> merchantQueryWrapper = new QueryWrapper<>();
        if (Optional.ofNullable(merchantQueryDTO).isPresent()) {
            if (StringUtils.isNotBlank(merchantQueryDTO.getCity())) {
                merchantQueryWrapper.eq("city", merchantQueryDTO.getCity());
            }
            if (StringUtils.isNotBlank(merchantQueryDTO.getName())) {
               
				if (MerchantKey.JIUDIAN.getName().equals(merchantQueryDTO.getName())) {
					merchantQueryWrapper.and(wrapper -> wrapper.like("name", merchantQueryDTO.getName()).or()
							.eq("type_key", MerchantKey.JIUDIAN.getType()));
				} else if (MerchantKey.YEDIAN.getName().equals(merchantQueryDTO.getName())) {

					merchantQueryWrapper.and(wrapper -> wrapper.like("name", merchantQueryDTO.getName()).or()
							.eq("type_key", MerchantKey.YEDIAN.getType()));
				} else if (MerchantKey.LIVE_HOUSE.getName().toUpperCase().equals(merchantQueryDTO.getName().toUpperCase())) {
					merchantQueryWrapper.and(wrapper -> wrapper.like("name", merchantQueryDTO.getName()).or()
							.eq("type_key", MerchantKey.LIVE_HOUSE.getType()));
				} else {
					merchantQueryWrapper.like("name", merchantQueryDTO.getName());
				}
            }
        }
        Page<Merchant> merchantPage = merchantService.page(page, merchantQueryWrapper);
        for (Merchant merchant : merchantPage.getRecords()) {
        	//显示标签
			if (merchant.getTypeKey() == MerchantKey.JIUDIAN.getType()) {
				merchant.setTypeKeyName(MerchantKey.JIUDIAN.getDesc());
			} else if (merchant.getTypeKey() == MerchantKey.YEDIAN.getType()) {
				merchant.setTypeKeyName(MerchantKey.YEDIAN.getDesc());
			} else if (merchant.getTypeKey() == MerchantKey.LIVE_HOUSE.getType()) {
				merchant.setTypeKeyName(MerchantKey.LIVE_HOUSE.getDesc());
			}
			
            // 查询商家话题
            QueryWrapper<MerchantTopic> merchantTopicQueryWrapper = new QueryWrapper<>();
            merchantTopicQueryWrapper.eq("merchant_id", merchant.getId());
            merchantTopicQueryWrapper.orderByAsc("sort");
            List<MerchantTopic> merchantTopics = merchantTopicService.list(merchantTopicQueryWrapper);
            if (CollUtil.isNotEmpty(merchantTopics)) {
                merchant.setMerchantTopics(merchantTopics);
            }
        }
        return AppletResult.success(merchantPage);
    }

    /**
     * 查询商家详情
     *
     * @param merchantId
     * @return
     */
    @ApiOperation("查询商家详情")
    @GetMapping("/detail")
    public AppletResult detail(@RequestParam("merchantId") Long merchantId) {
        Merchant merchant = merchantService.getById(merchantId);
        if (!Optional.ofNullable(merchant).isPresent()) {
            throw new ServiceException("商家不存在");
        }
		// 显示标签
		if (merchant.getTypeKey() == MerchantKey.JIUDIAN.getType()) {
			merchant.setTypeKeyName(MerchantKey.JIUDIAN.getDesc());
		} else if (merchant.getTypeKey() == MerchantKey.YEDIAN.getType()) {
			merchant.setTypeKeyName(MerchantKey.YEDIAN.getDesc());
		} else if (merchant.getTypeKey() == MerchantKey.LIVE_HOUSE.getType()) {
			merchant.setTypeKeyName(MerchantKey.LIVE_HOUSE.getDesc());
		}
        // 查询商家话题
        QueryWrapper<MerchantTopic> merchantTopicQueryWrapper = new QueryWrapper<>();
        merchantTopicQueryWrapper.eq("merchant_id", merchant.getId());
        merchantTopicQueryWrapper.orderByAsc("sort");
        List<MerchantTopic> merchantTopics = merchantTopicService.list(merchantTopicQueryWrapper);
        if (CollUtil.isNotEmpty(merchantTopics)) {
            merchant.setMerchantTopics(merchantTopics);
        }
        // 查询商家散票商品
        QueryWrapper<Goods> scatteredTicketQueryWrapper = new QueryWrapper<>();
        scatteredTicketQueryWrapper.eq("merchant_id", merchant.getId());
        scatteredTicketQueryWrapper.eq("type", GoodsType.SCATTERED_TICKET.getType());
        scatteredTicketQueryWrapper.eq("status", GoodsStatus.SOLD_ON.getStatus());
        List<Goods> scatteredTickets = goodsService.list(scatteredTicketQueryWrapper);
        if (CollUtil.isNotEmpty(scatteredTickets)) {
            merchant.setScatteredTickets(scatteredTickets);
        }
        // 查询商家套餐商品
        QueryWrapper<Goods> packageQueryWrapper = new QueryWrapper<>();
        packageQueryWrapper.eq("merchant_id", merchant.getId());
        packageQueryWrapper.eq("type", GoodsType.PACKAGE.getType());
        packageQueryWrapper.eq("status", GoodsStatus.SOLD_ON.getStatus());
        List<Goods> packages = goodsService.list(packageQueryWrapper);
        if (CollUtil.isNotEmpty(packages)) {
            merchant.setPackages(packages);
        }
        // 查询商家活动列表
        QueryWrapper<Activity> activityQueryWrapper = new QueryWrapper<>();
        activityQueryWrapper.eq("merchant_id", merchant.getId());
        List<Activity> activities = activityService.list(activityQueryWrapper);
        if (CollUtil.isNotEmpty(activities)) {
            // 遍历活动列表
            for (Activity activity : activities) {
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
            merchant.setActivities(activities);
        }

        return AppletResult.success(merchant);
    }

    /**
     * 查询商家套餐列表
     *
     * @param merchantId
     * @return
     */
    @ApiOperation("查询商家套餐列表接口")
    @GetMapping("/packageList")
    public AppletResult packageList(@RequestParam("merchantId") Long merchantId) {
        // 获取商家信息
        Merchant merchant = merchantService.getById(merchantId);
		// 显示标签
		if (merchant.getTypeKey() == MerchantKey.JIUDIAN.getType()) {
			merchant.setTypeKeyName(MerchantKey.JIUDIAN.getDesc());
		} else if (merchant.getTypeKey() == MerchantKey.YEDIAN.getType()) {
			merchant.setTypeKeyName(MerchantKey.YEDIAN.getDesc());
		} else if (merchant.getTypeKey() == MerchantKey.LIVE_HOUSE.getType()) {
			merchant.setTypeKeyName(MerchantKey.LIVE_HOUSE.getDesc());
		}
        if (!Optional.ofNullable(merchant).isPresent()) {
            throw new ServiceException("商家不存在");
        }
        // 查询商家话题
        QueryWrapper<MerchantTopic> merchantTopicQueryWrapper = new QueryWrapper<>();
        merchantTopicQueryWrapper.eq("merchant_id", merchant.getId());
        merchantTopicQueryWrapper.orderByAsc("sort");
        List<MerchantTopic> merchantTopics = merchantTopicService.list(merchantTopicQueryWrapper);
        if (CollUtil.isNotEmpty(merchantTopics)) {
            merchant.setMerchantTopics(merchantTopics);
        }
        // 查询商家套餐商品
        QueryWrapper<Goods> packageQueryWrapper = new QueryWrapper<>();
        packageQueryWrapper.eq("merchant_id", merchant.getId());
        packageQueryWrapper.eq("type", GoodsType.PACKAGE.getType());
        packageQueryWrapper.eq("status", GoodsStatus.SOLD_ON.getStatus());
        List<Goods> packages = goodsService.list(packageQueryWrapper);
        if (CollUtil.isNotEmpty(packages)) {
            for (Goods aPackage : packages) {
                // 获取套餐信息
                QueryWrapper<SetMeal> setMealQueryWrapper = new QueryWrapper<>();
                setMealQueryWrapper.eq("goods_id", aPackage.getId());
                SetMeal setMeal = setMealService.getOne(setMealQueryWrapper);
                if (Optional.ofNullable(setMeal).isPresent()) {
                    aPackage.setPicture(setMeal.getPicture());
                    aPackage.setNotice(setMeal.getNotice());
                    // 获取套餐项目
                    QueryWrapper<SetMealItem> setMealItemQueryWrapper = new QueryWrapper<>();
                    setMealItemQueryWrapper.eq("set_meal_id", setMeal.getId());
                    List<SetMealItem> setMealItems = setMealItemService.list(setMealItemQueryWrapper);
                    if (CollUtil.isNotEmpty(setMealItems)) {
                        aPackage.setSetMealItems(setMealItems);
                    }
                }
            }
            merchant.setPackages(packages);
        }

        return AppletResult.success(merchant);
    }

    /**
     * 查询商家套餐详情
     *
     * @param merchantId
     * @return
     */
    @ApiOperation("查询商家套餐详情接口")
    @GetMapping("/packageDetail")
    public AppletResult packageDetail(@RequestParam("merchantId") Long merchantId, @RequestParam("goodsId") Long goodsId) {
        // 获取商家信息
        Merchant merchant = merchantService.getById(merchantId);
		// 显示标签
		if (merchant.getTypeKey() == MerchantKey.JIUDIAN.getType()) {
			merchant.setTypeKeyName(MerchantKey.JIUDIAN.getDesc());
		} else if (merchant.getTypeKey() == MerchantKey.YEDIAN.getType()) {
			merchant.setTypeKeyName(MerchantKey.YEDIAN.getDesc());
		} else if (merchant.getTypeKey() == MerchantKey.LIVE_HOUSE.getType()) {
			merchant.setTypeKeyName(MerchantKey.LIVE_HOUSE.getDesc());
		}
        if (!Optional.ofNullable(merchant).isPresent()) {
            throw new ServiceException("商家不存在");
        }
        // 查询商家话题
        QueryWrapper<MerchantTopic> merchantTopicQueryWrapper = new QueryWrapper<>();
        merchantTopicQueryWrapper.eq("merchant_id", merchant.getId());
        merchantTopicQueryWrapper.orderByAsc("sort");
        List<MerchantTopic> merchantTopics = merchantTopicService.list(merchantTopicQueryWrapper);
        if (CollUtil.isNotEmpty(merchantTopics)) {
            merchant.setMerchantTopics(merchantTopics);
        }
        // 查询商家套餐商品
        Goods goods = goodsService.getById(goodsId);
        if (!Optional.ofNullable(goods).isPresent()) {
            throw new ServiceException("商品不存在");
        }
        // 获取套餐信息
        QueryWrapper<SetMeal> setMealQueryWrapper = new QueryWrapper<>();
        setMealQueryWrapper.eq("goods_id", goods.getId());
        SetMeal setMeal = setMealService.getOne(setMealQueryWrapper);
        if (Optional.ofNullable(setMeal).isPresent()) {
            goods.setPicture(setMeal.getPicture());
            goods.setNotice(setMeal.getNotice());
            // 获取套餐项目
            QueryWrapper<SetMealItem> setMealItemQueryWrapper = new QueryWrapper<>();
            setMealItemQueryWrapper.eq("set_meal_id", setMeal.getId());
            List<SetMealItem> setMealItems = setMealItemService.list(setMealItemQueryWrapper);
            if (CollUtil.isNotEmpty(setMealItems)) {
                goods.setSetMealItems(setMealItems);
            }
        }
        merchant.setGoods(goods);

        return AppletResult.success(merchant);
    }

}