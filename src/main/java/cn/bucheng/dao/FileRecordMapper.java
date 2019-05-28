package cn.bucheng.dao;

import cn.bucheng.model.po.FileRecordPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @ClassName FileRecordMapper
 * @Author buchengyin
 * @Date 2019/5/24 17:22
 **/
public interface FileRecordMapper extends BaseMapper<FileRecordPO> {
    int updateFileFinishFlag(@Param("fileId") long fileId);
    int updateTime(@Param("fileId")long fileId, @Param("time")Date time);
}
