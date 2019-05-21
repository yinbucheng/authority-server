package cn.bucheng.dao;

import cn.bucheng.model.domain.UserEntity;
import cn.bucheng.model.dto.UserMappingDto;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Author buchengyin
 * @Date 2019/5/20 9:10
 **/
public interface UserMapper extends BaseMapper<UserEntity> {

    List<User> listPage(Pagination page, @Param("name")String name);

    List<UserMappingDto> listUserMapping(@Param("userName") String userName, @Param("password") String password);
}
