package cn.bucheng.service;

import cn.bucheng.model.domain.RoleEntity;
import cn.bucheng.model.dto.RoleMappingDto;
import cn.bucheng.model.vo.RoleMappingVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface RoleService extends IService<RoleEntity> {
    void saveRole(String name)throws Exception;

    void addMappingToRole(RoleMappingVo vo)throws Exception;

    List<RoleMappingDto> listRoleMapping(Long roleId);

    void revokeMappingFromRole(RoleMappingVo vo)throws Exception;
}
