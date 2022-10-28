package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ArticleCategory;
import com.ruoyi.system.service.IArticleCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章分类Controller
 *
 * @author AlanLee
 * @date 2022-08-10
 */
@Controller
@RequestMapping("/system/articleCategory")
public class ArticleCategoryController extends BaseController {

    private String prefix = "system/articleCategory";

    @Autowired
    private IArticleCategoryService articleCategoryService;

    @RequiresPermissions("system:articleCategory:view")
    @GetMapping()
    public String articleCategory() {
        return prefix + "/articleCategory";
    }

    /**
     * 查询文章分类列表
     */
    @RequiresPermissions("system:articleCategory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ArticleCategory articleCategory) {
        startPage();
        List<ArticleCategory> list = articleCategoryService.selectArticleCategoryList(articleCategory);
        return getDataTable(list);
    }

    /**
     * 新增文章分类
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存文章分类
     */
    @RequiresPermissions("system:articleCategory:add")
    @Log(title = "文章分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ArticleCategory articleCategory) {
        articleCategory.setCreateBy(getLoginName());
        articleCategory.setUpdateBy(getLoginName());
        return toAjax(articleCategoryService.insertArticleCategory(articleCategory));
    }

    /**
     * 修改文章分类
     */
    @RequiresPermissions("system:articleCategory:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ArticleCategory articleCategory = articleCategoryService.selectArticleCategoryById(id);
        mmap.put("articleCategory", articleCategory);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章分类
     */
    @RequiresPermissions("system:articleCategory:edit")
    @Log(title = "文章分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ArticleCategory articleCategory) {
        articleCategory.setUpdateBy(getLoginName());
        return toAjax(articleCategoryService.updateArticleCategory(articleCategory));
    }

    /**
     * 删除文章分类
     */
    @RequiresPermissions("system:articleCategory:remove")
    @Log(title = "文章分类", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(articleCategoryService.deleteArticleCategoryByIds(ids));
    }

}