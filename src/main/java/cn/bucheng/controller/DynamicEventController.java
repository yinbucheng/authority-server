package cn.bucheng.controller;

import cn.bucheng.model.po.TableEntity;
import cn.bucheng.model.po.TableFieldEntity;
import cn.bucheng.model.dto.ServerResult;
import cn.bucheng.model.vo.FieldValueVO;
import cn.bucheng.model.vo.TableFieldVO;
import cn.bucheng.service.DynamicEventService;
import com.baomidou.mybatisplus.mapper.Condition;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName DynamicEventController
 * @Author buchengyin
 * @Date 2019/5/22 16:44
 **/
@RestController
@RequestMapping("/dynamicEvent")
public class DynamicEventController {
    @Autowired
    private DynamicEventService dynamicEventService;

    @PostMapping("/addEvent")
    public Object createEvent(String eventName,String tableName){
        if(Strings.isEmpty(eventName)){
            return ServerResult.fail("请输入事件名称");
        }
        try {
            dynamicEventService.createEvent(eventName,tableName);
            return ServerResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/listEvents")
    public Object listEvents(){
        List<TableEntity> entities = dynamicEventService.selectList(new Condition());
        return ServerResult.success(entities);
    }

    @RequestMapping("/addField")
    public Object createField(@RequestBody TableFieldVO[] vos){
        if(vos==null||vos.length==0){
            return ServerResult.fail("请添加字段");
        }
        try {
            dynamicEventService.createField(vos);
            return ServerResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResult.fail(e.getMessage());
        }
    }

    @RequestMapping("/listFields")
    public Object listFields(Long tableId){
        List<TableFieldEntity> tableFieldEntities = dynamicEventService.listFieldEntity(tableId);
        return ServerResult.success(tableFieldEntities);
    }

    @RequestMapping("/addFieldValue")
    public Object createFiedValue(@RequestBody FieldValueVO[] vos){
        if(vos==null||vos.length==0){
            return ServerResult.fail("请输出值");
        }
        try {
            dynamicEventService.createFieldValue(vos);
            return ServerResult.success();
        } catch (Exception e) {
            return ServerResult.fail(e.getMessage());
        }
    }
}
