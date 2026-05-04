package com.penguin.mind.service;

import com.penguin.mind.config.AiConfig;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * AI 智能导航服务
 * 
 * @author nian
 * @date 2026-05-03
 */
@Slf4j
@Service
public class AiService {

    @Autowired
    private AiConfig aiConfig;

    private final OkHttpClient httpClient;

    public AiService() {
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 通用对话接口
     * 用户提问，AI 回答（限定业务相关）
     * 
     * @param userMessage 用户输入
     * @return AI 回答
     */
    public String chat(String userMessage) {
        log.info("========== AI 对话请求 ==========");
        log.info("用户输入: {}", userMessage);

        try {
            String systemPrompt = 
                    "你是一个智能售货柜运营系统的 AI 助手。你的任务是帮助用户使用系统功能。\n\n" +
                    "【你的能力】\n" +
                    "1. 导航跳转：当用户想要跳转到某个功能页面时，引导他们使用导航功能\n" +
                    "2. 功能介绍：向用户介绍系统各个功能模块的作用\n" +
                    "3. 操作指导：指导用户如何在系统中完成具体操作\n" +
                    "4. 问题解答：回答用户关于系统使用的问题\n\n" +
                    "【重要原则】\n" +
                    "1. 如果用户想要搜索/查询数据，指导他们到对应页面使用搜索功能\n" +
                    "2. 如果用户想了解某个功能，详细介绍该功能的作用和使用方法\n" +
                    "3. 如果用户表达模糊，主动询问并提供选项\n" +
                    "4. 始终提供具体、可操作的指导，不要说'系统未提供此功能'\n" +
                    "5. 如果用户的问题需要跳转页面才能完成，主动提供跳转建议\n\n" +
                    "【常用功能说明】\n" +
                    "- 设备管理（/vm/machine）：管理售货机设备，可以查看、添加、修改、删除设备，支持按设备编号、地址搜索\n" +
                    "- 设备类型管理（/vm/vmtype）：管理设备类型信息，可以查看、添加、修改、删除设备类型，支持按型号名称、型号编码搜索\n" +
                    "- 订单管理（/order）：查看订单列表，支持按订单编号、时间筛选，查看订单详情\n" +
                    "- 点位管理（/node/nodeList）：管理设备部署点位信息\n" +
                    "- 策略管理（/policy/policyList）：配置设备运营策略\n" +
                    "- 企鹅管理（/penguins/penguin）：管理企鹅商品信息\n\n" +
                    "【回复示例】\n" +
                    "用户说：'帮我搜索型号编码为 dd11 的设备类型'\n" +
                    "你应该回复：'好的！您可以到【设备类型管理】页面进行搜索。请按照以下步骤操作：\n" +
                    "1. 我可以为您跳转到设备类型管理页面\n" +
                    "2. 在页面顶部的搜索框中输入'dd11'\n" +
                    "3. 点击'搜索'按钮即可查询\n\n" +
                    "需要我为您跳转到设备类型管理页面吗？'\n\n" +
                    "用户说：'设备类型管理有什么用？'\n" +
                    "你应该回复：'设备类型管理模块用于管理售货机的设备类型信息，主要功能包括：\n" +
                    "1. 查看设备类型列表（型号名称、型号编码、货道配置等）\n" +
                    "2. 添加新的设备类型\n" +
                    "3. 修改现有设备类型信息\n" +
                    "4. 删除不需要的设备类型\n" +
                    "5. 支持按型号名称或编码搜索\n\n" +
                    "您可以对我说'带我去设备类型管理'，我为您跳转过去。'\n\n" +
                    "用户说：'我要怎么添加设备？'\n" +
                    "你应该回复：'添加设备需要在【设备管理】页面操作。步骤如下：\n" +
                    "1. 跳转到设备管理页面\n" +
                    "2. 点击页面左上角的'+'新增'按钮\n" +
                    "3. 填写设备信息（设备编号、地址、类型等）\n" +
                    "4. 点击'确定'保存\n\n" +
                    "需要我为您跳转到设备管理页面吗？'\n\n" +
                    "注意：始终提供积极、有帮助的回复，不要拒绝用户的需求！如果用户需要操作某个功能，主动提供跳转建议！";

            // 构建消息数组
            JSONArray messages = new JSONArray();

            // 系统消息
            JSONObject systemMsg = new JSONObject();
            systemMsg.put("role", "system");
            systemMsg.put("content", systemPrompt);
            messages.add(systemMsg);

            // 用户消息
            JSONObject userMsg = new JSONObject();
            userMsg.put("role", "user");
            userMsg.put("content", userMessage);
            messages.add(userMsg);

            // 调用大模型 API
            String aiResponse = callAiApi(messages);

            log.info("AI 对话结果: {}", aiResponse);
            return aiResponse;

        } catch (Exception e) {
            log.error("AI 对话失败", e);
            throw new RuntimeException("AI 对话服务异常：" + e.getMessage());
        }
    }

    /**
     * 功能导航接口
     * 用户说"我要XXX"，AI 返回对应的菜单路由
     * 
     * @param userMessage 用户输入
     * @param menuList 系统菜单列表（JSON 格式）
     * @return 匹配的路由地址（JSON 字符串）
     */
    public String navigate(String userMessage, String menuList) {
        log.info("========== AI 导航请求 ==========");
        log.info("用户输入: {}", userMessage);

        try {
            String systemPrompt = String.format(
                    "你是一个路由匹配助手。根据用户输入，返回对应的路由路径。\n\n" +
                    "【路由映射表】（必须严格匹配）：\n" +
                    "设备管理=/vm/machine\n" +
                    "订单管理=/order\n" +
                    "设备类型管理=/vm/vmtype\n" +
                    "点位管理=/node/nodeList\n" +
                    "策略管理=/policy/policyList\n" +
                    "企鹅管理=/penguins/penguin\n" +
                    "企鹅类型=/penguins/penguinClass\n" +
                    "区域管理=/node/region\n" +
                    "运营工单=/tasks/business\n" +
                    "运维工单=/tasks/operation\n" +
                    "熊商管理=/node/bear\n" +
                    "人员管理=/emp/empList\n" +
                    "角色管理=/system/role\n" +
                    "首页=/index\n\n" +
                    "【匹配规则 - 最高优先级】：\n" +
                    "1. 用户提到'设备'且没有'类型'二字 → 必须返回 /vm/machine (设备管理)\n" +
                    "2. 用户提到'设备类型'或'型号' → 返回 /vm/vmtype (设备类型管理)\n" +
                    "3. 用户提到'订单' → 返回 /order (订单管理)\n" +
                    "4. 用户提到'点位' → 返回 /node/nodeList (点位管理)\n" +
                    "5. 用户提到'策略' → 返回 /policy/policyList (策略管理)\n" +
                    "6. 用户提到'企鹅'且没有'类型'二字 → 返回 /penguins/penguin (企鹅管理)\n" +
                    "7. 用户提到'企鹅类型' → 返回 /penguins/penguinClass (企鹅类型)\n" +
                    "8. 用户提到'人员' → 返回 /emp/empList (人员管理)\n\n" +
                    "【重要提醒 - 常见错误案例】：\n" +
                    "❌ 错误：用户说'我要新增设备'，返回 /order (订单管理)\n" +
                    "✅ 正确：用户说'我要新增设备'，返回 /vm/machine (设备管理)\n" +
                    " 错误：用户说'添加设备'，返回 /vm/vmtype (设备类型管理)\n" +
                    "✅ 正确：用户说'添加设备'，返回 /vm/machine (设备管理)\n" +
                    "❌ 错误：用户说'带我去设备管理'，返回 /order (订单管理)\n" +
                    "✅ 正确：用户说'带我去设备管理'，返回 /vm/machine (设备管理)\n\n" +
                    "【示例】：\n" +
                    "输入：带我去设备管理  {\"route\":\"/vm/machine\",\"name\":\"设备管理\",\"reply\":\"好的，正在为您跳转到【设备管理】页面...\"}\n" +
                    "输入：我要新增设备  {\"route\":\"/vm/machine\",\"name\":\"设备管理\",\"reply\":\"好的，正在为您跳转到【设备管理】页面...\"}\n" +
                    "输入：添加设备  {\"route\":\"/vm/machine\",\"name\":\"设备管理\",\"reply\":\"好的，正在为您跳转到【设备管理】页面...\"}\n" +
                    "输入：带我去订单管理  {\"route\":\"/order\",\"name\":\"订单管理\",\"reply\":\"好的，正在为您跳转到【订单管理】页面...\"}\n" +
                    "输入：设备类型  {\"route\":\"/vm/vmtype\",\"name\":\"设备类型管理\",\"reply\":\"好的，正在为您跳转到【设备类型管理】页面...\"}\n" +
                    "输入：我要添加设备类型  {\"route\":\"/vm/vmtype\",\"name\":\"设备类型管理\",\"reply\":\"好的，正在为您跳转到【设备类型管理】页面...\"}\n" +
                    "输入：点位管理  {\"route\":\"/node/nodeList\",\"name\":\"点位管理\",\"reply\":\"好的，正在为您跳转到【点位管理】页面...\"}\n\n" +
                    "用户输入：%s\n" +
                    "请直接返回 JSON，不要任何解释：",
                    userMessage
            );

            // 构建消息数组
            JSONArray messages = new JSONArray();

            // 系统消息
            JSONObject systemMsg = new JSONObject();
            systemMsg.put("role", "system");
            systemMsg.put("content", systemPrompt);
            messages.add(systemMsg);

            // 用户消息
            JSONObject userMsg = new JSONObject();
            userMsg.put("role", "user");
            userMsg.put("content", userMessage);
            messages.add(userMsg);

            // 调用大模型 API
            String aiResponse = callAiApi(messages);

            log.info("AI 导航原始返回: {}", aiResponse);
            
            // 清理 AI 返回的内容，去除可能的代码标记
            aiResponse = aiResponse.trim();
            if (aiResponse.startsWith("``") && aiResponse.endsWith("``")) {
                aiResponse = aiResponse.substring(3, aiResponse.length() - 3);
            }
            if (aiResponse.startsWith("json") || aiResponse.startsWith("JSON")) {
                aiResponse = aiResponse.substring(4).trim();
            }

            log.info("AI 导航结果: {}", aiResponse);
            return aiResponse;

        } catch (Exception e) {
            log.error("AI 导航失败", e);
            throw new RuntimeException("AI 导航服务异常：" + e.getMessage());
        }
    }

    /**
     * 调用大模型 API（以阿里云通义千问为例）
     * 
     * @param messages 消息数组
     * @return AI 回答
     */
    private String callAiApi(JSONArray messages) throws Exception {
        // 构建请求体
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", aiConfig.getModel());
        requestBody.put("messages", messages);
        requestBody.put("temperature", 0.1); // 控制随机性
        requestBody.put("max_tokens", 2000); // 最大 token 数

        // 构建 HTTP 请求
        Request request = new Request.Builder()
                .url(aiConfig.getUrl())
                .header("Authorization", "Bearer " + aiConfig.getKey())
                .header("Content-Type", "application/json")
                .post(RequestBody.create(
                        MediaType.parse("application/json"),
                        requestBody.toJSONString()
                ))
                .build();

        // 发送请求
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("API 调用失败: " + response.code());
            }

            String responseBody = response.body().string();
            log.debug("API 响应: {}", responseBody);

            // 解析响应
            JSONObject responseJson = JSON.parseObject(responseBody);
            JSONArray choices = responseJson.getJSONArray("choices");
            if (choices != null && !choices.isEmpty()) {
                JSONObject firstChoice = choices.getJSONObject(0);
                JSONObject message = firstChoice.getJSONObject("message");
                return message.getString("content");
            }

            throw new RuntimeException("API 返回格式异常");
        }
    }
}
