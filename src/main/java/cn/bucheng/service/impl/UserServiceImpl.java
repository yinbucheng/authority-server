package cn.bucheng.service.impl;

import cn.bucheng.dao.UserMapper;
import cn.bucheng.dao.UserRoleMapper;
import cn.bucheng.model.domain.UserEntity;
import cn.bucheng.model.domain.UserRolePO;
import cn.bucheng.model.dto.UserMappingDto;
import cn.bucheng.model.vo.UserRoleVo;
import cn.bucheng.model.vo.UserVo;
import cn.bucheng.service.UserService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName UserServiceImpl
 * @Author buchengyin
 * @Date 2019/5/20 9:13
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserEntity> implements UserService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public void saveUser(UserVo userVo) throws Exception {
        Wrapper<UserEntity> wrapper = new Condition().eq("name",userVo.getName());
        List<UserEntity> userEntities = baseMapper.selectList(wrapper);
        if(userEntities!=null&&userEntities.size()>0){
            throw new RuntimeException("用户已经存在");
        }
        UserEntity entity = new UserEntity();
        entity.setName(userVo.getName());
        entity.setNickName(userVo.getNickName());
        entity.setCreateTime(new Date());
        entity.setPassword(userVo.getPassword());
        baseMapper.insert(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public void addRole(UserRoleVo vo) throws Exception {
        for(Long roleId:vo.getRoleIds()){
            //校验是否未曾赋值角色过
            Wrapper<UserRolePO> wrapper = new Condition().eq("role_id",roleId).and().eq("user_id",vo.getUserId());
            List<UserRolePO> userRolePOS = userRoleMapper.selectList(wrapper);
            if(userRolePOS!=null&&userRolePOS.size()>0){
                throw new RuntimeException("存在角色授权过了");
            }
            UserRolePO po = new UserRolePO();
            po.setRoleId(roleId);
            po.setUserId(vo.getUserId());
            po.setCreateTime(new Date());
            userRoleMapper.insert(po);
        }
    }

    @Override
    public Set<UserMappingDto> listUserMapping(String userName, String password) {
        List<UserMappingDto> records = baseMapper.listUserMapping(userName, password);
        if(records==null||records.size()==0){
            return null;
        }
        Set<UserMappingDto> result = new TreeSet<>(new Comparator<UserMappingDto>() {
            @Override
            public int compare(UserMappingDto o1, UserMappingDto o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        for(UserMappingDto dto:records){
            result.add(dto);
        }
        return result;
    }

    @Override
    public void revokeRole(UserRoleVo vo) throws Exception {
        userRoleMapper.deleteUserAndRole(vo.getUserId(),vo.getRoleIds());
    }


}
