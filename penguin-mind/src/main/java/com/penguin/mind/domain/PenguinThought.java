package com.penguin.mind.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 企鹅思想子对象 tb_penguin_thought
 * 
 * @author ruoyi
 * @date 2026-03-29
 */
public class PenguinThought extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 关联企鹅ID */
    @Excel(name = "关联企鹅ID")
    private Long penguinId;

    /** 思想神名 */
    @Excel(name = "思想神名")
    private String title;

    /** 思想内容 */
    @Excel(name = "思想内容")
    private String content;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPenguinId(Long penguinId) 
    {
        this.penguinId = penguinId;
    }

    public Long getPenguinId() 
    {
        return penguinId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("penguinId", getPenguinId())
            .append("title", getTitle())
            .append("content", getContent())
            .toString();
    }
}
