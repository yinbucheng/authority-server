package cn.bucheng.service;

import cn.bucheng.model.po.MappingEntity;
import cn.bucheng.model.dto.MappingRoleDTO;
import cn.bucheng.model.vo.MappingVO;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * @ClassName MappingService
 * @Author buchengyin
 * @Date 2019/5/20 16:09
 **/
public interface MappingService extends IService<MappingEntity> {
    void saveMapping(MappingVO vo)throws Exception;
    List<MappingRoleDTO> listMapping(Long projectId, Long roleId);
}
