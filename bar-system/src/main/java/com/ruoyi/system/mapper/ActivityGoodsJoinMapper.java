package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ActivityGoodsJoin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 活动商品关联Mapper接口
 * 
 * @author AlanLee
 * @date 2022-08-28
 */
public interface ActivityGoodsJoinMapper 
{
    /**
     * 查询活动商品关联
     * 
     * @param id 活动商品关联主键
     * @return 活动商品关联
     */
    public ActivityGoodsJoin selectActivityGoodsJoinById(Long id);

    /**
     * 查询活动商品关联列表
     * 
     * @param activityGoodsJoin 活动商品关联
     * @return 活动商品关联集合
     */
	public List<ActivityGoodsJoin> selectActivityGoodsJoinList(@Param("en") ActivityGoodsJoin activityGoodsJoin,
			@Param("goodName") String goodName, @Param("goodStatus") Integer goodStatus);

	/**
	 * 新增活动商品关联
	 * 
	 * @param activityGoodsJoin
	 *            活动商品关联
	 * @return 结果
	 */
    public int insertActivityGoodsJoin(ActivityGoodsJoin activityGoodsJoin);

    /**
     * 修改活动商品关联
     * 
     * @param activityGoodsJoin 活动商品关联
     * @return 结果
     */
    public int updateActivityGoodsJoin(ActivityGoodsJoin activityGoodsJoin);

    /**
     * 删除活动商品关联
     * 
     * @param id 活动商品关联主键
     * @return 结果
     */
    public int deleteActivityGoodsJoinById(Long id);

    /**
     * 批量删除活动商品关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActivityGoodsJoinByIds(String[] ids);
    
    /**
     * 根据活动的商品id删除
     * @param ids
     * @return
     */
    public int  deleteGoodByGoodJoinIds(String[] ids);
}
