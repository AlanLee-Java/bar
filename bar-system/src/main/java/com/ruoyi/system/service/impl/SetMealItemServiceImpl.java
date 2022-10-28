package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SetMeal;
import com.ruoyi.system.domain.SetMealItem;
import com.ruoyi.system.mapper.SetMealItemMapper;
import com.ruoyi.system.mapper.SetMealMapper;
import com.ruoyi.system.service.ISetMealItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 套餐项目Service业务层处理
 *
 * @author AlanLee
 * @date 2022-09-01
 */
@Service
public class SetMealItemServiceImpl implements ISetMealItemService {

    @Autowired
    private SetMealItemMapper setMealItemMapper;

    @Autowired
    private SetMealMapper setMealMapper;

    /**
     * 查询套餐项目
     *
     * @param id 套餐项目主键
     * @return 套餐项目
     */
    @Override
    public SetMealItem selectSetMealItemById(Long id) {
        return setMealItemMapper.selectSetMealItemById(id);
    }

    /**
     * 查询套餐项目列表
     *
     * @param setMealItem 套餐项目
     * @return 套餐项目
     */
    @Override
    public List<SetMealItem> selectSetMealItemList(SetMealItem setMealItem) {
        return setMealItemMapper.selectSetMealItemList(setMealItem);
    }

    /**
     * 新增套餐项目
     *
     * @param setMealItem 套餐项目
     * @return 结果
     */
    @Override
    public int insertSetMealItem(SetMealItem setMealItem) {
        // 查询套餐所属商户ID
        SetMeal setMeal = setMealMapper.selectSetMealById(setMealItem.getSetMealId());
        setMealItem.setMerchantId(setMeal.getMerchantId());
        setMealItem.setCreateTime(DateUtils.getNowDate());
        setMealItem.setUpdateTime(DateUtils.getNowDate());
        return setMealItemMapper.insertSetMealItem(setMealItem);
    }

    /**
     * 修改套餐项目
     *
     * @param setMealItem 套餐项目
     * @return 结果
     */
    @Override
    public int updateSetMealItem(SetMealItem setMealItem) {
        // 查询套餐所属商户ID
        setMealItem.setUpdateTime(DateUtils.getNowDate());
        return setMealItemMapper.updateSetMealItem(setMealItem);
    }

    /**
     * 批量删除套餐项目
     *
     * @param ids 需要删除的套餐项目主键
     * @return 结果
     */
    @Override
    public int deleteSetMealItemByIds(String ids) {
        return setMealItemMapper.deleteSetMealItemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除套餐项目信息
     *
     * @param id 套餐项目主键
     * @return 结果
     */
    @Override
    public int deleteSetMealItemById(Long id) {
        return setMealItemMapper.deleteSetMealItemById(id);
    }

}