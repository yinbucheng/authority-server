package cn.bucheng.controller;

import cn.bucheng.model.domain.ProjectEntity;
import cn.bucheng.model.dto.PageDto;
import cn.bucheng.model.dto.ServerResult;
import cn.bucheng.model.vo.BaseVo;
import cn.bucheng.service.ProjectService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName ProjectController
 * @Author buchengyin
 * @Date 2019/5/20 16:12
 **/
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/saveProject")
    public Object saveProject(String name) {
        try {
            projectService.saveProject(name);
            return ServerResult.success();
        } catch (Exception e) {
            return ServerResult.fail(e.getMessage());
        }
    }

    @PostMapping("/saveModule")
    public Object saveModule(Long parentId,String name){
        try {
            projectService.saveModule(parentId,name);
            return ServerResult.success();
        } catch (Exception e) {
           return ServerResult.fail(e.getMessage());
        }
    }

    @PostMapping("/listProject")
    public Object listProject(@RequestBody  BaseVo vo){
        Wrapper<ProjectEntity> wrapper = new Condition().isNull("parent_id").and().isNull("delete_time");
        Page<ProjectEntity> page = new Page<>(vo.getPageNum(),vo.getPageSize());
        projectService.selectPage(page,wrapper);
        PageDto<ProjectEntity> dto = new PageDto(page);
        return ServerResult.success(dto);
    }

    @PostMapping("/listModules")
    public Object listModules(Long projectId){
        Wrapper<ProjectEntity> wrapper = new Condition().eq("parent_id",projectId).and().isNull("delete_time");
        List<ProjectEntity> projectEntities = projectService.selectList(wrapper);
        return ServerResult.success(projectEntities);
    }

}
