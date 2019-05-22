package cn.bucheng.service.impl;

import cn.bucheng.dao.MappingMapper;
import cn.bucheng.dao.RoleMappingMapper;
import cn.bucheng.model.domain.MappingEntity;
import cn.bucheng.model.dto.MappingRoleDto;
import cn.bucheng.model.vo.MappingVo;
import cn.bucheng.service.MappingService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName MappingServiceImpl
 * @Author buchengyin
 * @Date 2019/5/20 16:10
 **/
@Service
public class MappingServiceImpl extends ServiceImpl<MappingMapper, MappingEntity> implements MappingService {

    @Autowired
    private RoleMappingMapper roleMappingMapper;

    @Override
    public void saveMapping(MappingVo vo) throws Exception {
        Wrapper<MappingEntity> wrapper = new Condition().eq("url",vo.getUrl());
        List<MappingEntity> records = baseMapper.selectList(wrapper);
        if(records!=null&&records.size()>0){
            throw new RuntimeException("url "+vo.getUrl()+" 已经存在");
        }
        MappingEntity entity = new MappingEntity();
        entity.setName(vo.getName());
        entity.setProjectId(vo.getProjectId());
        entity.setUrl(vo.getUrl());
        entity.setCreateTime(new Date());
        entity.setRemark(vo.getRemark());
        entity.setWebId(vo.getWebId());
        baseMapper.insert(entity);
    }

    @Override
    public List<MappingRoleDto> listMapping(Long projectId, Long roleId) {
        List<Long> mappingIds = roleMappingMapper.listMappingId(roleId);
        List<MappingEntity> datas = baseMapper.selectList(new Condition().eq("project_id",projectId));
        if(datas==null){
            return null;
        }
        List<MappingRoleDto> result = new LinkedList<>();
        for(MappingEntity entity:datas){
            MappingRoleDto dto = new MappingRoleDto();
            dto.setCreateTime(entity.getCreateTime());
            dto.setMappingId(entity.getId());
            dto.setUrl(entity.getUrl());
            dto.setName(entity.getName());
            dto.setRemark(entity.getRemark());
            dto.setCheck(checkFlag(entity.getId(),mappingIds));
            result.add(dto);
        }
        return result;
    }

    private boolean checkFlag(Long mappingId,List<Long> mappingIds){
        if(mappingIds==null||mappingIds.size()==0)
            return false;
        for(Long tempId:mappingIds){
            if(tempId.equals(mappingId))
                return true;
        }
        return false;
    }
}
