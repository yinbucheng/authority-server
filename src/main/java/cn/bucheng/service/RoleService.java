package cn.bucheng.service;

import cn.bucheng.model.po.RoleEntity;
import cn.bucheng.model.dto.RoleMappingDTO;
import cn.bucheng.model.vo.RoleMappingVO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface RoleService extends IService<RoleEntity> {
    void saveRole(String name)throws Exception;

    void addMappingToRole(RoleMappingVO vo)throws Exception;

    List<RoleMappingDTO> listRoleMapping(Long roleId);

    void revokeMappingFromRole(RoleMappingVO vo)throws Exception;
}
