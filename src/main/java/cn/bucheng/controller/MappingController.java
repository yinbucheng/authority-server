package cn.bucheng.controller;

import cn.bucheng.model.domain.MappingEntity;
import cn.bucheng.model.dto.MappingRoleDto;
import cn.bucheng.model.dto.PageDto;
import cn.bucheng.model.dto.ServerResult;
import cn.bucheng.model.vo.BaseVo;
import cn.bucheng.model.vo.MappingVo;
import cn.bucheng.service.MappingService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName MappingController
 * @Author buchengyin
 * @Date 2019/5/20 16:12
 **/
@RestController
@RequestMapping("/mapping")
public class MappingController {
    @Autowired
    private MappingService mappingService;

    @PostMapping("/save")
    public Object saveMapping(@RequestBody  MappingVo vo){
        try {
            mappingService.saveMapping(vo);
            return ServerResult.success();
        } catch (Exception e) {
            return ServerResult.fail(e.getMessage());
        }
    }

    @PostMapping("/listAll")
    public Object listAll(Long projectId,Long roleId){
        List<MappingRoleDto> result = mappingService.listMapping(projectId,roleId);
        return ServerResult.success(result);
    }
}
