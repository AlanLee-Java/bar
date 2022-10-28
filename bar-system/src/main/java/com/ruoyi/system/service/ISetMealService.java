package com.ruoyi.system.service;

import com.ruoyi.system.domain.SetMeal;

import java.util.List;

/**
 * 套餐Service接口
 * 
 * @author AlanLee
 * @date 2022-09-01
 */
public interface ISetMealService 
{
    /**
     * 查询套餐
     * 
     * @param id 套餐主键
     * @return 套餐
     */
    public SetMeal selectSetMealById(Long id);

    /**
     * 查询套餐列表
     * 
     * @param setMeal 套餐
     * @return 套餐集合
     */
    public List<SetMeal> selectSetMealList(SetMeal setMeal);

    /**
     * 新增套餐
     * 
     * @param setMeal 套餐
     * @return 结果
     */
    public long insertSetMeal(SetMeal setMeal);

    /**
     * 修改套餐
     * 
     * @param setMeal 套餐
     * @return 结果
     */
    public int updateSetMeal(SetMeal setMeal);

    /**
     * 批量删除套餐
     * 
     * @param ids 需要删除的套餐主键集合
     * @return 结果
     */
    public int deleteSetMealByIds(String ids);

    /**
     * 删除套餐信息
     * 
     * @param id 套餐主键
     * @return 结果
     */
    public int deleteSetMealById(Long id);
}
