package cn.bucheng.service;

import cn.bucheng.model.po.UserEntity;
import cn.bucheng.model.dto.UserMappingDTO;
import cn.bucheng.model.vo.UserRoleVO;
import cn.bucheng.model.vo.UserVO;
import com.baomidou.mybatisplus.service.IService;

import java.util.Set;

public interface UserService  extends IService<UserEntity> {
     void saveUser(UserVO userVo)throws Exception;

     void addRole(UserRoleVO vo)throws Exception;

     Set<UserMappingDTO> listUserMapping(String userName, String password);

     void revokeRole(UserRoleVO vo)throws Exception;
}
