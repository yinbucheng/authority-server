package cn.bucheng.model.vo;

import java.io.Serializable;

/**
 * @ClassName RoleMappingVO
 * @Author buchengyin
 * @Date 2019/5/20 17:31
 **/
public class RoleMappingVO implements Serializable {
    private Long roleId;
    private Long[] mappingIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long[] getMappingIds() {
        return mappingIds;
    }

    public void setMappingIds(Long[] mappingIds) {
        this.mappingIds = mappingIds;
    }
}
