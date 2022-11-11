package com.ruoyi.applet.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.applet.config.DomainConfig;
import com.ruoyi.applet.config.RuoYiAppletConfig;
import com.ruoyi.applet.support.utils.FileUploadUtils;
import com.ruoyi.common.core.domain.AppletResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用请求处理
 *
 * @author AlanLee
 * @date 2022-09-02
 */
@Controller
@RequestMapping("/common")
@Slf4j
@Api(tags = "通用请求接口")
public class CommonController {

    private static final String FILE_DELIMETER = ",";

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private DomainConfig domainConfig;

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    @ResponseBody
    @ApiOperation("通用上传请求（单个）接口")
    public AppletResult uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            // 上传文件路径
            String filePath = RuoYiAppletConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);

            String url = domainConfig.getAdminUrl() + fileName;
            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("url", url);
            resultMap.put("fileName", fileName);
            resultMap.put("newFileName", FileUtils.getName(fileName));
            resultMap.put("originalFilename", file.getOriginalFilename());
            return AppletResult.success(resultMap);
        } catch (Exception e) {
            return AppletResult.error(e.getMessage());
        }
    }

    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    @ResponseBody
    @ApiOperation("通用上传请求（多个）接口")
    public AppletResult uploadFiles(@RequestPart("files") List<MultipartFile> files) {
        try {
            // 上传文件路径
            String filePath = RuoYiAppletConfig.getUploadPath();
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files) {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = domainConfig.getAdminUrl() + fileName;
                urls.add(url);
                fileNames.add(fileName);
                newFileNames.add(FileUtils.getName(fileName));
                originalFilenames.add(file.getOriginalFilename());
            }
            Map<String, String> resultMap = new HashMap<>();
            resultMap.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            resultMap.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            resultMap.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
            resultMap.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
            return AppletResult.success(resultMap);
        } catch (Exception e) {
            return AppletResult.error(e.getMessage());
        }
    }

}