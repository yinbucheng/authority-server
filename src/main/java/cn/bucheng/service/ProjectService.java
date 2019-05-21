package cn.bucheng.service;

import cn.bucheng.model.domain.ProjectEntity;
import com.baomidou.mybatisplus.service.IService;

/**
 * @ClassName ProjectService
 * @Author buchengyin
 * @Date 2019/5/20 16:08
 **/
public interface ProjectService extends IService<ProjectEntity> {
    void saveProject(String name)throws Exception;

    void saveModule(Long parentId,String name)throws Exception;

}
