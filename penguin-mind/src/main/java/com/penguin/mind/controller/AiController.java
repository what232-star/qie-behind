package com.penguin.mind.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.penguin.mind.service.AiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.penguin.common.core.controller.BaseController;
import com.penguin.common.core.domain.AjaxResult;

import java.util.HashMap;
import java.util.Map;

import static com.penguin.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * AI 智能导航控制器
 * 
 * @author nian
 * @date 2026-05-03
 */
@RestController
@RequestMapping("/mind/ai")
public class AiController extends BaseController {

    @Autowired
    private AiService aiService;

    /**
     * 通用对话接口
     * 用户提问，AI 回答
     * 
     * @param message 用户输入
     * @return AI 回答
     */
    @PreAuthorize("@ss.hasPermi('mind:ai:chat')")
    @PostMapping("/chat")
    public AjaxResult chat(@RequestParam String message) {
        try {
            String response = aiService.chat(message);
            return success(response);
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    /**
     * 功能导航接口
     * 用户说"我要XXX"，AI 返回对应的菜单路由
     * 
     * @param message 用户输入
     * @param menuList 系统菜单列表（JSON 格式）
     * @return 匹配的路由信息或对话回复
     */
    @PreAuthorize("@ss.hasPermi('mind:ai:navigate')")
    @PostMapping("/navigate")
    public AjaxResult navigate(
            @RequestParam String message,
            @RequestParam String menuList) {
        try {
            // 调用 AI 服务获取导航结果
            String aiResponse = aiService.navigate(message, menuList);
            
            // 解析 AI 返回的 JSON 字符串
            JSONObject navData = JSON.parseObject(aiResponse);
            
            // 构建标准返回格式
            Map<String, Object> result = new HashMap<>();
            result.put("route", navData.getString("route"));
            result.put("name", navData.getString("name"));
            result.put("reply", navData.getString("reply"));
            
            return success(result);
            
        } catch (Exception e) {
            log.error("AI 导航失败", e);
            return error("导航服务异常：" + e.getMessage());
        }
    }
}
