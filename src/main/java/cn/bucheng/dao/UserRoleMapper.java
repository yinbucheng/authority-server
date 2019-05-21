package cn.bucheng.dao;

import cn.bucheng.model.domain.UserRolePO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserRoleMapper
 * @Author buchengyin
 * @Date 2019/5/20 16:05
 **/
public interface UserRoleMapper extends BaseMapper<UserRolePO> {
    void deleteUserAndRole(@Param("userId") Long userId,@Param("roleIds")Long[] roleIds);
}
