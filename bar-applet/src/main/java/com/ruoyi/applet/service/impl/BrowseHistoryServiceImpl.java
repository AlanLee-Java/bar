package com.ruoyi.applet.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.applet.domain.Activity;
import com.ruoyi.applet.domain.BrowseHistory;
import com.ruoyi.applet.domain.Merchant;
import com.ruoyi.applet.domain.MerchantTopic;
import com.ruoyi.applet.mapper.BrowseHistoryMapper;
import com.ruoyi.applet.model.vo.BrowseHistoryQueryVO;
import com.ruoyi.applet.service.ActivityService;
import com.ruoyi.applet.service.BrowseHistoryService;
import com.ruoyi.applet.service.MerchantService;
import com.ruoyi.applet.service.MerchantTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 浏览历史 服务实现类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-17
 */
@Service
public class BrowseHistoryServiceImpl extends ServiceImpl<BrowseHistoryMapper, BrowseHistory> implements BrowseHistoryService {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MerchantTopicService merchantTopicService;

    @Autowired
    private ActivityService activityService;

    /**
     * 分页查询浏览历史
     *
     * @param page
     * @param userId
     * @param type
     * @return
     */
    @Override
    public Page<BrowseHistoryQueryVO> queryBrowseHistoryPage(Page<BrowseHistoryQueryVO> page, Long userId, Integer type) {
        Page<BrowseHistoryQueryVO> browseHistoryQueryVOPage = this.baseMapper.queryBrowseHistoryPage(page, userId, type);
        for (BrowseHistoryQueryVO browseHistoryQueryVO : browseHistoryQueryVOPage.getRecords()) {
            if (type == 1) {
                // 获取商家信息
                Merchant merchant = merchantService.getById(browseHistoryQueryVO.getObjectId());
                if (Optional.ofNullable(merchant).isPresent()) {
                    browseHistoryQueryVO.setMerchant(merchant);
                    // 获取商家话题
                    QueryWrapper<MerchantTopic> merchantTopicQueryWrapper = new QueryWrapper<>();
                    merchantTopicQueryWrapper.eq("merchant_id", merchant.getId());
                    merchantTopicQueryWrapper.orderByAsc("sort");
                    List<MerchantTopic> merchantTopicList = merchantTopicService.list(merchantTopicQueryWrapper);
                    if (CollectionUtil.isNotEmpty(merchantTopicList)) {
                        browseHistoryQueryVO.setMerchantTopicList(merchantTopicList);
                    }
                }
            } else {
                // 获取活动信息
                Activity activity = activityService.getById(browseHistoryQueryVO.getObjectId());
                if (Optional.ofNullable(activity).isPresent()) {
                    browseHistoryQueryVO.setActivity(activity);
                }
            }
        }
        return browseHistoryQueryVOPage;
    }

}