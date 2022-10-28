package com.ruoyi.system.service;

import com.ruoyi.system.domain.BrowseHistory;

import java.util.List;

/**
 * 浏览历史Service接口
 * 
 * @author AlanLee
 * @date 2022-09-22
 */
public interface IBrowseHistoryService 
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
     * 批量删除浏览历史
     * 
     * @param ids 需要删除的浏览历史主键集合
     * @return 结果
     */
    public int deleteBrowseHistoryByIds(String ids);

    /**
     * 删除浏览历史信息
     * 
     * @param id 浏览历史主键
     * @return 结果
     */
    public int deleteBrowseHistoryById(Long id);
}
