package cn.bucheng.service.impl;

import cn.bucheng.dao.ProjectMapper;
import cn.bucheng.dao.RoleMapper;
import cn.bucheng.dao.RoleMappingMapper;
import cn.bucheng.model.po.RoleEntity;
import cn.bucheng.model.po.RoleMappingPO;
import cn.bucheng.model.dto.RoleMappingDTO;
import cn.bucheng.model.vo.RoleMappingVO;
import cn.bucheng.service.RoleService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Author buchengyin
 * @Date 2019/5/20 16:11
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
    @Autowired
    private RoleMappingMapper roleMappingMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public void saveRole(String name) throws Exception {
        Wrapper<RoleEntity> wrapper = new Condition().eq("name", name);
        List<RoleEntity> results = baseMapper.selectList(wrapper);
        if (results != null && results.size() > 0) {
            throw new RuntimeException("角色已经存在了");
        }
        RoleEntity role = new RoleEntity();
        role.setName(name);
        role.setCreateTime(new Date());
        baseMapper.insert(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public void addMappingToRole(RoleMappingVO vo) throws Exception {
        Wrapper<RoleMappingPO> wrapper = new Condition().eq("role_id", vo.getRoleId()).and().in("mapping_id", vo.getMappingIds());
        List<RoleMappingPO> roleMappingPOS = roleMappingMapper.selectList(wrapper);
        if (roleMappingPOS != null && roleMappingPOS.size() > 0) {
            throw new RuntimeException("存在操作已经授权过了");
        }
        for (Long mappingId : vo.getMappingIds()) {
            RoleMappingPO po = new RoleMappingPO();
            po.setCreateTime(new Date());
            po.setRoleId(vo.getRoleId());
            po.setMappingId(mappingId);
            roleMappingMapper.insert(po);
        }
    }

    @Override
    public List<RoleMappingDTO> listRoleMapping(Long roleId) {

        return null;
    }

    @Override
    public void revokeMappingFromRole(RoleMappingVO vo) throws Exception {
        roleMappingMapper.deleteMappingAndRole(vo.getRoleId(), vo.getMappingIds());
    }
}
