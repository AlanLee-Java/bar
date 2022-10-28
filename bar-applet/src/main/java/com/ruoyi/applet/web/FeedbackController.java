package com.ruoyi.applet.web;


import com.ruoyi.applet.domain.Feedback;
import com.ruoyi.applet.domain.User;
import com.ruoyi.applet.model.dto.FeedbackCreateDTO;
import com.ruoyi.applet.service.FeedbackService;
import com.ruoyi.applet.service.UserService;
import com.ruoyi.common.core.domain.AppletResult;
import com.ruoyi.common.enums.FeedbackStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * <p>
 * 问题反馈 前端控制器
 * </p>
 *
 * @author AlanLee
 * @since 2022-09-02
 */
@RestController
@RequestMapping("/feedback")
@Api(tags = "问题反馈相关接口")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 创建接口
     *
     * @return
     */
    @PostMapping("/create")
    @ApiOperation("创建接口")
    public AppletResult create(@RequestBody @Validated FeedbackCreateDTO feedbackCreateDTO) {
        User user = userService.getUserByRequest(request);
        Feedback feedback = new Feedback();
        if (Optional.ofNullable(user).isPresent()) {
            feedback.setUserId(user.getId());
        }
        feedback.setContent(feedbackCreateDTO.getContent());
        feedback.setScreenshot(feedbackCreateDTO.getScreenshot());
        feedback.setLinkman(feedbackCreateDTO.getLinkman());
        feedback.setContact(feedbackCreateDTO.getContact());
        feedback.setStatus(FeedbackStatus.PENDING.getStatus());
        feedback.setCreateTime(LocalDateTime.now());
        feedback.setUpdateTime(LocalDateTime.now());
        boolean result = feedbackService.save(feedback);
        return AppletResult.success(result);
    }

}