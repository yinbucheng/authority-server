package cn.bucheng.service.impl;

import cn.bucheng.dao.ProjectMapper;
import cn.bucheng.model.po.ProjectEntity;
import cn.bucheng.service.ProjectService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ProjectServiceImpl
 * @Author buchengyin
 * @Date 2019/5/20 16:10
 **/
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectEntity> implements ProjectService {
    @Override
    public void saveProject(String name)throws Exception{
        checkNameExist(name);
        ProjectEntity entity = new ProjectEntity();
        entity.setName(name);
        entity.setCreateTime(new Date());
        baseMapper.insert(entity);
    }

    private void checkNameExist(String name) {
        Wrapper<ProjectEntity> wrapper = new Condition().eq("name", name);
        List<ProjectEntity> records = baseMapper.selectList(wrapper);
        if (records != null&&records.size()>0) {
            throw new RuntimeException(name + "已经存在");
        }
    }

    @Override
    public void saveModule(Long parentId, String name) throws Exception {
        checkNameExist(name);
        ProjectEntity entity = new ProjectEntity();
        entity.setParentId(parentId);
        entity.setName(name);
        entity.setCreateTime(new Date());
        baseMapper.insert(entity);
    }
}
