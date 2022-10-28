package com.ruoyi.system.service;

import com.ruoyi.system.domain.SetMealItem;

import java.util.List;

/**
 * 套餐项目Service接口
 * 
 * @author AlanLee
 * @date 2022-09-01
 */
public interface ISetMealItemService 
{
    /**
     * 查询套餐项目
     * 
     * @param id 套餐项目主键
     * @return 套餐项目
     */
    public SetMealItem selectSetMealItemById(Long id);

    /**
     * 查询套餐项目列表
     * 
     * @param setMealItem 套餐项目
     * @return 套餐项目集合
     */
    public List<SetMealItem> selectSetMealItemList(SetMealItem setMealItem);

    /**
     * 新增套餐项目
     * 
     * @param setMealItem 套餐项目
     * @return 结果
     */
    public int insertSetMealItem(SetMealItem setMealItem);

    /**
     * 修改套餐项目
     * 
     * @param setMealItem 套餐项目
     * @return 结果
     */
    public int updateSetMealItem(SetMealItem setMealItem);

    /**
     * 批量删除套餐项目
     * 
     * @param ids 需要删除的套餐项目主键集合
     * @return 结果
     */
    public int deleteSetMealItemByIds(String ids);

    /**
     * 删除套餐项目信息
     * 
     * @param id 套餐项目主键
     * @return 结果
     */
    public int deleteSetMealItemById(Long id);
}
