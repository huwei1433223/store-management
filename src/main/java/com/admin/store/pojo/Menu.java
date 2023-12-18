package com.admin.store.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * @TableName menu
 */
@TableName(value = "menu")
@Data
public class Menu implements Serializable {
    /**
     *
     */
    @TableId
    private Integer menuId;

    /**
     * 菜单编码
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单级别
     */
    private String menuLevel;

    /**
     * 菜单的父编码
     */
    private String menuParentCode;

    /**
     * 点击触发的函数
     */
    private String menuClick;

    /**
     * 权限 0超级管理员，1表示管理员 2表示普通用户，用逗号组合
     */
    private String menuRight;

    /**
     * 跳转路径
     */
    private String menuComponent;

    /**
     * 图标
     */
    private String menuIcon;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Menu other = (Menu) that;
        return (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
                && (this.getMenuCode() == null ? other.getMenuCode() == null : this.getMenuCode().equals(other.getMenuCode()))
                && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
                && (this.getMenuLevel() == null ? other.getMenuLevel() == null : this.getMenuLevel().equals(other.getMenuLevel()))
                && (this.getMenuParentCode() == null ? other.getMenuParentCode() == null : this.getMenuParentCode().equals(other.getMenuParentCode()))
                && (this.getMenuClick() == null ? other.getMenuClick() == null : this.getMenuClick().equals(other.getMenuClick()))
                && (this.getMenuRight() == null ? other.getMenuRight() == null : this.getMenuRight().equals(other.getMenuRight()))
                && (this.getMenuComponent() == null ? other.getMenuComponent() == null : this.getMenuComponent().equals(other.getMenuComponent()))
                && (this.getMenuIcon() == null ? other.getMenuIcon() == null : this.getMenuIcon().equals(other.getMenuIcon()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getMenuCode() == null) ? 0 : getMenuCode().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getMenuLevel() == null) ? 0 : getMenuLevel().hashCode());
        result = prime * result + ((getMenuParentCode() == null) ? 0 : getMenuParentCode().hashCode());
        result = prime * result + ((getMenuClick() == null) ? 0 : getMenuClick().hashCode());
        result = prime * result + ((getMenuRight() == null) ? 0 : getMenuRight().hashCode());
        result = prime * result + ((getMenuComponent() == null) ? 0 : getMenuComponent().hashCode());
        result = prime * result + ((getMenuIcon() == null) ? 0 : getMenuIcon().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", menuCode=").append(menuCode);
        sb.append(", menuName=").append(menuName);
        sb.append(", menuLevel=").append(menuLevel);
        sb.append(", menuParentCode=").append(menuParentCode);
        sb.append(", menuClick=").append(menuClick);
        sb.append(", menuRight=").append(menuRight);
        sb.append(", menuComponet=").append(menuComponent);
        sb.append(", menuIcon=").append(menuIcon);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}