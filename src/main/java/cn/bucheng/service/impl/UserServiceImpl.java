package cn.bucheng.service.impl;

import cn.bucheng.dao.UserMapper;
import cn.bucheng.dao.UserRoleMapper;
import cn.bucheng.model.po.UserEntity;
import cn.bucheng.model.po.UserRolePO;
import cn.bucheng.model.dto.UserMappingDTO;
import cn.bucheng.model.vo.UserRoleVO;
import cn.bucheng.model.vo.UserVO;
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
    @Transactional
    public void saveUser(UserVO userVo) throws Exception {
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
        throw new RuntimeException("test2");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    public void addRole(UserRoleVO vo) throws Exception {
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
    public Set<UserMappingDTO> listUserMapping(String userName, String password) {
        List<UserMappingDTO> records = baseMapper.listUserMapping(userName, password);
        if(records==null||records.size()==0){
            return null;
        }
        Set<UserMappingDTO> result = new TreeSet<>(new Comparator<UserMappingDTO>() {
            @Override
            public int compare(UserMappingDTO o1, UserMappingDTO o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        for(UserMappingDTO dto:records){
            result.add(dto);
        }
        return result;
    }

    @Override
    public void revokeRole(UserRoleVO vo) throws Exception {
        userRoleMapper.deleteUserAndRole(vo.getUserId(),vo.getRoleIds());
    }


}
