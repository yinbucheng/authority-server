package cn.bucheng.service;

import cn.bucheng.model.domain.MappingEntity;
import cn.bucheng.model.vo.MappingVo;
import com.baomidou.mybatisplus.service.IService;

/**
 * @ClassName MappingService
 * @Author buchengyin
 * @Date 2019/5/20 16:09
 **/
public interface MappingService extends IService<MappingEntity> {
    void saveMapping(MappingVo vo)throws Exception;
}
