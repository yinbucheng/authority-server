package cn.bucheng.dao;

import cn.bucheng.model.po.TableFieldValueEntity;
import cn.bucheng.model.vo.FieldValueVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName TableFieldValueMapper
 * @Author buchengyin
 * @Date 2019/5/22 16:40
 **/
public interface TableFieldValueMapper  extends BaseMapper<TableFieldValueEntity> {
    Integer checkRepeat(@Param("vos") FieldValueVO[] vos);
}
