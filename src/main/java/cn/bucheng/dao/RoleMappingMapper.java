package cn.bucheng.dao;

import cn.bucheng.model.domain.RoleMappingPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName RoleMappingMapper
 * @Author buchengyin
 * @Date 2019/5/20 16:07
 **/
public interface RoleMappingMapper extends BaseMapper<RoleMappingPO> {
    void deleteMappingAndRole(@Param("roleId")Long roleId,@Param("mappingIds")Long[] mappingIds );
}
