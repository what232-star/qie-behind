package com.penguin.mind.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 熊熊合作商管理对象 tb_bear
 * 
 * @author nian
 * @date 2026-04-04
 */
public class Bear extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 熊熊名称 */
    @Excel(name = "熊熊名称")
    private String bearName;

    /** 联系熊（对接人） */
    @Excel(name = "联系熊", readConverterExp = "对=接人")
    private String contactBear;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 分成比例（单位：百分比，如10代表10%） */
    @Excel(name = "分成比例", readConverterExp = "单=位：百分比，如10代表10%")
    private Long shareRatio;

    /** 登录账号 */
    @Excel(name = "登录账号")
    private String account;

    /** 登录密码（建议加密存储） */
    private String password;

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

    public void setBearName(String bearName) 
    {
        this.bearName = bearName;
    }

    public String getBearName() 
    {
        return bearName;
    }

    public void setContactBear(String contactBear) 
    {
        this.contactBear = contactBear;
    }

    public String getContactBear() 
    {
        return contactBear;
    }

    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }

    public void setShareRatio(Long shareRatio) 
    {
        this.shareRatio = shareRatio;
    }

    public Long getShareRatio() 
    {
        return shareRatio;
    }

    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
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
            .append("bearName", getBearName())
            .append("contactBear", getContactBear())
            .append("contactPhone", getContactPhone())
            .append("shareRatio", getShareRatio())
            .append("account", getAccount())
            .append("password", getPassword())
            .append("createTime", getCreateTime())
            .append("dateTime", getDateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
