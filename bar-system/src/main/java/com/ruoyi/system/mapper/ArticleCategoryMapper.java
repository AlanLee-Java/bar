package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ArticleCategory;

import java.util.List;

/**
 * 文章分类Mapper接口
 * 
 * @author AlanLee
 * @date 2022-08-10
 */
public interface ArticleCategoryMapper 
{
    /**
     * 查询文章分类
     * 
     * @param id 文章分类主键
     * @return 文章分类
     */
    public ArticleCategory selectArticleCategoryById(Long id);

    /**
     * 查询文章分类列表
     * 
     * @param articleCategory 文章分类
     * @return 文章分类集合
     */
    public List<ArticleCategory> selectArticleCategoryList(ArticleCategory articleCategory);

    /**
     * 新增文章分类
     * 
     * @param articleCategory 文章分类
     * @return 结果
     */
    public int insertArticleCategory(ArticleCategory articleCategory);

    /**
     * 修改文章分类
     * 
     * @param articleCategory 文章分类
     * @return 结果
     */
    public int updateArticleCategory(ArticleCategory articleCategory);

    /**
     * 删除文章分类
     * 
     * @param id 文章分类主键
     * @return 结果
     */
    public int deleteArticleCategoryById(Long id);

    /**
     * 批量删除文章分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteArticleCategoryByIds(String[] ids);
}
