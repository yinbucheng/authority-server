package cn.bucheng.service.impl;

import cn.bucheng.dao.TableFieldMapper;
import cn.bucheng.dao.TableFieldValueMapper;
import cn.bucheng.dao.TableMapper;
import cn.bucheng.model.po.TableEntity;
import cn.bucheng.model.po.TableFieldEntity;
import cn.bucheng.model.po.TableFieldValueEntity;
import cn.bucheng.model.vo.FieldValueVO;
import cn.bucheng.model.vo.TableFieldVO;
import cn.bucheng.service.DynamicEventService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName DynamicEventServiceImpl
 * @Author buchengyin
 * @Date 2019/5/22 16:43
 **/
@Service
public class DynamicEventServiceImpl extends ServiceImpl<TableMapper, TableEntity> implements DynamicEventService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TableFieldMapper tableFieldMapper;
    @Autowired
    private TableFieldValueMapper fieldValueMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    @Override
    public void createEvent(String eventName, String tableName) throws RuntimeException {
        List<TableEntity> records = baseMapper.selectList(new Condition().eq("name", eventName).or().eq("table_name", tableName));
        if (records != null && records.size() > 0) {
            throw new RuntimeException("事件或者对应拼音表示已经存在请换别的名称");
        }
        TableEntity tableEntity = new TableEntity();
        tableEntity.setName(eventName);
        tableEntity.setCreateTime(new Date());
        tableEntity.setDatabase("yinchong");
        tableName = "t_" + tableName;
        tableEntity.setTableName(tableName);
        baseMapper.insert(tableEntity);
        try {
            jdbcTemplate.execute("drop table if exists yinchong." + tableName);
            jdbcTemplate.execute("create table " + tableName + "(" +
                    "id bigint primary key auto_increment)ENGINE=InnoDB AUTO_INCREMENT=11418 DEFAULT CHARSET=utf8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("创建表失败");
        }
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    @Transactional
    @Override
    public void createField(TableFieldVO[] vos) throws RuntimeException {
        Integer integer = tableFieldMapper.countName(vos);
        if (integer > 0) {
            throw new RuntimeException("存在字段已经创建");
        }
        TableEntity tableEntity = baseMapper.selectById(vos[0].getTableId());
        for (TableFieldVO vo : vos) {
            TableFieldEntity entity = new TableFieldEntity();
            entity.setCheckRule(vo.getCheckRule());
            entity.setColumnNum(vo.getColumnNum());
            entity.setRowNum(vo.getRowNum());
            entity.setTableId(vo.getTableId());
            entity.setType(vo.getType());
            entity.setName(vo.getFieldName());
            entity.setNeed(vo.getNeed());
            tableFieldMapper.insert(entity);
            String sql = "alter table " + tableEntity.getDatabase() + "." + tableEntity.getTableName() + " add column  "+vo.getFieldName()+" ";
            switch (vo.getType()){
                case TableFieldEntity.DATE:
                    sql+=" datetime ";
                    break;
                case TableFieldEntity.FILE:
                    sql+=" varchar(255) ";
                    break;
                case TableFieldEntity.NUMBER:
                    sql+=" int ";
                    break;
                case TableFieldEntity.TEXT_INPUT:
                    sql+=" varchar(255) ";
                    break;
                case TableFieldEntity.SELECT:
                    sql+=" bigint ";
                    break;
            }
            if(vo.getNeed()){
                sql+="not null";
            }
            jdbcTemplate.execute(sql);
        }
        throw new RuntimeException("test");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
    @Override
    public void createFieldValue(FieldValueVO[] vos) throws RuntimeException {
        Integer count = fieldValueMapper.checkRepeat(vos);
        if(count>0){
            throw new RuntimeException("字段已经存在值");
        }
        for(FieldValueVO vo:vos){
            TableFieldValueEntity entity = new TableFieldValueEntity();
            entity.setCreateTime(new Date());
            entity.setFieldId(vo.getFieldId());
            entity.setValue(vo.getValue());
            fieldValueMapper.insert(entity);
        }
    }

    @Override
    public List<TableFieldEntity> listFieldEntity(Long id) {
        return tableFieldMapper.selectList(new Condition().eq("table_id",id));
    }
}
