package com.ruoyi.applet.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.applet.domain.BrowseHistory;
import com.ruoyi.applet.model.vo.BrowseHistoryQueryVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 浏览历史 Mapper 接口
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-17
 */
public interface BrowseHistoryMapper extends BaseMapper<BrowseHistory> {

    /**
     * 分页查询浏览历史
     *
     * @return
     */
    Page<BrowseHistoryQueryVO> queryBrowseHistoryPage(Page<BrowseHistoryQueryVO> page, @Param("userId") Long userId, @Param("type") Integer type);

}
