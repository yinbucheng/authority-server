package cn.bucheng.dao;

import cn.bucheng.model.po.TableFieldEntity;
import cn.bucheng.model.vo.TableFieldVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName TableFieldMapper
 * @Author buchengyin
 * @Date 2019/5/22 16:39
 **/
public interface TableFieldMapper extends BaseMapper<TableFieldEntity> {
    Integer countName(@Param("vos") TableFieldVO[] vos);
}
