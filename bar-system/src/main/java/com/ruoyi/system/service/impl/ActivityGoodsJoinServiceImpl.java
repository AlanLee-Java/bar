package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Activity;
import com.ruoyi.system.domain.ActivityGoodsJoin;
import com.ruoyi.system.domain.Goods;
import com.ruoyi.system.mapper.ActivityGoodsJoinMapper;
import com.ruoyi.system.mapper.ActivityMapper;
import com.ruoyi.system.mapper.GoodsMapper;
import com.ruoyi.system.service.IActivityGoodsJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 活动商品关联Service业务层处理
 *
 * @author AlanLee
 * @date 2022-08-28
 */
@Service
public class ActivityGoodsJoinServiceImpl implements IActivityGoodsJoinService {

    @Autowired
    private ActivityGoodsJoinMapper activityGoodsJoinMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private ActivityMapper activityMapper;

    /**
     * 查询活动商品关联
     *
     * @param id 活动商品关联主键
     * @return 活动商品关联
     */
    @Override
    public ActivityGoodsJoin selectActivityGoodsJoinById(Long id) {
        return activityGoodsJoinMapper.selectActivityGoodsJoinById(id);
    }

    /**
     * 查询活动商品关联列表
     *
     * @param activityGoodsJoin 活动商品关联
     * @return 活动商品关联
     */
	@Override
	public List<ActivityGoodsJoin> selectActivityGoodsJoinList(ActivityGoodsJoin ag, String goodName,
			Integer goodStatus) {

		return activityGoodsJoinMapper.selectActivityGoodsJoinList(ag, goodName, goodStatus);
	}

    /**
     * 新增活动商品关联
     *
     * @param activityGoodsJoin 活动商品关联
     * @return 结果
     */
    @Override
    @Transactional
    public int insertActivityGoodsJoin(ActivityGoodsJoin ag) {
    	// 查询活动所属商家ID
        Activity activity = activityMapper.selectActivityById(ag.getActivityId());
    	//添加商品
    	Goods good = ag.getGood();
    	good.setStatus(0);
    	good.setCreateBy(ag.getCreateBy());
    	good.setUpdateBy(ag.getUpdateBy());
    	good.setMerchantId(activity.getMerchantId());
    	goodsMapper.insertGoods(good);
    	ag.setGoodsId(good.getId());
        
        ag.setMerchantId(activity.getMerchantId());
        ag.setCreateTime(DateUtils.getNowDate());
        ag.setUpdateTime(DateUtils.getNowDate());
        return activityGoodsJoinMapper.insertActivityGoodsJoin(ag);
    }

    /**
     * 修改活动商品关联
     *
     * @param activityGoodsJoin 活动商品关联
     * @return 结果
     */
    @Override
    @Transactional
    public int updateActivityGoodsJoin(ActivityGoodsJoin activityGoodsJoin) {
        // 查询活动所属商家ID
        Activity activity = activityMapper.selectActivityById(activityGoodsJoin.getActivityId());
        activityGoodsJoin.setMerchantId(activity.getMerchantId());
        activityGoodsJoin.setUpdateTime(DateUtils.getNowDate());
        return activityGoodsJoinMapper.updateActivityGoodsJoin(activityGoodsJoin);
    }

    /**
     * 批量删除活动商品关联
     *
     * @param ids 需要删除的活动商品关联主键
     * @return 结果
     */
    @Override
    public int deleteActivityGoodsJoinByIds(String ids) {
    	activityGoodsJoinMapper.deleteGoodByGoodJoinIds(Convert.toStrArray(ids));
        return activityGoodsJoinMapper.deleteActivityGoodsJoinByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除活动商品关联信息
     *
     * @param id 活动商品关联主键
     * @return 结果
     */
    @Override
    public int deleteActivityGoodsJoinById(Long id) {
        return activityGoodsJoinMapper.deleteActivityGoodsJoinById(id);
    }
}
