package cn.bucheng.service;

import cn.bucheng.model.po.TableEntity;
import cn.bucheng.model.po.TableFieldEntity;
import cn.bucheng.model.vo.FieldValueVO;
import cn.bucheng.model.vo.TableFieldVO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface DynamicEventService extends IService<TableEntity> {
    void createEvent(String eventName,String tableName)throws RuntimeException;

    void createField(TableFieldVO[] vos)throws RuntimeException;

    void createFieldValue(FieldValueVO[] vos)throws RuntimeException;

    List<TableFieldEntity> listFieldEntity(Long id);
}
