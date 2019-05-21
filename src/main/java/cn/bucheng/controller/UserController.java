package cn.bucheng.controller;

import cn.bucheng.model.domain.UserEntity;
import cn.bucheng.model.dto.PageDto;
import cn.bucheng.model.dto.ServerResult;
import cn.bucheng.model.dto.UserMappingDto;
import cn.bucheng.model.vo.BaseVo;
import cn.bucheng.model.vo.UserRoleVo;
import cn.bucheng.model.vo.UserVo;
import cn.bucheng.service.UserService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @ClassName UserController
 * @Author buchengyin
 * @Date 2019/5/20 16:12
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public Object saveUser(@RequestBody UserVo userVo) {
        try {
            userService.saveUser(userVo);
            return ServerResult.success();
        } catch (Exception e) {
            return ServerResult.fail(e.getMessage());
        }
    }

    @PostMapping("/listAll")
    public Object listUser(@RequestBody BaseVo baseVo) {
        Page<UserEntity> page = userService.selectPage(new Page<>(baseVo.getPageNum(), baseVo.getPageSize()));
        PageDto<UserEntity> result = new PageDto<>(page.getTotal(), page.getCurrent(), page.getSize(), page.getRecords());
        return ServerResult.success(result);
    }

    @PostMapping("/addRole")
    public Object addRoleToUser(@RequestBody UserRoleVo vo) {
        try {
            userService.addRole(vo);
            return ServerResult.success();
        } catch (Exception e) {
            return ServerResult.fail(e.getMessage());
        }
    }



    @PostMapping("/listUserMapping")
    public Object listUserMapping(String userName,String password){
        Set<UserMappingDto> userMappingDtos = userService.listUserMapping(userName, password);
        return ServerResult.success(userMappingDtos);
    }


    @PostMapping("/revokeRole")
    public Object revokeRoleFromUser(@RequestBody  UserRoleVo vo){
        try {
            userService.revokeRole(vo);
            return ServerResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResult.fail();
        }
    }


}
