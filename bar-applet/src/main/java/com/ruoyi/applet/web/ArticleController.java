package com.ruoyi.applet.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.applet.domain.Article;
import com.ruoyi.applet.service.ArticleService;
import com.ruoyi.common.core.domain.AppletResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author AlanLee
 * @since 2022-08-11
 */
@Api(tags = "文章相关接口")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 根据文章类型获取单篇文章
     *
     * @param categoryCode
     * @return
     */
    @ApiOperation("根据文章类型获取单篇文章")
    @GetMapping("/getOneByCategoryCode")
    public AppletResult getOneByCategoryCode(@RequestParam(name = "categoryCode") String categoryCode) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("category_code", categoryCode);
        Article article = articleService.getOne(articleQueryWrapper);
        return AppletResult.success(article);
    }

}