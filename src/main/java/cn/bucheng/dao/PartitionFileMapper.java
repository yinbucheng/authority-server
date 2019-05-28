package cn.bucheng.dao;

import cn.bucheng.model.po.PartitionFileRecordPO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface PartitionFileMapper extends BaseMapper<PartitionFileRecordPO> {
    int updateStartPosition(@Param("partitionId") long partitionId,@Param("startPosition") long startPosition);
    int updateFinishState(@Param("partitionId") Long partitionId);
    int updateTime(@Param("partitionId")long partitionId, @Param("time")Date date);
}
