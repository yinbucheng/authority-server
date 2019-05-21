package cn.bucheng.controller;

import cn.bucheng.model.dto.ServerResult;
import cn.bucheng.model.vo.RoleMappingVo;
import cn.bucheng.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName RoleController
 * @Author buchengyin
 * @Date 2019/5/20 16:12
 **/
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/save")
    public Object saveRole(String name) {
        try {
            roleService.saveRole(name);
            return ServerResult.success();
        } catch (Exception e) {
            return ServerResult.fail(e.getMessage());
        }
    }

    @PostMapping("/addMapping")
    public Object addMappingToRole(@RequestBody RoleMappingVo vo){
        try {
            roleService.addMappingToRole(vo);
            return ServerResult.success();
        } catch (Exception e) {
            return ServerResult.fail(e.getMessage());
        }
    }


    @PostMapping("/revokeMapping")
    public Object revokeMappingFromRole(@RequestBody RoleMappingVo vo){
        try {
            roleService.revokeMappingFromRole(vo);
            return ServerResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResult.fail("操作失败");
        }
    }


}
