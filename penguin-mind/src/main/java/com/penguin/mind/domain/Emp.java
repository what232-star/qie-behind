package com.penguin.mind.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.penguin.common.annotation.Excel;
import com.penguin.common.core.domain.BaseEntity;

/**
 * 人员列表对象 tb_emp
 * 
 * @author nian
 * @date 2026-04-09
 */
public class Emp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工ID（主键） */
    private Long id;

    /** 人员名称 */
    @Excel(name = "人员名称")
    private String userName;

    /** 所属区域ID */
    private Long regionId;

    /** 归属区域 */
    @Excel(name = "归属区域")
    private String regionName;

    /** 角色ID */
    private Long roleId;

    /** 角色编码 */
    private String roleCode;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String roleName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String mobile;

    /** 员工头像 */
    private String image;

    /** 是否启用 */
    private Long status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }

    public void setRegionId(Long regionId) 
    {
        this.regionId = regionId;
    }

    public Long getRegionId() 
    {
        return regionId;
    }

    public void setRegionName(String regionName) 
    {
        this.regionName = regionName;
    }

    public String getRegionName() 
    {
        return regionName;
    }

    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }

    public void setRoleCode(String roleCode) 
    {
        this.roleCode = roleCode;
    }

    public String getRoleCode() 
    {
        return roleCode;
    }

    public void setRoleName(String roleName) 
    {
        this.roleName = roleName;
    }

    public String getRoleName() 
    {
        return roleName;
    }

    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }

    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("regionId", getRegionId())
            .append("regionName", getRegionName())
            .append("roleId", getRoleId())
            .append("roleCode", getRoleCode())
            .append("roleName", getRoleName())
            .append("mobile", getMobile())
            .append("image", getImage())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
