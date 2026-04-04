package com.penguin.mind.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 点位管理对象 tb_node
 * 
 * @author nian
 * @date 2026-04-04
 */
public class Node extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 点位名称 */
    @Excel(name = "点位名称")
    private String nodeName;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 商圈类型 1-校内 2-家属区 3-周边商圈 4-校外商业体 */
    @Excel(name = "商圈类型 1-校内 2-家属区 3-周边商圈 4-校外商业体")
    private Long businessType;

    /** 区域外键ID */
    @Excel(name = "区域外键ID")
    private String regionId;

    /** 熊熊合作商外键ID */
    @Excel(name = "熊熊合作商外键ID")
    private String bearId;

    /** 修改时间 */
    private Date dateTime;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }

    public void setNodeName(String nodeName) 
    {
        this.nodeName = nodeName;
    }

    public String getNodeName() 
    {
        return nodeName;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setBusinessType(Long businessType) 
    {
        this.businessType = businessType;
    }

    public Long getBusinessType() 
    {
        return businessType;
    }

    public void setRegionId(String regionId) 
    {
        this.regionId = regionId;
    }

    public String getRegionId() 
    {
        return regionId;
    }

    public void setBearId(String bearId) 
    {
        this.bearId = bearId;
    }

    public String getBearId() 
    {
        return bearId;
    }

    public void setDateTime(Date dateTime) 
    {
        this.dateTime = dateTime;
    }

    public Date getDateTime() 
    {
        return dateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nodeName", getNodeName())
            .append("address", getAddress())
            .append("businessType", getBusinessType())
            .append("regionId", getRegionId())
            .append("bearId", getBearId())
            .append("createTime", getCreateTime())
            .append("dateTime", getDateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
