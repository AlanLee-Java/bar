package com.ruoyi.applet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.applet.domain.BrowseHistory;
import com.ruoyi.applet.model.vo.BrowseHistoryQueryVO;

/**
 * <p>
 * 浏览历史 服务类
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-17
 */
public interface BrowseHistoryService extends IService<BrowseHistory> {

    /**
     * 分页查询浏览历史
     *
     * @return
     */
    Page<BrowseHistoryQueryVO> queryBrowseHistoryPage(Page<BrowseHistoryQueryVO> page, Long userId, Integer type);

}