package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BarUser;
import com.ruoyi.system.mapper.BarUserMapper;
import com.ruoyi.system.service.IBarUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * bar用户Service业务层处理
 * 
 * @author AlanLee
 * @date 2022-09-22
 */
@Service
public class BarUserServiceImpl implements IBarUserService 
{
    @Autowired
    private BarUserMapper barUserMapper;

    /**
     * 查询bar用户
     * 
     * @param id bar用户主键
     * @return bar用户
     */
    @Override
    public BarUser selectBarUserById(Long id)
    {
        return barUserMapper.selectBarUserById(id);
    }

    /**
     * 查询bar用户列表
     * 
     * @param barUser bar用户
     * @return bar用户
     */
    @Override
    public List<BarUser> selectBarUserList(BarUser barUser)
    {
        return barUserMapper.selectBarUserList(barUser);
    }

    /**
     * 新增bar用户
     * 
     * @param barUser bar用户
     * @return 结果
     */
    @Override
    public int insertBarUser(BarUser barUser)
    {
        barUser.setCreateTime(DateUtils.getNowDate());
        return barUserMapper.insertBarUser(barUser);
    }

    /**
     * 修改bar用户
     * 
     * @param barUser bar用户
     * @return 结果
     */
    @Override
    public int updateBarUser(BarUser barUser)
    {
        barUser.setUpdateTime(DateUtils.getNowDate());
        return barUserMapper.updateBarUser(barUser);
    }

    /**
     * 批量删除bar用户
     * 
     * @param ids 需要删除的bar用户主键
     * @return 结果
     */
    @Override
    public int deleteBarUserByIds(String ids)
    {
        return barUserMapper.deleteBarUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除bar用户信息
     * 
     * @param id bar用户主键
     * @return 结果
     */
    @Override
    public int deleteBarUserById(Long id)
    {
        return barUserMapper.deleteBarUserById(id);
    }
}
