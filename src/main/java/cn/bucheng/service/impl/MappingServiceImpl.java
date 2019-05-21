package cn.bucheng.service.impl;

import cn.bucheng.dao.MappingMapper;
import cn.bucheng.model.domain.MappingEntity;
import cn.bucheng.model.domain.UserEntity;
import cn.bucheng.model.vo.MappingVo;
import cn.bucheng.service.MappingService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MappingServiceImpl
 * @Author buchengyin
 * @Date 2019/5/20 16:10
 **/
@Service
public class MappingServiceImpl extends ServiceImpl<MappingMapper, MappingEntity> implements MappingService {

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
}
