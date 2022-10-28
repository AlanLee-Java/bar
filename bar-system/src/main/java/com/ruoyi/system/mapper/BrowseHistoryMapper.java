package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BrowseHistory;

import java.util.List;

/**
 * 浏览历史Mapper接口
 * 
 * @author AlanLee
 * @date 2022-09-22
 */
public interface BrowseHistoryMapper 
{
    /**
     * 查询浏览历史
     * 
     * @param id 浏览历史主键
     * @return 浏览历史
     */
    public BrowseHistory selectBrowseHistoryById(Long id);

    /**
     * 查询浏览历史列表
     * 
     * @param browseHistory 浏览历史
     * @return 浏览历史集合
     */
    public List<BrowseHistory> selectBrowseHistoryList(BrowseHistory browseHistory);

    /**
     * 新增浏览历史
     * 
     * @param browseHistory 浏览历史
     * @return 结果
     */
    public int insertBrowseHistory(BrowseHistory browseHistory);

    /**
     * 修改浏览历史
     * 
     * @param browseHistory 浏览历史
     * @return 结果
     */
    public int updateBrowseHistory(BrowseHistory browseHistory);

    /**
     * 删除浏览历史
     * 
     * @param id 浏览历史主键
     * @return 结果
     */
    public int deleteBrowseHistoryById(Long id);

    /**
     * 批量删除浏览历史
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBrowseHistoryByIds(String[] ids);
}
