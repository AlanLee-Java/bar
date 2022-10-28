package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.ArticleCategory;
import com.ruoyi.system.mapper.ArticleCategoryMapper;
import com.ruoyi.system.service.IArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章分类Service业务层处理
 * 
 * @author AlanLee
 * @date 2022-08-10
 */
@Service
public class ArticleCategoryServiceImpl implements IArticleCategoryService 
{
    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    /**
     * 查询文章分类
     * 
     * @param id 文章分类主键
     * @return 文章分类
     */
    @Override
    public ArticleCategory selectArticleCategoryById(Long id)
    {
        return articleCategoryMapper.selectArticleCategoryById(id);
    }

    /**
     * 查询文章分类列表
     * 
     * @param articleCategory 文章分类
     * @return 文章分类
     */
    @Override
    public List<ArticleCategory> selectArticleCategoryList(ArticleCategory articleCategory)
    {
        return articleCategoryMapper.selectArticleCategoryList(articleCategory);
    }

    /**
     * 新增文章分类
     * 
     * @param articleCategory 文章分类
     * @return 结果
     */
    @Override
    public int insertArticleCategory(ArticleCategory articleCategory)
    {
        articleCategory.setCreateTime(DateUtils.getNowDate());
        articleCategory.setUpdateTime(DateUtils.getNowDate());
        return articleCategoryMapper.insertArticleCategory(articleCategory);
    }

    /**
     * 修改文章分类
     * 
     * @param articleCategory 文章分类
     * @return 结果
     */
    @Override
    public int updateArticleCategory(ArticleCategory articleCategory)
    {
        articleCategory.setUpdateTime(DateUtils.getNowDate());
        return articleCategoryMapper.updateArticleCategory(articleCategory);
    }

    /**
     * 批量删除文章分类
     * 
     * @param ids 需要删除的文章分类主键
     * @return 结果
     */
    @Override
    public int deleteArticleCategoryByIds(String ids)
    {
        return articleCategoryMapper.deleteArticleCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文章分类信息
     * 
     * @param id 文章分类主键
     * @return 结果
     */
    @Override
    public int deleteArticleCategoryById(Long id)
    {
        return articleCategoryMapper.deleteArticleCategoryById(id);
    }
}
