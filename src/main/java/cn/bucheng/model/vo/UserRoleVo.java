package cn.bucheng.model.vo;

import java.io.Serializable;

/**
 * @ClassName UserRoleVo
 * @Author buchengyin
 * @Date 2019/5/20 17:38
 **/
public class UserRoleVo implements Serializable {
    private Long userId;
    private Long[] roleIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }
}
