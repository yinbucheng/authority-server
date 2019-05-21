package cn.bucheng.controller;

import cn.bucheng.model.domain.MappingEntity;
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
    public Object listAll(@RequestBody  BaseVo vo){
        Page<MappingEntity> page = mappingService.selectPage(new Page<>(vo.getPageNum(), vo.getPageSize()));
        PageDto<MappingEntity> result = new PageDto<>(page.getTotal(),page.getCurrent(),page.getSize(),page.getRecords());
        return ServerResult.success(result);
    }
}
