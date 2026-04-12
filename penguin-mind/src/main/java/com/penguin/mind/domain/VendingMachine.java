package com.penguin.mind.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 自动售货机设备管理对象 tb_vending_machine
 * 
 * @author nian
 * @date 2026-04-12
 */
public class VendingMachine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 设备唯一编号 */
    @Excel(name = "设备唯一编号")
    private String innerCode;

    /** 设备最大货道容量 */
    private Long channelMaxCapacity;

    /** 关联点位ID */
    private Long nodeId;

    /** 设备详细地址 */
    @Excel(name = "设备详细地址")
    private String addr;

    /** 上次补货时间 */
    private Date lastSupplyTime;

    /** 商圈类型 */
    private Long businessType;

    /** 关联区域ID */
    private Long regionId;

    /** 关联合作商ID */
    @Excel(name = "关联合作商ID")
    private Long bearId;

    /** 设备型号ID */
    @Excel(name = "设备型号ID")
    private Long vmTypeId;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private Long vmStatus;

    /** 运行状态描述 */
    private String runningStatus;

    /** 经度 */
    private Long longitudes;

    /** 纬度 */
    private Long latitude;

    /** 设备客户端连接ID */
    private String clientId;

    /** 关联运营策略ID */
    private Long policyId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setInnerCode(String innerCode) 
    {
        this.innerCode = innerCode;
    }

    public String getInnerCode() 
    {
        return innerCode;
    }

    public void setChannelMaxCapacity(Long channelMaxCapacity) 
    {
        this.channelMaxCapacity = channelMaxCapacity;
    }

    public Long getChannelMaxCapacity() 
    {
        return channelMaxCapacity;
    }

    public void setNodeId(Long nodeId) 
    {
        this.nodeId = nodeId;
    }

    public Long getNodeId() 
    {
        return nodeId;
    }

    public void setAddr(String addr) 
    {
        this.addr = addr;
    }

    public String getAddr() 
    {
        return addr;
    }

    public void setLastSupplyTime(Date lastSupplyTime) 
    {
        this.lastSupplyTime = lastSupplyTime;
    }

    public Date getLastSupplyTime() 
    {
        return lastSupplyTime;
    }

    public void setBusinessType(Long businessType) 
    {
        this.businessType = businessType;
    }

    public Long getBusinessType() 
    {
        return businessType;
    }

    public void setRegionId(Long regionId) 
    {
        this.regionId = regionId;
    }

    public Long getRegionId() 
    {
        return regionId;
    }

    public void setBearId(Long bearId) 
    {
        this.bearId = bearId;
    }

    public Long getBearId() 
    {
        return bearId;
    }

    public void setVmTypeId(Long vmTypeId) 
    {
        this.vmTypeId = vmTypeId;
    }

    public Long getVmTypeId() 
    {
        return vmTypeId;
    }

    public void setVmStatus(Long vmStatus) 
    {
        this.vmStatus = vmStatus;
    }

    public Long getVmStatus() 
    {
        return vmStatus;
    }

    public void setRunningStatus(String runningStatus) 
    {
        this.runningStatus = runningStatus;
    }

    public String getRunningStatus() 
    {
        return runningStatus;
    }

    public void setLongitudes(Long longitudes) 
    {
        this.longitudes = longitudes;
    }

    public Long getLongitudes() 
    {
        return longitudes;
    }

    public void setLatitude(Long latitude) 
    {
        this.latitude = latitude;
    }

    public Long getLatitude() 
    {
        return latitude;
    }

    public void setClientId(String clientId) 
    {
        this.clientId = clientId;
    }

    public String getClientId() 
    {
        return clientId;
    }

    public void setPolicyId(Long policyId) 
    {
        this.policyId = policyId;
    }

    public Long getPolicyId() 
    {
        return policyId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("innerCode", getInnerCode())
            .append("channelMaxCapacity", getChannelMaxCapacity())
            .append("nodeId", getNodeId())
            .append("addr", getAddr())
            .append("lastSupplyTime", getLastSupplyTime())
            .append("businessType", getBusinessType())
            .append("regionId", getRegionId())
            .append("bearId", getBearId())
            .append("vmTypeId", getVmTypeId())
            .append("vmStatus", getVmStatus())
            .append("runningStatus", getRunningStatus())
            .append("longitudes", getLongitudes())
            .append("latitude", getLatitude())
            .append("clientId", getClientId())
            .append("policyId", getPolicyId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
