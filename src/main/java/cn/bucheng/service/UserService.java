package cn.bucheng.service;

import cn.bucheng.model.domain.UserEntity;
import cn.bucheng.model.dto.UserMappingDto;
import cn.bucheng.model.vo.UserRoleVo;
import cn.bucheng.model.vo.UserVo;
import com.baomidou.mybatisplus.service.IService;

import java.util.Set;

public interface UserService  extends IService<UserEntity> {
     void saveUser(UserVo userVo)throws Exception;

     void addRole(UserRoleVo vo)throws Exception;

     Set<UserMappingDto> listUserMapping(String userName,String password);

     void revokeRole(UserRoleVo vo)throws Exception;
}
