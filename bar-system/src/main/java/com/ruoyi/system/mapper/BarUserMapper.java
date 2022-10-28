package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BarUser;

import java.util.List;

/**
 * bar用户Mapper接口
 * 
 * @author AlanLee
 * @date 2022-09-22
 */
public interface BarUserMapper 
{
    /**
     * 查询bar用户
     * 
     * @param id bar用户主键
     * @return bar用户
     */
    public BarUser selectBarUserById(Long id);

    /**
     * 查询bar用户列表
     * 
     * @param barUser bar用户
     * @return bar用户集合
     */
    public List<BarUser> selectBarUserList(BarUser barUser);

    /**
     * 新增bar用户
     * 
     * @param barUser bar用户
     * @return 结果
     */
    public int insertBarUser(BarUser barUser);

    /**
     * 修改bar用户
     * 
     * @param barUser bar用户
     * @return 结果
     */
    public int updateBarUser(BarUser barUser);

    /**
     * 删除bar用户
     * 
     * @param id bar用户主键
     * @return 结果
     */
    public int deleteBarUserById(Long id);

    /**
     * 批量删除bar用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBarUserByIds(String[] ids);
}
