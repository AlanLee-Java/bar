package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Goods;
import com.ruoyi.system.domain.SetMeal;
import com.ruoyi.system.mapper.GoodsMapper;
import com.ruoyi.system.mapper.SetMealMapper;
import com.ruoyi.system.service.ISetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 套餐Service业务层处理
 *
 * @author AlanLee
 * @date 2022-09-01
 */
@Service
public class SetMealServiceImpl implements ISetMealService {

    @Autowired
    private SetMealMapper setMealMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查询套餐
     *
     * @param id 套餐主键
     * @return 套餐
     */
    @Override
    public SetMeal selectSetMealById(Long id) {
        return setMealMapper.selectSetMealById(id);
    }

    /**
     * 查询套餐列表
     *
     * @param setMeal 套餐
     * @return 套餐
     */
    @Override
    public List<SetMeal> selectSetMealList(SetMeal setMeal) {
        return setMealMapper.selectSetMealList(setMeal);
    }

    /**
     * 新增套餐
     *
     * @param setMeal 套餐
     * @return 结果
     */
    @Override
    public long insertSetMeal(SetMeal setMeal) {
    	//添加商品
    	Goods good = setMeal.getGood();
    	good.setStatus(0);
    	good.setCreateTime(DateUtils.getNowDate());
    	good.setUpdateTime(DateUtils.getNowDate());
    	good.setMerchantId(setMeal.getMerchantId());
    	goodsMapper.insertGoods(good);
    	setMeal.setGoodsId(good.getId());
        setMeal.setCreateTime(good.getCreateTime());
        setMeal.setUpdateTime(good.getUpdateTime());
        int rows = setMealMapper.insertSetMeal(setMeal);
        if (rows <= 0) {
            throw new ServiceException("新增套餐失败");
        }
        return setMeal.getId();
    }

    /**
     * 修改套餐
     *
     * @param setMeal 套餐
     * @return 结果
     */
    @Override
    public int updateSetMeal(SetMeal setMeal) {
    	Goods good = setMeal.getGood();
    	good.setUpdateTime(DateUtils.getNowDate());
    	goodsMapper.updateGoods(good);
        setMeal.setUpdateTime(DateUtils.getNowDate());
        return setMealMapper.updateSetMeal(setMeal);
    }

    /**
     * 批量删除套餐
     *
     * @param ids 需要删除的套餐主键
     * @return 结果
     */
    @Override
    public int deleteSetMealByIds(String ids) {
        return setMealMapper.deleteSetMealByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除套餐信息
     *
     * @param id 套餐主键
     * @return 结果
     */
    @Override
    public int deleteSetMealById(Long id) {
        return setMealMapper.deleteSetMealById(id);
    }

}