package com.penguin.mind.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AI 智能导航配置
 *
 * @author nian
 * @date 2026-05-03
 */
@Data
@Component
@ConfigurationProperties(prefix = "ai.api")
public class AiConfig {

    /**
     * 大模型 API Key
     */
    private String key;

    /**
     * 大模型 API 地址
     */
    private String url;

    /**
     * 模型名称
     */
    private String model;

    /**
     * 超时时间（秒）
     */
    private Integer timeout = 30;
}

