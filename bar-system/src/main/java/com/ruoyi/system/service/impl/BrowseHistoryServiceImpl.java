package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BrowseHistoryMapper;
import com.ruoyi.system.domain.BrowseHistory;
import com.ruoyi.system.service.IBrowseHistoryService;
import com.ruoyi.common.core.text.Convert;

/**
 * 浏览历史Service业务层处理
 * 
 * @author AlanLee
 * @date 2022-09-22
 */
@Service
public class BrowseHistoryServiceImpl implements IBrowseHistoryService 
{
    @Autowired
    private BrowseHistoryMapper browseHistoryMapper;

    /**
     * 查询浏览历史
     * 
     * @param id 浏览历史主键
     * @return 浏览历史
     */
    @Override
    public BrowseHistory selectBrowseHistoryById(Long id)
    {
        return browseHistoryMapper.selectBrowseHistoryById(id);
    }

    /**
     * 查询浏览历史列表
     * 
     * @param browseHistory 浏览历史
     * @return 浏览历史
     */
    @Override
    public List<BrowseHistory> selectBrowseHistoryList(BrowseHistory browseHistory)
    {
        return browseHistoryMapper.selectBrowseHistoryList(browseHistory);
    }

    /**
     * 新增浏览历史
     * 
     * @param browseHistory 浏览历史
     * @return 结果
     */
    @Override
    public int insertBrowseHistory(BrowseHistory browseHistory)
    {
        browseHistory.setCreateTime(DateUtils.getNowDate());
        return browseHistoryMapper.insertBrowseHistory(browseHistory);
    }

    /**
     * 修改浏览历史
     * 
     * @param browseHistory 浏览历史
     * @return 结果
     */
    @Override
    public int updateBrowseHistory(BrowseHistory browseHistory)
    {
        browseHistory.setUpdateTime(DateUtils.getNowDate());
        return browseHistoryMapper.updateBrowseHistory(browseHistory);
    }

    /**
     * 批量删除浏览历史
     * 
     * @param ids 需要删除的浏览历史主键
     * @return 结果
     */
    @Override
    public int deleteBrowseHistoryByIds(String ids)
    {
        return browseHistoryMapper.deleteBrowseHistoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除浏览历史信息
     * 
     * @param id 浏览历史主键
     * @return 结果
     */
    @Override
    public int deleteBrowseHistoryById(Long id)
    {
        return browseHistoryMapper.deleteBrowseHistoryById(id);
    }
}
